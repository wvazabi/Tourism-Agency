package view;

import javax.swing.*;

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

    public EmployeeView() {
        this.add(container);
        this.guiInitilaze(1250,750,"Employee Management");

    }
}
