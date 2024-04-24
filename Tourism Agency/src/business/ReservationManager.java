package business;

import core.Helper;
import dao.*;
import dao.ReservationDao;
import entity.Reservation;
import entity.Room;

import java.sql.Date;
import java.util.ArrayList;

public class ReservationManager {

    private ReservationDao reservationDao;
    private HotelDao hotelDao;
    private PensionDao pensionDao;
    private SeasonDao seasonDao;
    private RoomDao roomDao;

    public ReservationManager() {

        this.reservationDao = new ReservationDao();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
        this.roomDao = new RoomDao();

    }



    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

//    public ArrayList<Object[]> getForTableForreservationSrch(int size, ArrayList<reservation> reservationList) {
//        ArrayList<Object[]> reservationObjList = new ArrayList<>();
//        for (reservation obj : reservationList) {
//            int i = 0;
//
//            Object[] rowObject = new Object[size];
//            rowObject[i++] = obj.getId();
//            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName();
//            rowObject[i++] = obj.getHotelId();
//            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelAddress(); // pension ismi eklendi
//            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelPhone();// pension ismi eklendi
//            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelMail();
//            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelStar();
//            rowObject[i++] = obj.getType();
//            rowObject[i++] = obj.getStock();
//            rowObject[i++] = obj.getBedCapacity();
//
//            reservationObjList.add(rowObject);
//        }
//        return reservationObjList;
//    }



    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservationList) {
        ArrayList<Object[]> reservationObjList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            int i = 0;

            Object[] rowObject = new Object[size]; // 11 sütun var
            rowObject[i++] = reservation.getId(); // Reservation ID
            rowObject[i++] = reservation.getGuestCitizenId(); // Guest ID
            rowObject[i++] = reservation.getGuestName(); // Guest Name
            rowObject[i++] = reservation.getGuestPhone(); // Guest Phone
            rowObject[i++] = reservation.getGuestMail(); // Guest E-Mail
            rowObject[i++] = hotelDao.getById(roomDao.getById(reservation.getRoomId()).getHotelId()).getHotelName(); // Hotel Name
            rowObject[i++] = reservation.getCheckInDate(); // Check-in Date
            rowObject[i++] = reservation.getCheckOutDate(); // Check-out Date
            rowObject[i++] = pensionDao.getById(roomDao.getById(reservation.getRoomId()).getPensionId()).getPensionType();
            rowObject[i++] = reservation.getNumberOfNights(); // Number of Night
            rowObject[i++] = reservation.getGuestCount(); // Number of Guest
            rowObject[i++] = reservation.getTotalPrice(); // Total Cost

            reservationObjList.add(rowObject);
        }
        return reservationObjList;
    }



    public boolean save(Reservation reservation) {
        if(this.getById(reservation.getId())!=null){
            Helper.showMsg("error","");
            return false ;
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation) {

        if(this.getById(reservation.getId()) == null){
            Helper.showMsg(reservation.getId()+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int id) {
        if(this.getById(id)==null){
            Helper.showMsg(id+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.reservationDao.delete(id);
    }

}
