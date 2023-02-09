package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age TINYINT)");
            System.out.println("table create success");
        } catch (SQLException e){
            System.out.println("error creating table");
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            System.out.println("table was deleted");
        } catch (SQLException e){
            System.out.println("error droping table");
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, last_name, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User s imenem - " + name + " dobavlen v bazu dannih");
        } catch (SQLException e){
            System.out.println("error save User");
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            preparedStatement.setLong(1,id);
            System.out.println("user " + id + " was removed");
        } catch (SQLException e){
            System.out.println("error remove user");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT name, last_name, age FROM user");
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e){
            System.out.println("error userList");
            e.printStackTrace();
        }
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM user");
            System.out.println("DELETE FROM user");
        } catch (SQLException e){
            System.out.println("error DELETE FROM user");
            e.printStackTrace();
        }
    }
}
