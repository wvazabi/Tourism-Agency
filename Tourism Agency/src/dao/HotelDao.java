package dao;

import core.Db;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {


    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }


    // Method to retrieve all hotels from the database
    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();

        // Retrieving hotels from the database and ordering them by hotel_id
        String query = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";
        try {
            ResultSet resultSet = this.con.createStatement().executeQuery(query);

            // Adding each hotel to the list
            while (resultSet.next()) {
                hotelArrayList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hotelArrayList;
    }

    // Method to retrieve hotels based on a custom query
    public ArrayList<Hotel> findAll(String query) {
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = this.con.createStatement().executeQuery(query);

            // Adding each hotel to the list
            while (resultSet.next()) {
                hotelArrayList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return hotelArrayList;
    }

    public Hotel match(ResultSet resultSet) throws SQLException {
        Hotel hotel = new Hotel();

        hotel.setHotelId(resultSet.getInt("hotel_id"));
        hotel.setHotelName(resultSet.getString("hotel_name"));
        hotel.setHotelAddress(resultSet.getString("hotel_address"));
        hotel.setHotelMail(resultSet.getString("hotel_mail"));
        hotel.setHotelPhone(resultSet.getString("hotel_phone"));
        hotel.setHotelStar(resultSet.getString("hotel_star"));
        hotel.setHotelCarParking(resultSet.getBoolean("hotel_car_park"));
        hotel.setHotelWifi(resultSet.getBoolean("hotel_wifi"));
        hotel.setHotelPool(resultSet.getBoolean("hotel_pool"));
        hotel.setHotelFitness(resultSet.getBoolean("hotel_fitness"));
        hotel.setHotelConcierge(resultSet.getBoolean("hotel_concierge"));
        hotel.setHotelSpa(resultSet.getBoolean("hotel_spa"));
        hotel.setHotelRoomService(resultSet.getBoolean("hotel_room_service"));
        return hotel;
    }

    // Method to save a new hotel to the database
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel (hotel_name,hotel_address,hotel_mail,hotel_phone,hotel_star,hotel_car_park,hotel_wifi,hotel_pool,hotel_fitness,hotel_concierge,hotel_spa,hotel_room_service) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getHotelName());
            pr.setString(2, hotel.getHotelAddress());
            pr.setString(3, hotel.getHotelMail());
            pr.setString(4, hotel.getHotelPhone());
            pr.setString(5, hotel.getHotelStar());
            pr.setBoolean(6, hotel.getHotelCarPark());
            pr.setBoolean(7, hotel.getHotelWifi());
            pr.setBoolean(8, hotel.getHotelPool());
            pr.setBoolean(9, hotel.getHotelFitness());
            pr.setBoolean(10, hotel.getHotelConcierge());
            pr.setBoolean(11, hotel.getHotelSpa());
            pr.setBoolean(12, hotel.getHotelRoomService());

            // Executing the query and returning true if successful
            return pr.executeUpdate() != -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // Method to update an existing hotel in the database
    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET " +
                "hotel_name = ?, " +
                "hotel_address = ?, " +
                "hotel_mail = ?, " +
                "hotel_phone = ?, " +
                "hotel_star = ?, " +
                "hotel_car_park = ?, " +
                "hotel_wifi = ?, " +
                "hotel_pool = ?, " +
                "hotel_fitness = ?, " +
                "hotel_concierge = ?, " +
                "hotel_spa = ?, " +
                "hotel_room_service = ? " +
                "WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getHotelName());
            pr.setString(2, hotel.getHotelAddress());
            pr.setString(3, hotel.getHotelMail());
            pr.setString(4, hotel.getHotelPhone());
            pr.setString(5, hotel.getHotelStar());
            pr.setBoolean(6, hotel.getHotelCarPark());
            pr.setBoolean(7, hotel.getHotelWifi());
            pr.setBoolean(8, hotel.getHotelPool());
            pr.setBoolean(9, hotel.getHotelFitness());
            pr.setBoolean(10, hotel.getHotelConcierge());
            pr.setBoolean(11, hotel.getHotelSpa());
            pr.setBoolean(12, hotel.getHotelRoomService());
            pr.setInt(13, hotel.getHotelId());

            // Executing the query and returning true if successful
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if the update operation fails
        }


    }

    // Method to delete a hotel from the database
    public boolean delete(int id) {
        String query = "DELETE FROM public.hotel WHERE hotel_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Method to retrieve a hotel by its ID from the database
    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;

    }

    // Method to search for hotels by a custom query
    public String searchHotelByQuery(String name, String address, String star) {
        String query = "SELECT * FROM public.hotel WHERE hotel_name LIKE '%{{name}}%' AND hotel_star LIKE '%{{star}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{address}}", address);
        query = query.replace("{{star}}", star);
        if (!address.isEmpty()) {
            query += " AND hotel_address='{{address}}'";
            query = query.replace("{{address}}", address);
        }

        return query;
    }

}
