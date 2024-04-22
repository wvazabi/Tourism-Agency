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
    public ArrayList<Hotel> findAll(String query) {
        return hotelDao.findAll(query);
    }

    // tablodaki her satır için row object oluşturulup hotel lsitesine atanıyor
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel obj : hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getHotelName();
            rowObject[i++] = obj.getHotelAddress();
            rowObject[i++] = obj.getHotelMail();
            rowObject[i++] = obj.getHotelPhone();
            rowObject[i++] = obj.getHotelStar();
            rowObject[i++] = obj.getHotelCarPark();
            rowObject[i++] = obj.getHotelWifi();
            rowObject[i++] = obj.getHotelPool();
            rowObject[i++] = obj.getHotelFitness();
            rowObject[i++] = obj.getHotelConcierge();
            rowObject[i++] = obj.getHotelSpa();
            rowObject[i++] = obj.getHotelRoomService();


            hotelList.add(rowObject);
        }

        System.out.println("Hotel getfortable List: " + hotelList);


        return hotelList;
    }

    public boolean save(Hotel hotel) {
        if (hotel.getHotelId() != 0) {
            Helper.showMsg("error");
            return false;
        }
        return this.hotelDao.save(hotel);
    }

//    public boolean update(User user) {
//        if (this.getById(user.getId()) == null) {
//            Helper.showMsg("notFound");
//            return false;
//        }
//        return this.userDao.update(user);
//    }

    public boolean delete(int id) {

        if(this.getById(id) == null){
            Helper.showMsg("notFound");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public String searchHotelByQuery(String name, String address, String star) {

        return this.hotelDao.searchHotelByQuery(name, address, star);
    }




}
