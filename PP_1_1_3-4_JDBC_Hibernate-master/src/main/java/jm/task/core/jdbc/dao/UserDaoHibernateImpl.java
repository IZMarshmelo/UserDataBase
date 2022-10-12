package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

import static jm.task.core.jdbc.util.Util.HibernateUtil.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao  {

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();

        session.createNativeQuery ("CREATE TABLE IF NOT EXISTS users2" +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(20) NOT NULL, lastName VARCHAR(20) NOT NULL, " +
                "age TINYINT NOT NULL)")
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();

        session.createNativeQuery("DROP TABLE IF EXISTS users2")
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();

        session.createNativeQuery ( "INSERT INTO usersbase.users2 (name, lastName, age) VALUES (?, ?, ?)")
                .setParameter(1, name)
                .setParameter(2, lastName)
                .setParameter(3, age)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();

        session.createNativeQuery ("DELETE FROM users2 WHERE id = ?")
                .setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
            session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();

            return session.createQuery("FROM users", User.class).list();
    }

    @Override
    public void cleanUsersTable() {

    }
}
