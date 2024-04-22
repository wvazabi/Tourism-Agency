package dao;

import core.Db;
import entity.Hotel;
import entity.User;

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


    // User döndüren metod

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();

        //order by ile daha sonra güncellenenleri sona atmayı engelledik
        String query = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";
        try {
            ResultSet resultSet = this.con.createStatement().executeQuery(query);

            //Arraylist yaptığımız için while kullandık
            while (resultSet.next()) {
                hotelArrayList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Hotel Array List: " + hotelArrayList.get(0).getHotelRoomService());
        return hotelArrayList;
    }
//TODO Hotel Search
//    public Hotel findByLogin(String username, String password) {
//        Hotel obj = null;
//        //postgresql kullandığımız için schemalar içinde tablolar var varsayılan olarak publiğin içinde geliyor
//        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
//        try {
//            PreparedStatement pr = this.con.prepareStatement(query);
//
//            // ? lerini parametreden gelen username ve password ile değiştirme
//            pr.setString(1, username);
//            pr.setString(2, password);
//            // bu işlem varsa resultsetw döndür
//            ResultSet resultSet = pr.executeQuery();
//            if (resultSet.next()) {
//                obj = new User();
//                //obj nin değerlerini veritabanındati değerlerle resulr set ile setliyoruz
//                obj = this.match(resultSet);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return obj;
//    }

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

    //TODO save boolean çünkü ya başarılıdır ya başarısızdır

//    public boolean save(User user) {
//        String query = "INSERT INTO public.user (user_name, user_password, user_role) VALUES (?, ?, ?)";
//
//        try {
//            PreparedStatement pr = this.con.prepareStatement(query);
//            pr.setString(1, user.getUsername());
//            pr.setString(2, user.getPassword());
//            pr.setString(3, user.getRole().toString());
//
//            //boolean metod olduğu için retun bu şekilde en az bir satır çalışırsa değer 0 dan büyük olur (TRUE)
//            return pr.executeUpdate() != -1;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }

    //TODO update
//    public boolean update(User user) {
//        String query = "UPDATE public.user SET user_name = ?, user_password = ?, user_role = ? WHERE user_id = ?";
//        try {
//            PreparedStatement pr = this.con.prepareStatement(query);
//            {
//                pr.setString(1, user.getUsername());
//                pr.setString(2, user.getPassword());
//                pr.setString(3, user.getRole().toString());
//                pr.setInt(4, user.getId());
//
//                return pr.executeUpdate() != -1;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//            return true;
//        }
//
//    }
//TODO delete
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
}
