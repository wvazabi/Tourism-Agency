package business;

import core.Helper;
import dao.HotelDao;
import dao.PensionDao;
import dao.ReservationDao;
import dao.ReservationDao;
import dao.SeasonDao;
import entity.Reservation;

import java.sql.Date;
import java.util.ArrayList;

public class ReservationManager {

    private ReservationDao reservationDao;
    private HotelDao hotelDao;
    private PensionDao pensionDao;
    private SeasonDao seasonDao;

    public ReservationManager() {

        this.reservationDao = new ReservationDao();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();

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

            Object[] rowObject = new Object[size];
            rowObject[i++] = reservation.getId();
            rowObject[i++] = reservation.getRoomId();
            rowObject[i++] = reservation.getCheckInDate();
            rowObject[i++] = reservation.getCheckOutDate();
            rowObject[i++] = reservation.getTotalPrice();
            rowObject[i++] = reservation.getGuestName();
            rowObject[i++] = reservation.getGuestCitizenId();
            rowObject[i++] = reservation.getGuestMail();
            rowObject[i++] = reservation.getGuestPhone();
            rowObject[i++] = reservation.getGuestCount();

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
