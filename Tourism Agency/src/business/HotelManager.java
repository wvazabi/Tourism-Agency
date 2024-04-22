package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.User;

import java.util.ArrayList;

public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

//    public Hotel findByLogin(String username, String password) {
//        //different methods can be use FOR filter
//        return this.userDao.findByLogin(username,password);
//    }

    public ArrayList<Hotel> findAll() {
        return hotelDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel obj : h) {
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

    public boolean save(User user) {
        if (user.getId() != 0) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notFound");
            return false;
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {

        if(this.getById(id) == null){
            Helper.showMsg("notFound");
            return false;
        }
        return this.userDao.delete(id);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }


}
