package entity;

import java.time.LocalDate;

public class Reservation {

    private int id;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private String guestName;
    private int guestCitizenId;
    private String guestMail;
    private String guestPhone;
    private int guestCount;

    public Reservation() {
    }

    public Reservation(int roomId,int numberOfNights, LocalDate checkInDate, LocalDate checkOutDate, double totalPrice, String guestName, int guestCitizenId, String guestMail, String guestPhone) {
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.guestName = guestName;
        this.guestCitizenId = guestCitizenId;
        this.guestMail = guestMail;
        this.guestPhone = guestPhone;
        this.guestCount = numberOfNights;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public int getId() {
        return id;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getGuestCitizenId() {
        return guestCitizenId;
    }

    public void setGuestCitizenId(int guestCitizenId) {
        this.guestCitizenId = guestCitizenId;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
}
