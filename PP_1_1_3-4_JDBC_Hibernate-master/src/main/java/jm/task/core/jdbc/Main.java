package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Ivan", "Zaitsev", (byte) 21);
        userDao.saveUser("Maksim", "Zaharov", (byte) 18);
        userDao.saveUser("Evgenii", "Shalapin", (byte)30);
        userDao.saveUser("Jora", "Kondratiev", (byte)25);


        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        Util.HibernateUtil.getSessionFactory();
        UserDao userDao2 = new UserDaoHibernateImpl();

        userDao2.createUsersTable();
        userDao2.saveUser("Sonya", "Juravlova", (byte)22);
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
