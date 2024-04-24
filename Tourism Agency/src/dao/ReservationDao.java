package dao;

import core.Db;
import entity.Reservation;
import entity.Reservation;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDao {



    private Connection con;
    private  HotelDao hotelDao;
    private SeasonDao seasonDao;
    private PensionDao pensionDao;
    private RoomDao roomDao;


    public ReservationDao() {
        this.con = Db.getInstance();
        roomDao = new RoomDao();
        hotelDao = new HotelDao();
        seasonDao = new SeasonDao();
        pensionDao = new PensionDao();
    }

    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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



    public ArrayList<Reservation> findAll() {
        String sql = "SELECT * FROM public.reservation ORDER BY room_id ASC";
        return this.selectByQuery(sql);

    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservationsList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationsList.add(this.match(rs));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return reservationsList;
    }

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation" +
                "(" +
                "room_id," +
                "check_in_date," +
                "check_out_date," +
                "total_price," +
                "guest_name," +
                "guest_citizen_id," +
                "guest_mail," +
                "guest_phone," +
                "guest_count," +
                "number_of_night " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pr.setInt(1, reservation.getRoomId());
            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));
            pr.setDouble(4, reservation.getTotalPrice());
            pr.setString(5, reservation.getGuestName());
            pr.setLong(6, reservation.getGuestCitizenId());
            pr.setString(7, reservation.getGuestMail());
            pr.setString(8, reservation.getGuestPhone());
            pr.setInt(9, reservation.getGuestCount());
            pr.setInt(10, reservation.getNumberOfNights());

            int affectedRows = pr.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating reservation failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "room_id = ?," +
                "check_in_date = ?," +
                "check_out_date = ?," +
                "total_price = ?," +
                "guest_name = ?," +
                "guest_citizen_id = ?," +
                "guest_mail = ?," +
                "guest_phone = ?," +
                "guest_count = ?," +
                "number_of_night = ? " +
                "WHERE reservation_id = ?";

        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, reservation.getRoomId());
            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));
            pr.setDouble(4, reservation.getTotalPrice());
            pr.setString(5, reservation.getGuestName());
            pr.setLong(6, reservation.getGuestCitizenId());
            pr.setString(7, reservation.getGuestMail());
            pr.setString(8, reservation.getGuestPhone());
            pr.setInt(9, reservation.getGuestCount());
            pr.setInt(10, reservation.getNumberOfNights());
            pr.setInt(11, reservation.getId());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }





    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;

    }


    public Reservation match(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("reservation_id"));
        reservation.setRoomId(rs.getInt("room_id"));
        reservation.setCheckInDate(rs.getDate("check_in_date").toLocalDate());
        reservation.setCheckOutDate(rs.getDate("check_out_date").toLocalDate());
        reservation.setTotalPrice(rs.getInt("total_price"));
        reservation.setGuestName(rs.getString("guest_name"));
        reservation.setGuestCitizenId(Integer.parseInt(rs.getString("guest_citizen_id")));
        reservation.setGuestMail(rs.getString("guest_mail"));
        reservation.setGuestPhone(rs.getString("guest_phone"));
        reservation.setGuestCount(rs.getInt("guest_count"));
        reservation.setNumberOfNights(rs.getInt("number_of_night"));

        return reservation;
    }

}
