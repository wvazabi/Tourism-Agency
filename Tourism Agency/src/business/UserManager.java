package business;

import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password) {
        //different methods can be use FOR filter
        return this.userDao.findByLogin(username,password);
    }

    public ArrayList<User> findAll() {
        return userDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userList = new ArrayList<>();
        for (User obj : users) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();

            userList.add(rowObject);
        }
        return userList;
    }


}
