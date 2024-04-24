package dao;

import core.Db;
import entity.Pension;
import entity.Room;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class RoomDao {

    // Connection to the database
    private  Connection con;
    // Instances of other DAOs to perform operations related to rooms
    private  HotelDao hotelDao;
    private SeasonDao seasonDao;
    private PensionDao pensionDao;

    // Constructor to initialize the RoomDao with a database connection and instances of other DAOs
    public RoomDao() {
        this.con = Db.getInstance();
        hotelDao = new HotelDao();
        seasonDao = new SeasonDao();
        pensionDao = new PensionDao();
    }

    // Retrieve a room by its ID from the database
    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
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

    // Retrieve all rooms from the database
    public ArrayList<Room> findAll() {
        String sql = "SELECT * FROM public.room ORDER BY hotel_id ASC";
        return this.selectByQuery(sql);
    }

    // Select rooms based on a custom query
    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomsList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomsList.add(this.match(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomsList;
    }

    // Save a new room to the database
    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_capacity," +
                "square_meter," +
                "television," +
                "minibar," +
                "game_console," +
                "cash_box " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getPensionId());
            pr.setInt(3, room.getSeasonId());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdultPrice());
            pr.setDouble(7, room.getChildPrice());
            pr.setInt(8, room.getBedCapacity());
            pr.setInt(9, room.getSquareMeter());
            pr.setBoolean(10, room.isTv());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isCashBox());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // Update an existing room in the database
    public boolean update(Room room) {
        String query = "UPDATE public.room SET " +
                "hotel_id = ?," +
                "pension_id = ?," +
                "season_id = ?," +
                "type = ?," +
                "stock = ?," +
                "adult_price = ?," +
                "child_price = ?," +
                "bed_capacity = ?," +
                "square_meter = ?," +
                "television = ?," +
                "minibar = ?," +
                "game_console = ?," +
                "cash_box = ? " +
                "WHERE room_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getPensionId());
            pr.setInt(3, room.getSeasonId());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdultPrice());
            pr.setDouble(7, room.getChildPrice());
            pr.setInt(8, room.getBedCapacity());
            pr.setInt(9, room.getSquareMeter());
            pr.setBoolean(10, room.isTv());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGameConsole());
            pr.setBoolean(13, room.isCashBox());
            pr.setInt(14, room.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // Delete a room from the database
    public boolean delete(int id) {
        String query = "DELETE FROM public.room WHERE room_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Map the ResultSet to a Room object
    public Room match(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        room.setHotelId(rs.getInt("hotel_id"));
        room.setPensionId(rs.getInt("pension_id"));
        room.setSeasonId(rs.getInt("season_id"));
        room.setType(rs.getString("type"));
        room.setStock(rs.getInt("stock"));
        room.setAdultPrice(rs.getDouble("adult_price"));
        room.setChildPrice(rs.getDouble("child_price"));
        room.setBedCapacity(rs.getInt("bed_capacity"));
        room.setSquareMeter(rs.getInt("square_meter"));
        room.setTv(rs.getBoolean("television"));
        room.setMinibar(rs.getBoolean("minibar"));
        room.setGameConsole(rs.getBoolean("game_console"));
        room.setCashBox(rs.getBoolean("cash_box"));
        return room;
    }
}
