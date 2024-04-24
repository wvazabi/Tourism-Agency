package business;

import core.Helper;
import dao.HotelDao;
import dao.PensionDao;
import dao.RoomDao;
import dao.SeasonDao;
import entity.Room;

import java.sql.Date;
import java.util.ArrayList;

public class RoomManager {
    private RoomDao roomDao;
    private HotelDao hotelDao;
    private PensionDao pensionDao;
    private SeasonDao seasonDao;

    public RoomManager() {
        // Initializing DAO objects
        this.roomDao = new RoomDao();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
    }

    // Method to retrieve a room by its ID
    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    // Method to retrieve all rooms
    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    // Method to create row objects for room search table
    public ArrayList<Object[]> getForTableForRoomSrch(int size, ArrayList<Room> roomList) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj : roomList) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId(); // Room ID
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName(); // Hotel Name
            rowObject[i++] = obj.getHotelId(); // Hotel ID
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelAddress(); // Hotel Address
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelPhone(); // Hotel Phone
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelMail(); // Hotel Mail
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelStar(); // Hotel Star
            rowObject[i++] = obj.getType(); // Room Type
            rowObject[i++] = obj.getStock(); // Room Stock
            rowObject[i++] = obj.getBedCapacity(); // Bed Capacity

            roomObjList.add(rowObject);
        }
        return roomObjList;
    }

    // Method to create row objects for room table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj : roomList) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId(); // Room ID
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName(); // Hotel Name
            rowObject[i++] = obj.getHotelId(); // Hotel ID
            rowObject[i++] = this.pensionDao.getById(obj.getPensionId()).getPensionType(); // Pension Type
            rowObject[i++] = obj.getPensionId(); // Pension ID

            // Constructing season dates string
            Date startDate = Date.valueOf(this.seasonDao.getById(obj.getSeasonId()).getStartDate());
            Date finishDate = Date.valueOf(this.seasonDao.getById(obj.getSeasonId()).getFinishDate());
            String seasonDates = startDate.toString() + " - " + finishDate.toString();

            rowObject[i++] = seasonDates; // Season Dates
            rowObject[i++] = obj.getType(); // Room Type
            rowObject[i++] = obj.getStock(); // Room Stock
            rowObject[i++] = obj.getAdultPrice(); // Adult Price
            rowObject[i++] = obj.getChildPrice(); // Child Price
            rowObject[i++] = obj.getBedCapacity(); // Bed Capacity
            rowObject[i++] = obj.getSquareMeter(); // Square Meter
            rowObject[i++] = obj.isTv(); // TV availability
            rowObject[i++] = obj.isMinibar(); // Minibar availability
            rowObject[i++] = obj.isGameConsole(); // Game console availability
            rowObject[i++] = obj.isCashBox(); // Cash box availability

            roomObjList.add(rowObject);
        }
        return roomObjList;
    }

    // Method to save a new room
    public boolean save(Room room) {
        if (this.getById(room.getId()) != null) {
            Helper.showMsg("error", "");
            return false;
        }
        return this.roomDao.save(room);
    }

    // Method to update an existing room
    public boolean update(Room room) {
        if (this.getById(room.getId()) == null) {
            Helper.showMsg(room.getId() + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.roomDao.update(room);
    }

    // Method to delete a room by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.roomDao.delete(id);
    }
}
