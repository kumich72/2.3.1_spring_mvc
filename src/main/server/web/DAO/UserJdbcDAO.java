package web.DAO;

import web.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements IUserDAO {
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users WHERE id='" + id + "'");
        ResultSet result = statement.getResultSet();
        if (result.next()) {
            User user = new User();
            user.setId(result.getLong("id"));
            user.setEmail(result.getString("email"));
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            return user;
        }
        result.close();
        statement.close();
        return null;
    }

    public List<User> getAllUsers()  {
        List<User> users = new ArrayList<User>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("SELECT * FROM users");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public boolean addUser(User user)  {
        try {
            if (getUserIdByName(user.getName()) == 0) {
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate("INSERT INTO users (name, password, email, role) VALUES ('"
                        + user.getName() + "', '" + user.getPassword() + "' ,'" + user.getEmail() + ",'" + user.getRole() + " ')");
                statement.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean editUser(Long id, String name, String password, String email){
        try {
            if (userExistById(id)) {
                String SQLQuery = "UPDATE users SET email = ?, password = ?  WHERE  id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setLong(3, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean userExistById(Long id) throws SQLException {
        boolean isExist = false;
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM users WHERE id='" + id + "'");
        ResultSet result = stmt.getResultSet();
        if (result.next()) {
            isExist = true;
        }
        result.close();
        stmt.close();
        return isExist;
    }

    public boolean deleteUser(Long id) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE id = '" + id + "'");
            stmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean userIsAdmin(String name, String password) {
        try {
            String SQLQuery = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                preparedStatement.close();
                return true;
            }
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        try {
            String SQLQuery = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.executeQuery();
            ResultSet result = preparedStatement.getResultSet();
            if (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
                return user;
            }
            preparedStatement.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userIsAdmin(Long id) {
        return false;
    }

    public Long getUserIdByName(String name) {
        Statement stmt = null;
        Long id = null;
        try {
            stmt = connection.createStatement();
            stmt.execute("SELECT * FROM users WHERE name='" + name + "'");
            ResultSet result = stmt.getResultSet();
            id = Long.valueOf(0);
            if (result.next()) {
                id = result.getLong("id");
                result.close();
                stmt.close();
            }
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), email varchar(256), primary key (id))");
        stmt.close();
    }


//    public void dropTable() throws SQLException {
//        Statement stmt = connection.createStatement();
//        stmt.executeUpdate("DROP TABLE IF EXISTS users");
//        stmt.close();
//    }


}
