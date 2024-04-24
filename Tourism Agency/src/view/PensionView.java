package view;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class PensionView extends Layout {
    private JPanel container;
    private JComboBox<ComboItem> cmd_hotel_name;
    private JTextField fld_pension_type;
    private JButton btn_save;
    private JLabel lbl_hotel_name;
    private JLabel lbl_pension_type;
    private ArrayList<Hotel> hotels;
    private DefaultComboBoxModel cmbModel;

    private Pension pension;
    private PensionManager pensionManager;
    private HotelManager hotelManager;


    public PensionView(Pension pension) {
        this.add(container);
        this.pension = pension;
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        this.guiInitilaze(300, 400, "Pension Create / Edit");

        //populateHotelComboBox();

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmd_hotel_name.addItem(new ComboItem(hotel.getHotelId(), hotel.getHotelName()));
        }

        if (this.pension.getPensionId() != 0) {
            this.fld_pension_type.setText(pension.getPensionType());


            ComboItem defaultHotel = new ComboItem(this.pension.getHotelId(), this.hotelManager.getById(pension.getHotelId()).getHotelName());
            this.cmd_hotel_name.getModel().setSelectedItem(defaultHotel);
        }


        btn_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_pension_type})) {
                Helper.showMsg("fill", "");
            } else {
                boolean result = false;
                ComboItem selectedHotel = (ComboItem) cmd_hotel_name.getSelectedItem();

                this.pension.setHotelId(selectedHotel.getKey());
                this.pension.setPensionType(fld_pension_type.getText());
                if (this.pension.getPensionId() != 0) {
                    result = this.pensionManager.update(this.pension);

                } else {
                    result = this.pensionManager.save(this.pension);

                }
                if (result) {
                    Helper.showMsg("done", "");
                    dispose();
                } else {
                    Helper.showMsg("error", "");
                }
            }

        });
    }
}
