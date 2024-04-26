package dao;

import core.Db;
import entity.Price;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;


public class PriceDao {

    private Connection con;

    public PriceDao() {

        this.con = Db.getInstance();

    }

    public Price getById(int id) {
        Price obj = null;
        String query = "SELECT * FROM public.price WHERE price_id = ?";
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

    // Retrieve all prices from the database
    public ArrayList<Price> findAll() {
        String sql = "SELECT * FROM public.price ORDER BY hotel_id ASC";
        return this.selectByQuery(sql);
    }




    // Select prices based on a custom query
    public ArrayList<Price> selectByQuery(String query) {
        ArrayList<Price> pricesList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                pricesList.add(this.match(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pricesList;
    }

    // Save a new price to the database
    public boolean save(Price price) {
        String query = "INSERT INTO public.price" +
                "(" +
                "hotel_id," +
                "season_id," +
                "pension_id," +
                "room_id," +
                "age," +
                "price" +
                ")" +
                "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, price.getHotel_id());
            pr.setInt(2, price.getSeason_id());
            pr.setInt(3, price.getPensionId());
            pr.setInt(4, price.getRoom_id());
            pr.setString(5, price.getAge());
            pr.setDouble(6, price.getPrice());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    // Update an existing price in the database
    public boolean update(Price price) {
        String query = "UPDATE public.price SET " +
                "hotel_id = ?," +
                "season_id = ?," +
                "pension_id = ?," +
                "room_id = ?," +
                "age = ?," +
                "price = ? " +
                "WHERE price_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, price.getHotel_id());
            pr.setInt(2, price.getSeason_id());
            pr.setInt(3, price.getPensionId());
            pr.setInt(4, price.getRoom_id());
            pr.setString(5, price.getAge());
            pr.setDouble(6, price.getPrice());
            pr.setInt(7, price.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    // Delete a price from the database
    public boolean delete(int id) {
        String query = "DELETE FROM public.price WHERE price_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    
    public Price match(ResultSet rs) throws SQLException {
        
        Price price = new Price();
        price.setId(rs.getInt("price_id"));
        price.setHotel_id(rs.getInt("hotel_id"));
        price.setSeason_id(rs.getInt("season_id"));
        price.setPensionId(rs.getInt("pension_id"));
        price.setRoom_id(rs.getInt("room_id"));
        price.setAge(rs.getString("age"));
        price.setPrice(rs.getDouble("price"));
        return price;
    }



    public  boolean deleteHotel(int hotel_id) {
        String query = "DELETE FROM price WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  boolean deletePeriod(int season_id) {
        String query = "DELETE FROM price WHERE season_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,season_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Price> findByRoomId(int roomId) {
        String query = "SELECT * FROM public.price WHERE room_id = ?";
        ArrayList<Price> price = new ArrayList<>();
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, roomId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                price.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
}
