package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao userDao2 = new UserDaoHibernateImpl();

        userDao2.createUsersTable();
        userDao2.saveUser("Sonya", "Juravlova", (byte) 22);
        userDao2.saveUser("Ivan", "Lomakin", (byte) 40);
        userDao2.saveUser("Vlad", "Lobachov", (byte) 30);
        userDao2.saveUser("Jeorgii", "Hanov", (byte) 35);


        userDao2.removeUserById(1);
        userDao2.getAllUsers();
        userDao2.cleanUsersTable();
        userDao2.dropUsersTable();

        // реализуйте алгоритм здесь
    }
}
