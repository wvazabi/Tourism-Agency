package dao;

import core.Db;
import entity.Pension;
import java.sql.*;
import java.util.ArrayList;

public class PensionDao {

    private final Connection con;

    public PensionDao() {
        this.con = Db.getInstance();
    }

    // Retrieve a pension by its ID from the database
    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
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
        // If no pension with the specified ID is found, return a default pension object or value
        if (obj == null) {
            obj = new Pension(); // You can create a default Pension object or return another value instead of null
            obj.setHotelId(3);
            obj.setPensionType("null");
        }
        return obj;
    }

    // Retrieve all pensions from the database
    public ArrayList<Pension> findAll() {
        String sql = "SELECT * FROM public.pension ORDER BY hotel_id ASC";
        return this.selectByQuery(sql);
    }

    // Select pensions based on a custom query
    public ArrayList<Pension> selectByQuery(String query) {
        ArrayList<Pension> pensionsList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionsList.add(this.match(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pensionsList;
    }

    // Save a new pension to the database
    public boolean save(Pension pension) {
        String query = "INSERT INTO public.pension" +
                "(" +
                "hotel_id," +
                "pension_type " +
                ")" +
                "VALUES (?,?)";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, pension.getHotelId());
            pr.setString(2, pension.getPensionType());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Update an existing pension in the database
    public boolean update(Pension pension) {
        String query = "UPDATE public.pension SET " +
                "hotel_id = ?," +
                "pension_type = ? " +
                "WHERE Pension_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, pension.getHotelId());
            pr.setString(2, pension.getPensionType());
            pr.setInt(3,  pension.getPensionId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Delete a pension from the database
    public boolean delete(int id) {
        String query = "DELETE FROM public.pension WHERE pension_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Map the ResultSet to a Pension object
    public Pension match(ResultSet rs) throws SQLException {
        Pension pension = new Pension();
        pension.setPensionId(rs.getInt("pension_id"));
        pension.setHotelId(rs.getInt("hotel_id"));
        pension.setPensionType(rs.getString("pension_type"));
        return pension;
    }

    // Find pensions by hotel ID
    public ArrayList<Pension> findByHotelId(int hotelId) {
        String query = "SELECT * FROM public.pension WHERE hotel_id = ?";
        ArrayList<Pension> pension = new ArrayList<>();
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                pension.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pension;
    }
}
