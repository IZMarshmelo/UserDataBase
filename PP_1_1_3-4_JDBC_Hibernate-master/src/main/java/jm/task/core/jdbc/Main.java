package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();
        userService.saveUser("Sonya", "Juravlova", (byte) 22);
        userService.saveUser("Ivan", "Lomakin", (byte) 40);
        userService.saveUser("Vlad", "Lobachov", (byte) 30);
        userService.saveUser("Jeorgii", "Hanov", (byte) 35);


        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        // реализуйте алгоритм здесь
    }
}
