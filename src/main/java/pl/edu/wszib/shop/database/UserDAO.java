package pl.edu.wszib.shop.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.shop.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    private static final UserDAO instance = new UserDAO();

    private final Connection connection;
    private UserDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrent","root","");
        } catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByLogin(String login){
        try{
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return Optional.of(new User(rs.getString("login"),
                        rs.getString("password"),
                        User.Role.valueOf(rs.getString("role"))));
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    public void userAdd(String login, String password){

        try {
            String sql = "INSERT INTO tuser (login, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, DigestUtils.md5Hex(password));
            ps.setString(3, User.Role.USER.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tuser";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                users.add(new User(rs.getString("login"),
                        rs.getString("password"),
                        User.Role.valueOf(rs.getString("role"))));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return users;
    }


    public static UserDAO getInstance() {
        return instance;
    }
}
