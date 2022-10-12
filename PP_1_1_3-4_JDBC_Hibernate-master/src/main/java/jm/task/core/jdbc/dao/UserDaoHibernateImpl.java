package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao  {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session =

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.HibernateUtil.getSession().getSessionFactory().openSession()) {
            return session.createQuery("from usersbase", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {

    }
}
