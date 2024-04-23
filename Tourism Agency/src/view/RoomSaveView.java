package view;

import javax.swing.*;

public class RoomSaveView extends Layout {
    private JPanel container;
    private JComboBox cmb_room_hotel_name;
    private JComboBox cmb_room_season;
    private JComboBox cmb_room_type;
    private JTextField fld_room_stock;
    private JTextField fld_bed_number;
    private JRadioButton rd_room_tv;
    private JRadioButton rd_room_minibar;
    private JRadioButton rd_room_game_cnsl;
    private JRadioButton rd_room_cash_box;
    private JButton btn_save_button;
    private JTextField fld_room_adult_price;
    private JTextField fld_room_child_price;
    private JLabel lbl_room_child_price;
    private JLabel lbl_room_adult_price;
    private JLabel lbl_bed_number;
    private JLabel lbl_room_stock;

    public RoomSaveView() {
        this.add(container);
        this.guiInitilaze(400,600,"Room Add/Update Screen");
    }
}
