package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao  {
    private final Session session = getSessionFactory().openSession();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users" +
                            "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(20) NOT NULL, lastName VARCHAR(20) NOT NULL, " +
                            "age TINYINT NOT NULL)")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
       try (session) {
           transaction = session.beginTransaction();
           session.createNativeQuery("DROP TABLE IF EXISTS Users");
           transaction.commit();
       } catch (Exception e) {
           if (transaction != null) transaction.rollback();
           e.printStackTrace();
       }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch(Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Users WHERE id = :id")
                    .setParameter("id", id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM userbase.Users ORDER BY name").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (session) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE Users")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
