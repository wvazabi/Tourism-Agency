package business;

import core.Helper;
import dao.HotelDao;
import dao.SeasonDao;
import entity.Season;
import entity.Hotel;

import java.util.ArrayList;

public class SeasonManager {
    // Initializing DAO objects
    private final SeasonDao seasonDao = new SeasonDao();
    private final HotelDao hotelDao = new HotelDao();

    // Method to retrieve a season by its ID
    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    // Method to retrieve all seasons
    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    // Method to create row objects for each season in the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList) {
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season obj : seasonList) {
            int i = 0;

            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId(); // Season ID
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName(); // Hotel Name
            rowObject[i++] = obj.getHotelId(); // Hotel ID
            rowObject[i++] = obj.getStartDate(); // Start Date
            rowObject[i++] = obj.getFinishDate(); // Finish Date

            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }

    // Method to save a new season
    public boolean save(Season season) {
        if (this.getById(season.getId()) != null) {
            Helper.showMsg("error", "");
            return false;
        }
        return this.seasonDao.save(season);
    }

    // Method to update an existing season
    public boolean update(Season season) {
        if (this.getById(season.getId()) == null) {
            Helper.showMsg(season.getId() + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.seasonDao.update(season);
    }

    // Method to delete a season by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.seasonDao.delete(id);
    }

    // Method to find seasons by hotel ID
    public ArrayList<Season> findByHotelId(int hotelId) {
        return this.seasonDao.findByHotelId(hotelId);
    }

    // Method to get seasons by hotel ID
    public ArrayList<Season> getByListSeasonId(int hotelId) {
        return this.seasonDao.getByListSeasonId(hotelId);
    }

    // Method to retrieve all hotel names for combo box
    public ArrayList<String> getAllHotelNames() {
        ArrayList<String> hotelNames = new ArrayList<>();
        ArrayList<Hotel> hotels = this.hotelDao.findAll();
        for (Hotel hotel : hotels) {
            hotelNames.add(hotel.getHotelName());
        }
        return hotelNames;
    }
}
