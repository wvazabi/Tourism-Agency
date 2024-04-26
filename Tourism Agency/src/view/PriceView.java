package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;

public class PriceView extends Layout {
    private JPanel container;
    private JComboBox<ComboItem> cmb_hotel_name;
    private JComboBox<ComboItem> cmb_s_strt_dt;
    private JComboBox<ComboItem> cmb__s_end_dt;
    private JComboBox<ComboItem> cmb_pension_type;
    private JComboBox<ComboItem> cmb_room_type;
    private JComboBox cmb_age;
    private JTextField fld_price;
    private JButton btn_save;
    private Price price;
    private PriceManager priceManager;
    private PensionManager pensionManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;

    public PriceView(Price price) {
        this.add(container);
        this.guiInitilaze(400,400,"Price Add/Uptade View");

// Eğer boş user gelirse new dolu user gelirse update işlemi olarak sayılacak
        this.price = price;
        this.priceManager = new PriceManager();
        this.pensionManager = new PensionManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();


        // user boş değilse fieldların doldurulması

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel_name.addItem(new ComboItem(hotel.getHotelId(), hotel.getHotelName()));
        }

        for (Room roomCmb : this.roomManager.findAll()) {
            this.cmb_room_type.addItem(new ComboItem(roomCmb.getId(), roomCmb.getType()));
        }
        initializeListeners();


            if (this.price.getId() != 0) {

                setPriceData();
            }

    }

    private void setPriceData() {


        fld_price.setText(String.valueOf(price.getPrice()));
        cmb_age.setSelectedItem(price.getAge());
        //cmb_hotel_name.setSelectedItem(price.getHotel().getHotelName());
        cmb_s_strt_dt.setSelectedItem(price.getSeason_start());
        cmb_pension_type.setSelectedItem(price.getPensionId());
        cmb_room_type.setSelectedItem(pensionManager.getById(price.getPensionId()));



        ComboItem defaultHotel = new ComboItem(this.price.getHotel_id(), this.hotelManager.getById(price.getHotel_id()).getHotelName());
        this.cmb_hotel_name.getModel().setSelectedItem(defaultHotel);

        ComboItem defaultType = new ComboItem(this.price.getRoom_id(), this.roomManager.getById(this.price.getRoom_id()).getType());
        this.cmb_room_type.getModel().setSelectedItem(defaultType);

        ComboItem defaultPension = new ComboItem(this.price.getPensionId(), this.pensionManager.getById(price.getPensionId()).getPensionType());
        this.cmb_pension_type.getModel().setSelectedItem(defaultPension);

        Date startDate = Date.valueOf(this.seasonManager.getById(price.getSeason_id()).getStartDate());
        Date finishDate = Date.valueOf(this.seasonManager.getById(price.getSeason_id()).getFinishDate());
        String seasonDates = startDate.toString() + " - " + finishDate.toString();

        ComboItem defaultSeason = new ComboItem(this.price.getSeason_id(), seasonDates);
        this.cmb_s_strt_dt.getModel().setSelectedItem(defaultSeason);
    }


    private void initializeRoomTypeComboBox() {
        for (Room roomCmb : this.roomManager.findAll()) {
            this.cmb_room_type.addItem(new ComboItem(roomCmb.getId(), roomCmb.getType()));
        }
    }
    private void initializeListeners() {
        cmb_hotel_name.addActionListener(e -> {
            ComboItem selectedHotel = (ComboItem) cmb_hotel_name.getSelectedItem();
            int selectedHotelId = selectedHotel.getKey();
            ArrayList<Season> seasons = seasonManager.findByHotelId(selectedHotelId);
            cmb_s_strt_dt.removeAllItems(); // Clear previous seasons
            cmb_pension_type.removeAllItems(); // Clear previous pensions
            for (Season season : seasons) {
                String startDate = season.getStartDate();
                String finishDate = season.getFinishDate();
                String seasonDates = startDate.toString() + " - " + finishDate.toString();
                cmb_s_strt_dt.addItem(new ComboItem(season.getId(), seasonDates));
            }
            cmb_s_strt_dt.setEnabled(true);
            populatePensions(selectedHotelId); // Populate pensions for the selected hotel
            cmb_pension_type.setEnabled(true);
        });

        btn_save.addActionListener(e -> {

                saveOrUpdateRoom();
        });

        // Initialize pensions combo box when creating a new room
        populatePensions(((ComboItem) cmb_hotel_name.getSelectedItem()).getKey());
    }

    private void populatePensions(int hotelId) {
        cmb_pension_type.removeAllItems();
        for (Pension pension : this.pensionManager.findByHotelId(hotelId)) {
            this.cmb_pension_type.addItem(new ComboItem(pension.getPensionId(), pension.getPensionType()));
        }
    }

    private void saveOrUpdateRoom() {
        ComboItem selectedHotel = (ComboItem) cmb_hotel_name.getSelectedItem();
        ComboItem selectedSeason = (ComboItem) cmb_s_strt_dt.getSelectedItem();
        ComboItem selectedPension = (ComboItem) cmb_pension_type.getSelectedItem();
        ComboItem selectedRoomType = (ComboItem) cmb_room_type.getSelectedItem();



        this.price.setHotel_id(selectedHotel.getKey());
        this.price.setSeason_id(selectedSeason.getKey());
        this.price.setPensionId(selectedPension.getKey());
        this.price.setRoom_id(selectedRoomType.getKey());
        this.price.setAge(cmb_age.getSelectedItem().toString());
        this.price.setPrice(Double.parseDouble(fld_price.getText()));


        boolean result;

        if (this.price.getId() != 0) {
            result = this.priceManager.update(this.price);
        } else {
            result = this.priceManager.save(this.price);
        }

        if (result) {
            Helper.showMsg("done", "");
            dispose();
        } else {
            Helper.showMsg("error", "");
        }
    }


}
