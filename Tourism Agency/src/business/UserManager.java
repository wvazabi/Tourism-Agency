package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    // Initializing UserDao object
    private final UserDao userDao;

    public UserManager() {
        // Creating a UserDao object
        this.userDao = new UserDao();
    }

    // Method to find a user by login credentials (username and password)
    public User findByLogin(String username, String password) {
        // Use the UserDao's method to find a user by login credentials
        return this.userDao.findByLogin(username, password);
    }

    // Method to retrieve all users
    public ArrayList<User> findAll() {
        return userDao.findAll();
    }

    // Method to create row objects for each user in the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userList = new ArrayList<>();
        for (User obj : users) {
            int i = 0;
            Object[] rowObject = new Object[size];

            // Adding user attributes to the row object
            rowObject[i++] = obj.getId(); // User ID
            rowObject[i++] = obj.getUsername(); // Username
            rowObject[i++] = obj.getPassword(); // Password (Note: Passwords should not be stored in plain text in production environments)
            rowObject[i++] = obj.getRole(); // User role

            userList.add(rowObject);
        }
        return userList;
    }

    // Method to save a new user
    public boolean save(User user) {
        // Check if the user ID is not already set (indicating it's a new user)
        if (user.getId() != 0) {
            Helper.showMsg("error"); // Show error message
            return false;
        }
        return this.userDao.save(user); // Save the user using UserDao's save method
    }

    // Method to update an existing user
    public boolean update(User user) {
        // Check if the user exists in the database
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notFound"); // Show error message if user is not found
            return false;
        }
        return this.userDao.update(user); // Update the user using UserDao's update method
    }

    // Method to delete a user by its ID
    public boolean delete(int id) {
        // Check if the user exists in the database
        if (this.getById(id) == null) {
            Helper.showMsg("notFound"); // Show error message if user is not found
            return false;
        }
        return this.userDao.delete(id); // Delete the user using UserDao's delete method
    }

    // Method to retrieve a user by its ID
    public User getById(int id) {
        return this.userDao.getById(id); // Retrieve user by ID using UserDao's getById method
    }
}
