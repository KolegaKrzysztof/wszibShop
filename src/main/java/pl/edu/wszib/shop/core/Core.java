package pl.edu.wszib.shop.core;

import pl.edu.wszib.shop.database.UserDB;
import pl.edu.wszib.shop.gui.GUI;
import pl.edu.wszib.shop.model.User;


public class Core {

    final Authenticator authenticator = Authenticator.getInstance();
    final GUI gui = GUI.getInstance();
    final UserDB userDB = UserDB.getInstance();

    private static final Core instace = new Core();

    private Core(){
    }

    public void start(){
        boolean isRunning = false;
        int counter = 0;

        while(!isRunning && counter < 3){
            switch (this.gui.showLoggingMenu()){
                case "1":
                    this.authenticator.authenticate(this.gui.readLoginAndPassword());
                    isRunning = this.authenticator.getLoggedUser().isPresent();
                    counter++;
                    if(!isRunning){
                        System.out.println("Not authorized!!!");
                    }
                    break;
                case "2": //user registration
                    User user = this.gui.readLoginAndPassword();
                    if(this.userDB.findByLogin(user.getLogin()).isEmpty()){
                        this.userDB.userAdd(user.getLogin(), user.getPassword() + this.authenticator.getSeed());
                    } else {
                        System.out.println("Login is taken !!!");
                    }
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }

        while(isRunning){
            switch(this.gui.showMenu()){
                case "1":  //menu testowe
                    this.gui.listProducts();
                    break;
                case "2":  //menu testowe
                    this.gui.buyProducts();
                    break;
                case "3": // logout
                    isRunning = false;
                    this.authenticator.setLoggedUserToNull();
                    instace.start();
                    break;
                case "4": // exit
                    isRunning = false;
                    break;
                case "5": //list users
                    if(this.authenticator.getLoggedUser().isPresent() && this.authenticator.
                            getLoggedUser().get().getRole() == User.Role.ADMIN){
                        this.gui.listUsers();
                        break;
                    }
                case "6"://change role
                    this.gui.changeRole();
                    break;
                case "7"://modyfing number of units in stock
                    if(this.authenticator.getLoggedUser().isPresent() && this.authenticator.
                            getLoggedUser().get().getRole() == User.Role.ADMIN){
                        this.gui.addUnits();
                    }
                    break;
                default:
                    System.out.println("Wrong choose !!");
                    break;
            }
        }
    }

    public static Core getInstance(){
        return instace;
    }
}
