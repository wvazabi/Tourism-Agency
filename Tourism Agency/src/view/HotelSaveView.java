package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.Role;
import entity.Star;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelSaveView extends Layout {
    private JPanel container;
    private JPanel pnl_add_delete_hotel;
    private JTextField fld_add_hotel_name;
    private JTextField fld_add_hotel_address;
    private JTextField fld_add_hotel_mail;
    private JTextField fld_add_hotel_phone;
    private JComboBox cmd_add_hotel_star;
    private JRadioButton rd_hotel_car_park;
    private JRadioButton rd_hotel_wifi;
    private JRadioButton rd_hotel_pool;
    private JRadioButton rd_hotel_fitness;
    private JRadioButton rd_hotel_concierge;
    private JRadioButton rd_hotel_spa;
    private JRadioButton rd_hotel_room_service;
    private JButton btn_hotel_save;
    private Hotel hotel;
    private DefaultComboBoxModel cmbModel;
    private HotelManager hotelManager;


    public HotelSaveView(Hotel hotel) {
        this.add(container);
        this.hotel = hotel;
        this.guiInitilaze(500, 500, "Add Hotel View");
        hotelManager = new HotelManager();

        Star[] values = Star.values();
        cmbModel = new DefaultComboBoxModel<>(values);
        cmd_add_hotel_star.setModel(cmbModel);


        if (hotel != null) {
            fld_add_hotel_name.setText(hotel.getHotelName());
            fld_add_hotel_address.setText(hotel.getHotelAddress());
            fld_add_hotel_mail.setText(hotel.getHotelMail());
            fld_add_hotel_phone.setText(hotel.getHotelPhone());
            cmd_add_hotel_star.setSelectedItem(hotel.getHotelStar());
            rd_hotel_car_park.setSelected(hotel.getHotelCarPark());
            rd_hotel_wifi.setSelected(hotel.getHotelWifi());
            rd_hotel_pool.setSelected(hotel.getHotelPool());
            rd_hotel_fitness.setSelected(hotel.getHotelFitness());
            rd_hotel_concierge.setSelected(hotel.getHotelConcierge());
            rd_hotel_spa.setSelected(hotel.getHotelSpa());
            rd_hotel_room_service.setSelected(hotel.getHotelRoomService());
        }


        btn_hotel_save.addActionListener(e -> {

            // Kaydedilecek alanların boş olup olmadığını kontrol ediyor
            JTextField[] textFields = new JTextField[]{fld_add_hotel_name, fld_add_hotel_mail,fld_add_hotel_address,fld_add_hotel_phone};

            if (Helper.isFieldListEmpty(textFields) || isAnyEmpty()) {
                Helper.showMsg("fill");
            } else {

                boolean result = true;

                if (this.hotel == null) {
                    // user boş ise save işlemi
                    this.hotel = new Hotel(fld_add_hotel_name.getText(),
                            fld_add_hotel_address.getText(),
                            fld_add_hotel_mail.getText(),
                            fld_add_hotel_phone.getText(),
                            cmd_add_hotel_star.getSelectedItem().toString(),
                            rd_hotel_car_park.isSelected(),
                            rd_hotel_wifi.isSelected(),
                            rd_hotel_pool.isSelected(),
                            rd_hotel_fitness.isSelected(),
                            rd_hotel_concierge.isSelected(),
                            rd_hotel_spa.isSelected(),
                            rd_hotel_room_service.isSelected());
                    // save metodu return boolean dönüyor
                    result = this.hotelManager.save(this.hotel);
                }
//                else {
//                    // update işlemi user boş değilse
//                    this.user.setUsername(fld_username.getText());
//                    this.user.setPassword(fld_password.getText());
//                    this.user.setRole((Role) cmb_role.getSelectedItem());
//                    result = this.userManager.update(this.user);
//
//                }
                if (result == true) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }

            }

        });
    }

    private boolean isAnyEmpty() {
        boolean isEmpty = false;

        if (cmd_add_hotel_star.getSelectedItem() == null) {
            isEmpty = true;
            // Boş olduğunda bir işlem yapabilirsiniz, örneğin:
            // JOptionPane.showMessageDialog(null, "Hotel star is empty!");
        }

        if (!rd_hotel_car_park.isSelected() && !rd_hotel_wifi.isSelected() &&
                !rd_hotel_pool.isSelected() && !rd_hotel_fitness.isSelected() &&
                !rd_hotel_concierge.isSelected() && !rd_hotel_spa.isSelected() &&
                !rd_hotel_room_service.isSelected()) {
            isEmpty = true;
            // Boş olduğunda bir işlem yapabilirsiniz, örneğin:
            // JOptionPane.showMessageDialog(null, "At least one feature should be selected!");
        }

        return isEmpty;
    }


}
