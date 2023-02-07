package pp_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my_data_base";
        String username = "root1";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, username, password)){
            if(!conn.isClosed()){
                System.out.println("Connection to my_data_base succesfull!");
            }
        }
        catch(SQLException e) {
            System.out.println("Неудалось подключиться");
        }
    }
}
