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

        this.roomDao = new RoomDao();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();

    }



    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj : roomList) {
            int i = 0;

            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = this.pensionDao.getById(obj.getPensionId()).getPensionType(); // pension ismi eklendi
            rowObject[i++] = obj.getPensionId(); // pension ismi eklendi

            Date startDate = Date.valueOf(this.seasonDao.getById(obj.getSeasonId()).getStartDate());
            Date finishDate = Date.valueOf(this.seasonDao.getById(obj.getSeasonId()).getFinishDate());
            String seasonDates = startDate.toString() + " - " + finishDate.toString();

            rowObject[i++] = seasonDates;
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildPrice();
            rowObject[i++] = obj.getBedCapacity();
            rowObject[i++] = obj.getSquareMeter();
            rowObject[i++] = obj.isTv();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGameConsole();
            rowObject[i++] = obj.isCashBox();

            roomObjList.add(rowObject);
        }
        return roomObjList;
    }

    public boolean save(Room room) {
        if(this.getById(room.getId())!=null){
            Helper.showMsg("error","");
            return false ;
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room) {

        if(this.getById(room.getId()) == null){
            Helper.showMsg(room.getId()+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.roomDao.update(room);
    }

    public boolean delete(int id) {
        if(this.getById(id)==null){
            Helper.showMsg(id+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.roomDao.delete(id);
    }

}
