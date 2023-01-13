package pl.edu.wszib.shop.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.shop.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserDB {
    private static final UserDB instance = new UserDB();
    private final List<User> users = new LinkedList<>();

    private UserDB(){
        this.users.add(new User("root","a92615ee8cd375c73efa7aade97fc8d9", User.Role.ADMIN)); //password = root
        this.users.add(new User("janusz","ca6939b5bd2d5a49418509d536e8cc33", User.Role.USER)); //password = janusz
    }

    public Optional<User> findByLogin(String login){
        for(User user : this.users){
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void userAdd(String login, String password){
        users.add(new User(login,
                DigestUtils.md5Hex(password), User.Role.USER));
    }


    public List<User> getUsers() {
        return users;
    }

    public static UserDB getInstance() {
        return instance;
    }
}
