package business;

import core.Helper;
import dao.*;
import entity.Reservation;
import java.sql.Date;
import java.util.ArrayList;

public class ReservationManager {

    private ReservationDao reservationDao;
    private HotelDao hotelDao;
    private PensionDao pensionDao;
    private SeasonDao seasonDao;
    private RoomDao roomDao;

    public ReservationManager() {
        // Initializing DAO objects
        this.reservationDao = new ReservationDao();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
        this.roomDao = new RoomDao();
    }

    // Method to retrieve a reservation by its ID
    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    // Method to retrieve all reservations
    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    // Method to create row objects for each reservation in the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservationList) {
        ArrayList<Object[]> reservationObjList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            int i = 0;
            Object[] rowObject = new Object[size]; // 11 columns
            rowObject[i++] = reservation.getId(); // Reservation ID
            rowObject[i++] = reservation.getGuestCitizenId(); // Guest ID
            rowObject[i++] = reservation.getGuestName(); // Guest Name
            rowObject[i++] = reservation.getGuestPhone(); // Guest Phone
            rowObject[i++] = reservation.getGuestMail(); // Guest E-Mail
            rowObject[i++] = hotelDao.getById(roomDao.getById(reservation.getRoomId()).getHotelId()).getHotelName(); // Hotel Name
            rowObject[i++] = reservation.getCheckInDate(); // Check-in Date
            rowObject[i++] = reservation.getCheckOutDate(); // Check-out Date
            rowObject[i++] = pensionDao.getById(roomDao.getById(reservation.getRoomId()).getPensionId()).getPensionType(); // Pension Type
            rowObject[i++] = reservation.getNumberOfNights(); // Number of Nights
            rowObject[i++] = reservation.getGuestCount(); // Number of Guests
            rowObject[i++] = reservation.getTotalPrice(); // Total Cost
            reservationObjList.add(rowObject);
        }
        return reservationObjList;
    }

    // Method to save a new reservation
    public boolean save(Reservation reservation) {
        if (this.getById(reservation.getId()) != null) {
            Helper.showMsg("error", "");
            return false;
        }
        return this.reservationDao.save(reservation);
    }

    // Method to update an existing reservation
    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getId()) == null) {
            Helper.showMsg(reservation.getId() + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    // Method to delete a reservation by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.reservationDao.delete(id);
    }
}
