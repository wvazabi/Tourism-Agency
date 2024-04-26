package entity;

import business.HotelManager;
import business.RoomManager;
import business.SeasonManager;

public class Price {
    private int id;
    private int hotel_id;
    private Hotel hotel;
    private int season_id;
    private Season season;
    private String age;
    private int pensionId;
    private Double price;
    private String season_start;
    private String season_end;
    private int room_id;
    private String room_name;
    private Room room;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;

    public Price() {
    }

    public Price(int id, int hotel_id, int season_id, int room_id, String age, double price, int pension) {
        this.roomManager = new RoomManager();
        this.id = id;
        this.hotel_id = hotel_id;
        this.season_id = season_id;
        this.room_id = room_id;
        this.room_name = roomManager.getById(this.room_id).getType();
        this.age = age;
        this.price = price;
        this.pensionId = pension;
        this.hotel = hotelManager.getById(hotel_id);
        this.season = seasonManager.getById(season_id);
        this.room = this.roomManager.getById(room_id);
        this.season_start = String.valueOf(this.season.getStartDate());
        this.season_end = String.valueOf(this.season.getFinishDate());
    }

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSeason_start() {
        return season_start;
    }

    public void setSeason_start(String season_start) {
        this.season_start = season_start;
    }

    public String getSeason_end() {
        return season_end;
    }

    public void setSeason_end(String season_end) {
        this.season_end = season_end;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomManager getRoomManager() {
        return roomManager;
    }

    public void setRoomManager(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    public HotelManager getHotelManager() {
        return hotelManager;
    }

    public void setHotelManager(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
    }

    public SeasonManager getSeasonManager() {
        return seasonManager;
    }

    public void setSeasonManager(SeasonManager seasonManager) {
        this.seasonManager = seasonManager;
    }
}
