package pl.edu.wszib.shop.core;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.shop.database.UserDB;
import pl.edu.wszib.shop.model.User;

public class Authenticator {
    final UserDB userDB = UserDB.getInstance();

    private static final Authenticator instance = new Authenticator();

    private Authenticator(){};
    private User loggedUser = null;

    private final String seed = "uyHTnBt}[Fp1+Ju8tre-@hV.Dn+p{ESft&E}IEwN";

    public void authenticate(User user){
        User userFromDB = this.userDB.findByLogin(user.getLogin());
        if(userFromDB != null && userFromDB.getPassword().equals(
                DigestUtils.md5Hex(user.getPassword() + this.seed))){
            this.loggedUser = userFromDB;
        }
    }

    public void setLoggedUserToNull(){
        this.loggedUser = null;
    } // method to logging out

    public static Authenticator getInstance() {
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public String getSeed() {
        return seed;
    }
}
