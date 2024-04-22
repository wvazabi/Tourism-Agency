package entity;

public class Hotel {

    private int hotelId;
    private String hotelName;
    private String hotelAddress;
    private String hotelMail;
    private String hotelPhone;
    private String hotelStar;
    private boolean hotelCarParking;
    private boolean hotelWifi;
    private boolean hotelPool;
    private boolean hotelFitness;
    private boolean hotelConcierge;
    private boolean hotelSpa;
    private boolean hotelRoomService;

    public Hotel() {
    }

    public Hotel(String hotelName, String hotelAddress, String hotelMail, String hotelPhone, String hotelStar, boolean hotelWifi, boolean hotelPool, boolean hotelFitness, boolean hotelConcierge, boolean hotelSpa, boolean hotelRoomService,boolean hotelCarParking) {
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelMail = hotelMail;
        this.hotelPhone = hotelPhone;
        this.hotelStar = hotelStar;
        this.hotelWifi = hotelWifi;
        this.hotelPool = hotelPool;
        this.hotelFitness = hotelFitness;
        this.hotelCarParking = hotelCarParking;
        this.hotelConcierge = hotelConcierge;
        this.hotelSpa = hotelSpa;
        this.hotelRoomService = hotelRoomService;
    }

    public int getHotelId() {
        return hotelId;
    }

//    public void setHotelId(int hotelId) {
//        this.hotelId = hotelId;
//    }


    public boolean isHotelCarParking() {
        return hotelCarParking;
    }

    public void setHotelCarParking(boolean hotelCarParking) {
        this.hotelCarParking = hotelCarParking;
    }

    public boolean isHotelWifi() {
        return hotelWifi;
    }

    public boolean isHotelPool() {
        return hotelPool;
    }

    public boolean isHotelFitness() {
        return hotelFitness;
    }

    public boolean isHotelConcierge() {
        return hotelConcierge;
    }

    public boolean isHotelSpa() {
        return hotelSpa;
    }

    public boolean isHotelRoomService() {
        return hotelRoomService;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelMail() {
        return hotelMail;
    }

    public void setHotelMail(String hotelMail) {
        this.hotelMail = hotelMail;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    public boolean getHotelWifi() {
        return hotelWifi;
    }

    public void setHotelWifi(boolean hotelWifi) {
        this.hotelWifi = hotelWifi;
    }

    public boolean getHotelPool() {
        return hotelPool;
    }

    public void setHotelPool(boolean hotelPool) {
        this.hotelPool = hotelPool;
    }

    public boolean getHotelFitness() {
        return hotelFitness;
    }

    public void setHotelFitness(boolean hotelFitness) {
        this.hotelFitness = hotelFitness;
    }

    public boolean getHotelConcierge() {
        return hotelConcierge;
    }

    public void setHotelConcierge(boolean hotelConcierge) {
        this.hotelConcierge = hotelConcierge;
    }

    public boolean getHotelSpa() {
        return hotelSpa;
    }

    public void setHotelSpa(boolean hotelSpa) {
        this.hotelSpa = hotelSpa;
    }

    public boolean getHotelRoomService() {
        return hotelRoomService;
    }

    public void setHotelRoomService(boolean hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
    }
}
