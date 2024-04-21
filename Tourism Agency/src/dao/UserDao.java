package dao;

import core.Db;
import entity.Role;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private final Connection con;


    public UserDao() {
        this.con = Db.getInstance();
    }

    // User döndüren metod

    public ArrayList<User> findAll(){
        ArrayList<User> userArrayList = new ArrayList<>();
        //order by ile daha sonra güncellenenleri sona atmayı engelledik
        String query = "SELECT * FROM public.user ORDER BY user_id ASC";
        try {
            ResultSet resultSet = this.con.createStatement().executeQuery(query);
            //Arraylist yaptığımız için while kullandık
            while (resultSet.next()){
                userArrayList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userArrayList;
    }
    public User findByLogin(String username, String password) {
        User obj = null;
        //postgresql kullandığımız için schemalar içinde tablolar var varsayılan olarak publiğin içinde geliyor
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);

            // ? lerini parametreden gelen username ve password ile değiştirme
            pr.setString(1,username);
            pr.setString(2,password);
            // bu işlem varsa resultsetw döndür
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()){
                obj = new User();
                //obj nin değerlerini veritabanındati değerlerle resulr set ile setliyoruz
                obj = this.match(resultSet);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public User match(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("user_name"));
        user.setPassword(resultSet.getString("user_password"));
        user.setRole(Role.valueOf(resultSet.getString("user_role")));
        return user;
    }

    //TODO save boolean çünkü ya başarılıdır ya başarısızdır

    public boolean save(User user) {
        String query = "INSERT INTO public.user (user_name, user_password, user_role) VALUES (?, ?, ?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());

            //boolean metod olduğu için retun bu şekilde en az bir satır çalışırsa değer 0 dan büyük olur (TRUE)
            return pr.executeUpdate() != -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User user){
        String query="UPDATE public.user SET user_name = ?, user_password = ?, user_role = ? WHERE user_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query); {
                pr.setString(1,user.getUsername());
                pr.setString(2,user.getPassword());
                pr.setString(3,user.getRole().toString());
                pr.setInt(4,user.getId());

                return pr.executeUpdate() != -1;
            }
        }catch (SQLException e){
            e.printStackTrace();

            return true;
        }

    }

    public User getById(int id){
        User obj = null;
        String query ="SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs =  pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;

    }
}
