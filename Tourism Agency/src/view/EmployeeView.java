package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.Role;
import entity.Star;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JButton btn_srch_hotel;
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

    private DefaultComboBoxModel<Star> cmbModel;


    private Hotel hotel;
    private User user;

    //Tablolar üzerinde işlem yapabilmemiz için table modellere ihtiyacımız var
    private DefaultTableModel tmdl_hotel;

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

        loadHotelComponent();
        loadHotelTable();



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

        //Tabloyu  seçilebilir yapar
        this.tableRowSelect(tbl_hotel_list);
        tmdl_hotel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // Sadece belirli bir sütunun düzenlenemez olmasını sağlar
                return column != 1; // 1. sütun (sütun indeksi 0) dışındaki tüm sütunlar düzenlenebilir olacak
            }
        };


        // Seçtiğimiz satırdaki id'yi fld_user_id kutucuğuna getirir.
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_hotel_id = tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString();
                fld_dlt_hotel_id.setText(select_hotel_id);
            } catch (Exception ignored){
            }
        });




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

        //cmb_star

        Star[] values = Star.values();
        cmbModel = new DefaultComboBoxModel<>(values);
        cmb_srch_hotel_star.setModel(cmbModel);

        btn_srch_hotel.addActionListener(e -> {
            String name = fld_srch_hotel_name.getText();
            String address = fld_srch_hotel_adress.getText();
            String star = cmb_srch_hotel_star.getSelectedItem().toString();
//            String query = Hotel.searchQuery(name, city, region, star);
//            loadSearchHotelModel(Hotel.searchHotelList(query));
        });



    }
}
