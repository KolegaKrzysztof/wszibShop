package pl.edu.wszib.shop.core;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.shop.database.UserDAO;
import pl.edu.wszib.shop.model.User;

import java.util.Optional;

public class Authenticator {
    final UserDAO userDAO = UserDAO.getInstance();

    private static final Authenticator instance = new Authenticator();

    private Authenticator(){};
    private Optional<User> loggedUser = Optional.empty();

    private final String seed = "uyHTnBt}[Fp1+Ju8tre-@hV.Dn+p{ESft&E}IEwN";

    public void authenticate(User user){
        Optional<User> userFromDB = this.userDAO.findByLogin(user.getLogin());
        if(userFromDB.isPresent() && userFromDB.get().getPassword().equals(
                DigestUtils.md5Hex(user.getPassword() + this.seed))){
            this.loggedUser = userFromDB;
        }
    }

    public void setLoggedUserToEmpty(){
        this.loggedUser = Optional.empty();
    } // method to logging out

    public static Authenticator getInstance() {
        return instance;
    }

    public Optional<User> getLoggedUser() {
        return loggedUser;
    }

    public String getSeed() {
        return seed;
    }
}
