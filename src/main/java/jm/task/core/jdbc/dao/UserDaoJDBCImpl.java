package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    User user = new User();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age TINYINT)");
        //    connection.commit();
            System.out.println("table was created");
        } catch (SQLException e){
            System.out.println("error creating table");
            e.printStackTrace();
        //    if(connection!=null){
         //       connection.rollback();
         //   }
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

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
