package dao;

import core.Db;
import entity.Pension;
import entity.Pension;

import java.sql.*;
import java.util.ArrayList;

public class PensionDao {

    private final Connection con;
    private final HotelDao hotelDao = new HotelDao();


    public PensionDao() {
        this.con = Db.getInstance();
    }

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
        if (obj == null) {
            // Belirli bir kimlik numarasına sahip bir pansiyon bulunamadığında, varsayılan bir pansiyon nesnesi veya değeri döndürün
            obj = new Pension(); // Varsayılan bir Pension nesnesi oluşturabilirsiniz veya null yerine başka bir değer döndürebilirsiniz
            obj.setHotelId(3);
            obj.setPensionType("null");
        }
        return obj;

    }
    

    public ArrayList<Pension> findAll() {
        String sql = "SELECT * FROM public.pension ORDER BY hotel_id ASC";
        return this.selectByQuery(sql);

    }

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


    public Pension match(ResultSet rs) throws SQLException {
        Pension pension = new Pension();
        pension.setPensionId(rs.getInt("pension_id"));
        pension.setHotelId(rs.getInt("hotel_id"));
        pension.setPensionType(rs.getString("pension_type"));

        return pension;
    }

}
