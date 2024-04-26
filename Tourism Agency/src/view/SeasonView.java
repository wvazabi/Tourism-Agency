package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Season;
import core.ComboItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonView extends Layout {
    private JTextField fld_start_date;
    private JTextField fld_finish_date;
    private JButton btn_save_season;
    private JLabel lbl_season_edit;
    private JLabel lbl_hotel_name;
    private JComboBox<ComboItem> cmd_hotel_name;
    private JPanel container;
    private SeasonManager seasonManager;
    private Season season;
    private HotelManager hotelManager;
    private ArrayList<Hotel> hotels;
    private DefaultComboBoxModel cmbModel;

    public SeasonView(Season season) {
        this.add(container);
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.season = season;
        this.guiInitilaze(300, 400, "Season Edit Update");

        //populateHotelComboBox();



        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmd_hotel_name.addItem(new ComboItem(hotel.getHotelId(), hotel.getHotelName()));
        }

        // TODO editle


        if (this.season.getId() != 0) {
            this.fld_start_date.setText(this.season.getStartDate());
            this.fld_finish_date.setText(this.season.getFinishDate());

            ComboItem defaultHotel = new ComboItem(this.season.getHotelId(), this.hotelManager.getById(season.getHotelId()).getHotelName());
            this.cmd_hotel_name.getModel().setSelectedItem(defaultHotel);
        }



        btn_save_season.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_start_date, this.fld_finish_date,})) {
                Helper.showMsg("fill", "");
            } else {
                boolean result = true;


                ComboItem selectedHotel = (ComboItem) cmd_hotel_name.getSelectedItem();

                this.season.setHotelId(selectedHotel.getKey());
                this.season.setStartDate(fld_start_date.getText());
                this.season.setFinishDate(fld_finish_date.getText());


                if (this.season.getId() != 0) {
                    result = this.seasonManager.update(this.season);

                } else {
                    this.season = new Season(selectedHotel.getKey(),fld_start_date.getText(),fld_finish_date.getText());
                    result = this.seasonManager.save(this.season);

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
