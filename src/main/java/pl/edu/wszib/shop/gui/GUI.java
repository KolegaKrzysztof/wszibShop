package pl.edu.wszib.shop.gui;

import pl.edu.wszib.shop.core.Authenticator;
import pl.edu.wszib.shop.database.ProductDB;
import pl.edu.wszib.shop.database.UserDB;
import pl.edu.wszib.shop.model.Product;
import pl.edu.wszib.shop.model.User;


import java.util.Scanner;

public class GUI {

    final Authenticator authenticator = Authenticator.getInstance();
    final UserDB userDB = UserDB.getInstance();
    final ProductDB productDB = ProductDB.getInstance();

    public final Scanner scanner = new Scanner(System.in);

    private static final GUI instance = new GUI();
    private GUI(){
    }

    public String showLoggingMenu(){
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        return this.scanner.nextLine();
    }

    public String showMenu(){
        System.out.println(" Menu:");
        System.out.println("1. List products.");
        System.out.println("2. Buy products.");
        System.out.println("3. Logout.");
        System.out.println("4. Exit.");
        if(this.authenticator.getLoggedUser() != null &&
                this.authenticator.getLoggedUser().getRole() == User.Role.ADMIN){
            System.out.println("5. List users.");
            System.out.println("6. Change role.");
            System.out.println("7. Add units to stock.");
        }
        return this.scanner.nextLine();
    }

    public User readLoginAndPassword(){
        User user = new User();
        System.out.println("Login: ");
        user.setLogin(this.scanner.nextLine());
        System.out.println("Passord: ");
        user.setPassword(this.scanner.nextLine());
        return user;
    }

    public void listProducts(){
        for( Product product : this.productDB.getProducts()){
            System.out.println(product.toString());
        }
    }

    public void listUsers(){
        for( User user : this.userDB.getUsers()){
            System.out.println(new StringBuilder()
                    .append("Login: '")
                    .append(user.getLogin())
                    .append("' Role: '")
                    .append(user.getRole())
                    .append("'"));
        }
    }

    public void changeRole(User user){
        System.out.println("Which role would you like to set for user: " + user.getLogin());
        System.out.println("1. USER");
        System.out.println("2. ADMIN");
        switch (scanner.nextLine()){
            case "1":
                user.setRole(User.Role.USER);
                break;
            case "2":
                user.setRole(User.Role.ADMIN);
                break;
            default:
                System.out.println("Wrong choice !!!");
                break;
        }
    }

    public Product readBrandAndModel(){
        Product product = new Product();
        System.out.println("Brand: ");
        product.setBrand(this.scanner.nextLine());
        System.out.println("Model: ");
        product.setModel(scanner.nextLine());
        return product;
    }

    public void buyProducts(){
        System.out.println("Which product would you like to buy? ");
        Product product = productDB.findProduct(this.readBrandAndModel());
        if(product != null){
            System.out.println("Units: ");
            productDB.buyProducts(product, Integer.parseInt(this.scanner.nextLine()));
        } else {
            System.out.println("Product doesn't exists!!!");
        }
    }

    public void addUnits(){
        System.out.println("Which product would you like to supply units?");
        Product product = productDB.findProduct(this.readBrandAndModel());
        if(product != null){
            System.out.println(new StringBuilder("How many units would you like to add to '")
                    .append(product.getBrand())
                    .append(" ")
                    .append(product.getModel())
                    .append("' stock?"));
            this.productDB.addUnits( product, Integer.parseInt(this.scanner.nextLine()));
        } else {
            System.out.println("Product doesn't exists!!!");
        }
    }

    public static GUI getInstance(){
        return instance;
    }
}





