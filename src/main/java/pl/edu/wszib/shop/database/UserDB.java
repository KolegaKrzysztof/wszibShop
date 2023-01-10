package pl.edu.wszib.shop.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.shop.model.User;

import java.util.LinkedList;

public class UserDB {
    private static final UserDB instance = new UserDB();
    private final LinkedList<User> users = new LinkedList<User>();

    private UserDB(){
        this.users.add(new User("root","a92615ee8cd375c73efa7aade97fc8d9", User.Role.ADMIN)); //password = root
        this.users.add(new User("janusz","ca6939b5bd2d5a49418509d536e8cc33", User.Role.USER)); //password = janusz
    }

    public User findByLogin(String login){
        for(User user : this.users){
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public void userAdd(String login, String password){
        users.add(new User(login,
                DigestUtils.md5Hex(password), User.Role.USER));
    }


    public LinkedList<User> getUsers() {
        return users;
    }

    public static UserDB getInstance() {
        return instance;
    }
}
