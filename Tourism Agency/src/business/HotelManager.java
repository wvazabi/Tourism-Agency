package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    // Method to retrieve all hotels from the database
    public ArrayList<Hotel> findAll() {
        return hotelDao.findAll();
    }

    // Method to retrieve hotels based on a specific query
    public ArrayList<Hotel> findAll(String query) {
        return hotelDao.findAll(query);
    }

    // Method to create row objects for each hotel in the table
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

        return hotelList;
    }

    // Method to save a new hotel
    public boolean save(Hotel hotel) {
        if (hotel.getHotelId() != 0) {
            Helper.showMsg("error");
            return false;
        }
        return this.hotelDao.save(hotel);
    }

    // Method to update an existing hotel
    public boolean update(Hotel hotel) {
        if (this.getById(hotel.getHotelId()) == null) {
            Helper.showMsg("notFound");
            return false;
        }
        return this.hotelDao.update(hotel);
    }

    // Method to delete a hotel by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notFound");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    // Method to retrieve a hotel by its ID
    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    // Method to search for hotels based on a query
    public String searchHotelByQuery(String name, String address, String star) {
        return this.hotelDao.searchHotelByQuery(name, address, star);
    }
}
