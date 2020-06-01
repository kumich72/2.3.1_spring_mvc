package web.service;

import org.springframework.stereotype.Service;
import web.DAO.IUserDAO;
import web.DAO.UserDaoFactory;
import web.exception.DBException;
import web.model.User;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User getUserById(Long id) {
        IUserDAO userDAO = UserDaoFactory.CreateDao();
        try {
            User user = userDAO.getUserById(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        IUserDAO userDAO = UserDaoFactory.CreateDao();

        List<User> users = new ArrayList<>();
        try {
            users = userDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(Long id) throws DBException {
        IUserDAO userDAO = UserDaoFactory.CreateDao();

        try {
            if (userDAO.deleteUser(id)) {
                return true;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return false;
    }

    public boolean addUser(User user) throws DBException {
        IUserDAO userDAO = UserDaoFactory.CreateDao();

        try {
            if (userDAO.addUser(user)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean editUser(Long id, String name, String password, String email) throws DBException {
        IUserDAO userDAO = UserDaoFactory.CreateDao();

        try {
            if (userDAO.editUser(id, name, password, email)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean userIsAdmin(String name, String password) throws DBException {
        IUserDAO userDAO = UserDaoFactory.CreateDao();
        try {
            if (userDAO.userIsAdmin(name, password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public User getUserByNameAndPassword(String name, String password) {
        IUserDAO userDAO = UserDaoFactory.CreateDao();
        try {
            User user = userDAO.getUserByNameAndPassword(name, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userIsAdmin(Long id) throws DBException {
        IUserDAO userDAO = UserDaoFactory.CreateDao();
        try {
            if (userDAO.userIsAdmin(id)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}