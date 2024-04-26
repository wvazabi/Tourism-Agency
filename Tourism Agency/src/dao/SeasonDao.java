package dao;

import core.Db;
import core.Helper;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class SeasonDao {

    private final Connection con;
    private final HotelDao hotelDao = new HotelDao();


    public SeasonDao() {
        this.con = Db.getInstance();
    }

    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?";
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
            obj = new Season(); // Varsayılan bir Pension nesnesi oluşturabilirsiniz veya null yerine başka bir değer döndürebilirsiniz
            obj.setHotelId(3);
            obj.setStartDate("tarih bulunamadı");
            obj.setFinishDate("Tarih bulunamadı");

        }
        return obj;

    }


    public ArrayList<Season> getByListSeasonId(int hotelId) {

        String sql = "SELECT * FROM public.season WHERE hotel_id = " + hotelId;
        return this.selectByQuery(sql);

    }

    public ArrayList<Season> findAll() {
        String sql = "SELECT * FROM public.season ORDER BY hotel_id ASC";
        return this.selectByQuery(sql);

    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonsList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonsList.add(this.match(rs));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seasonsList;
    }

    public boolean save(Season season) {

        String query = "INSERT INTO public.season" +
                "(" +
                "hotel_id," +
                "start_date," +
                "finish_date " +
                ")" +
                "VALUES (?,?,?)";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setString(2, season.getStartDate());
            pr.setString(3, season.getFinishDate());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public boolean update(Season season) {
        String query = "UPDATE public.season SET " +
                "hotel_id = ?," +
                "start_date = ?," +
                "finish_date = ? " +
                "WHERE season_id = ?";


        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setString(2, season.getStartDate());
            pr.setString(3, season.getFinishDate());
            pr.setInt(4, season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.season WHERE season_id =?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }


    public Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setId(rs.getInt("season_id"));
        season.setHotelId(rs.getInt("hotel_id"));
        season.setStartDate(rs.getString("start_date"));
        season.setFinishDate(rs.getString("finish_date"));

        return season;
    }

    public ArrayList<Season> findByHotelId(int hotelId) {
        String query = "SELECT * FROM public.season WHERE hotel_id = ?";
        ArrayList<Season> seasons = new ArrayList<>();
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                seasons.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasons;
    }

}
