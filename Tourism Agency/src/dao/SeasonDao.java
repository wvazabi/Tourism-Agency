package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

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
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
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
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            pr.setInt(4,  season.getId());
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
        season.setStartDate(rs.getDate("start_date").toLocalDate());
        season.setFinishDate(rs.getDate("finish_date").toLocalDate());

        return season;
    }
}
