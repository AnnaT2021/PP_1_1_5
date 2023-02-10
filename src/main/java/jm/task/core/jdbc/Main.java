package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        List<User> userList = new ArrayList<>();
        userList.add(new User("Ivan", "Petrov", (byte) 20));
        userList.add(new User("Petr", "Ivanov", (byte) 25));
        userList.add(new User("Semen", "Sidirov", (byte) 31));
        userList.add(new User("Mariya", "Kuznetsova", (byte) 38));
        userList.forEach(x ->{userService.saveUser(x.getName(), x.getLastName(), x.getAge());
            System.out.format("User с именем - %s добавлен в базу данных%n", x.getName() );});
        userService.getAllUsers().forEach(x -> System.out.println(x));
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
