package pl.edu.wszib.shop.gui;

import pl.edu.wszib.shop.core.Authenticator;
import pl.edu.wszib.shop.database.ProductDB;
import pl.edu.wszib.shop.database.UserDB;
import pl.edu.wszib.shop.model.Product;
import pl.edu.wszib.shop.model.User;


import java.util.*;

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
        if(this.authenticator.getLoggedUser().isPresent() &&
                this.authenticator.getLoggedUser().get().getRole() == User.Role.ADMIN){
            System.out.println("5. List users.");
            System.out.println("6. Change role.");
            System.out.println("7. Add units to stock.");
            System.out.print("[" + this.authenticator.getLoggedUser().get().getLogin() + "]# ");
        } else if(this.authenticator.getLoggedUser().isPresent() &&
                this.authenticator.getLoggedUser().get().getRole() == User.Role.USER){
            System.out.print("[" + this.authenticator.getLoggedUser().get().getLogin() + "]$ ");
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
        List<Product> copyOfProductDB = new ArrayList<>(this.productDB.getProducts());
        copyOfProductDB.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if(p1.getClass().equals(p2.getClass())){
                    return (int)(p1.getPrice() - p2.getPrice());
                }
                return p1.getClass().toString().compareTo(p2.getClass().toString());
            }
        });
        for( Product product : copyOfProductDB){
            System.out.println(product.toString());
        }
    }

    public void listUsers(){
        List<User> copyOfUserDB = new ArrayList<>(this.userDB.getUsers());
        copyOfUserDB.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getRole().equals(o2.getRole())){
                    return o1.getLogin().compareTo(o2.getLogin());
                }
                return o1.getRole().compareTo(o2.getRole());
            }
        });
        for( User user : copyOfUserDB){
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
        Optional<Product> product = productDB.findProduct(this.readBrandAndModel());
        if(product.isPresent()){
            System.out.println("Units: ");
            productDB.buyProducts(product.get(), Integer.parseInt(this.scanner.nextLine()));
        } else {
            System.out.println("Product doesn't exists!!!");
        }
    }

    public void addUnits(){
        System.out.println("Which product would you like to supply units?");
        Optional<Product> product = productDB.findProduct(this.readBrandAndModel());
        if(product.isPresent()){
            System.out.println(new StringBuilder("How many units would you like to add to '")
                    .append(product.get().getBrand())
                    .append(" ")
                    .append(product.get().getModel())
                    .append("' stock?"));
            this.productDB.addUnits( product.get(), Integer.parseInt(this.scanner.nextLine()));
        } else {
            System.out.println("Product doesn't exists!!!");
        }
    }

    public void changeRole(){
        if(this.authenticator.getLoggedUser().isPresent() && this.authenticator.
                getLoggedUser().get().getRole() == User.Role.ADMIN) {
            System.out.println("Enter login whose role you would like to change: ");
            Optional<User> user = this.userDB.findByLogin(this.scanner.nextLine());
            if (user.isPresent() && !user.get().equals(this.authenticator.getLoggedUser().get())) {
                this.changeRole(user.get());
            } else {
                System.out.println("User doesn't exist or You tried to change your role.");
            }
        }
    }

    public static GUI getInstance(){
        return instance;
    }
}





