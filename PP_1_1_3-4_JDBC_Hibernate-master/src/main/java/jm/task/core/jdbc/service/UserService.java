package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public void createUsersTable() throws SQLException;

    public void dropUsersTable();

    public void saveUser(String name, String lastName, byte age);

    public void removeUserById(long id);

    public List<User> getAllUsers() throws SQLException;

    public void cleanUsersTable();
}
