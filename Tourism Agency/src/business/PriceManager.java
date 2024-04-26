package business;

import core.Helper;
import dao.*;
import entity.Price;
import entity.Season;

import java.util.ArrayList;

public class PriceManager {

    private PriceDao priceDao;
    private HotelDao hotelDao;
    private PensionDao pensionDao;
    private SeasonDao seasonDao;
    private RoomDao roomDao;

    public PriceManager() {
        // Initializing priceDao and HotelDao objects
        this.priceDao = new PriceDao();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
        this.roomDao = new RoomDao();
    }

    // Method to retrieve a price by its ID
    public Price getById(int id) {
        return this.priceDao.getById(id);
    }

    // Method to retrieve all prices
    public ArrayList<Price> findAll() {
        return this.priceDao.findAll();
    }

    // Method to create row objects for each price in the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Price> priceList) {
        ArrayList<Object[]> priceObjList = new ArrayList<>();
        for (Price obj : priceList) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = this.seasonDao.getById(obj.getSeason_id()).getStartDate();
            rowObject[i++] = this.seasonDao.getById(obj.getSeason_id()).getFinishDate();
            rowObject[i++] = this.pensionDao.getById(obj.getPensionId()).getPensionType();
            rowObject[i++] = this.roomDao.getById(obj.getRoom_id()).getType();

//            if (obj.getRoom() != null) {
//                if (obj.getRoom().getType() != null) {
//                    rowObject[i++] = this.roomDao.getById(obj.getRoom_id()).getType();
//                } else {
//                    rowObject[i++] = "Room Type bulunamadı";
//                }
//            } else {
//                rowObject[i++] = "Room bulunamadı";
//            }
            rowObject[i++] = obj.getAge();
            rowObject[i++] = obj.getPrice();

            priceObjList.add(rowObject);
        }
        return priceObjList;
    }

    // Method to save a new price
    public boolean save(Price price) {
        if (this.getById(price.getId()) != null) {
            Helper.showMsg("error", "");
            return false;
        }
        return this.priceDao.save(price);
    }

    // Method to update an existing price
    public boolean update(Price price) {
        if (this.getById(price.getId()) == null) {
            Helper.showMsg(price.getId() + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.priceDao.update(price);
    }

    // Method to delete a price by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.priceDao.delete(id);
    }

    // Method to find prices by hotel I
    public  boolean deletePeriod(int season_id) {
        return priceDao.deletePeriod(season_id);
    }
    public  boolean deleteHotel(int hotel_id) {
        return priceDao.deleteHotel(hotel_id);
    }
    public ArrayList<Price> findByRoomId(int roomId) {
        return this.priceDao.findByRoomId(roomId);
    }

}
