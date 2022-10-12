package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.HibernateUtil.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao  {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery ("CREATE TABLE IF NOT EXISTS users2" +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(20) NOT NULL, lastName VARCHAR(20) NOT NULL, " +
                "age TINYINT NOT NULL)")
                .addEntity(User.class);

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
       try (Session session = getSessionFactory().openSession()) {
           session.beginTransaction();
           session.createNativeQuery("DROP TABLE IF EXISTS users2");
           session.getTransaction().commit();
       }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery ("DELETE FROM users2 WHERE id = ?")
                .setParameter(1, id).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("FROM users2 order by name").list();

        transaction.commit();
        session.close();

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createNativeQuery("DELETE Users2")
                .executeUpdate();

        transaction.commit();
        session.close();
    }
}
