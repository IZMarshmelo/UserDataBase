package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/usersbase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public class HibernateUtil {
        private static SessionFactory concreteSessionFactory;

        static {
            try {
                Properties setting = new Properties();
                setting.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/usersbase2");
                setting.setProperty("hibernate.connection.username", "root");
                setting.setProperty("hibernate.connection.password", "root");
                setting.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

                setting.setProperty("hibernate.hbm2ddl.auto", "create");

                concreteSessionFactory = new org.hibernate.cfg.Configuration()
                        .addProperties(setting)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static Session getSession() throws HibernateException {
            return concreteSessionFactory.openSession();
        }
    }
}

