package view;

import business.HotelManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JTabbedPane tpnl_employee;
    private JPanel pnl_hotel_list;
    private JPanel pnl_period_list;
    private JPanel pnl_room_list;
    private JPanel pnl_price_list;
    private JPanel pnl_search_room;
    private JPanel pnl_reservation;
    private JPanel pnl_hotel_srch;
    private JTextField fld_srch_hotel_name;
    private JTextField fld_srch_hotel_adress;
    private JComboBox cmb_srch_hotel_star;
    private JButton hotelSearchButton;
    private JTextField fld_add_hotel_name;
    private JTextField fld_add_hotel_address;
    private JTextField fld_add_hotel_mail;
    private JTextField fld_add_hotel_phone;
    private JComboBox cmd_add_hotel_star;
    private JButton btn_add_feature_hotel;
    private JTextField fld_dlt_hotel_id;
    private JButton btn_delete_hotel;
    private JPanel pnl_add_delete_hotel;
    private JTable tbl_hotel_list;

    private Hotel hotel;
    private User user;

    //Tablolar üzerinde işlem yapabilmemiz için table modellere ihtiyacımız var
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();

    private JPopupMenu hotelMenu;

    private HotelManager hotelManager;

    public EmployeeView(User user) {
        this.add(container);
        this.guiInitilaze(1500,750,"Employee Management");
        this.hotelManager = new HotelManager();
        this.user = user;

        this.lbl_welcome.setText("Welcome employee user: " + user.getUsername().toUpperCase());
        btn_logout.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView();
        });

        loadHotelTable();
        loadHotelComponent();


    }

    public void loadHotelTable() {

        // tablonun kolonlarını oluşturuyoruz Java da bütün veri tipleri objden üretildiği için obj kulllanıyoruz
        Object[] col_hotel = {"Hotel ID", "Hotel Name", "Hotel Address", "Hotel Mail", "Hotel Phone","Hotel Star","Hotel Car Park","Hotel Wifi","Hotel Pool","Hotel Fitness", "Hotel Concierge", "Hotel Spa", "Hotel Room Service"};
        ArrayList<Hotel> hotelArrayList = hotelManager.findAll();


        if (hotelArrayList != null) { // Null kontrolü yapılıyor
            ArrayList<Object[]> hotelObjectList = this.hotelManager.getForTable(col_hotel.length, hotelArrayList); // Tablo için gerekli listeyi oluştur
            this.createTable(this.tmdl_hotel, this.tbl_hotel_list, col_hotel, hotelObjectList);
        } else {
            // Hata mesajı veya başka bir işlem yapılabilir
            Helper.showMsg("error");
        }

    }

    public void loadHotelComponent() {
      

        btn_delete_hotel.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_dlt_hotel_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int hotel_id = Integer.parseInt(fld_dlt_hotel_id.getText());
                    if(this.hotelManager.delete(hotel_id)) {
                        Helper.showMsg("done");
                        loadHotelTable();
                        fld_dlt_hotel_id.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }
        });



    }
}
