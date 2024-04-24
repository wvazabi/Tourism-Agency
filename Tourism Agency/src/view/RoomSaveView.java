package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class RoomSaveView extends Layout {
    private JPanel container;
    private JComboBox<ComboItem> cmb_room_hotel_name;
    private JComboBox<ComboItem> cmb_room_season;
    private JComboBox<ComboItem> cmb_room_type;
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
    private JLabel lbl_room_pension;
    private JComboBox<ComboItem> cmb_room_pension;

    private RoomManager roomManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;

    private Room room;

    public RoomSaveView(Room room) {
        this.add(container);
        this.guiInitilaze(450, 450, "Room Add/Update Screen");
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.room = room;

        initializeHotelComboBox();
        initializeRoomTypeComboBox();
        initializeListeners();

        if (this.room.getId() != 0) {
            setRoomData();
        }
    }

    private void initializeHotelComboBox() {
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_room_hotel_name.addItem(new ComboItem(hotel.getHotelId(), hotel.getHotelName()));
        }
    }

    private void initializeRoomTypeComboBox() {
        for (Room roomCmb : this.roomManager.findAll()) {
            this.cmb_room_type.addItem(new ComboItem(roomCmb.getId(), roomCmb.getType()));
        }
    }

    private void initializeListeners() {
        cmb_room_hotel_name.addActionListener(e -> {
            ComboItem selectedHotel = (ComboItem) cmb_room_hotel_name.getSelectedItem();
            int selectedHotelId = selectedHotel.getKey();
            ArrayList<Season> seasons = seasonManager.findByHotelId(selectedHotelId);
            cmb_room_season.removeAllItems(); // Clear previous seasons
            cmb_room_pension.removeAllItems(); // Clear previous pensions
            for (Season season : seasons) {
                Date startDate = Date.valueOf(season.getStartDate());
                Date finishDate = Date.valueOf(season.getFinishDate());
                String seasonDates = startDate.toString() + " - " + finishDate.toString();
                cmb_room_season.addItem(new ComboItem(season.getId(), seasonDates));
            }
            cmb_room_season.setEnabled(true);
            populatePensions(selectedHotelId); // Populate pensions for the selected hotel
            cmb_room_pension.setEnabled(true);
        });

        btn_save_button.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_room_adult_price, this.fld_room_child_price,
                    this.fld_room_stock, this.fld_bed_number})) {
                Helper.showMsg("fill", "");
            } else {
                saveOrUpdateRoom();
            }
        });

        // Initialize pensions combo box when creating a new room
        populatePensions(((ComboItem) cmb_room_hotel_name.getSelectedItem()).getKey());
    }


    private void populatePensions(int hotelId) {
        cmb_room_pension.removeAllItems();
        for (Pension pension : this.pensionManager.findByHotelId(hotelId)) {
            this.cmb_room_pension.addItem(new ComboItem(pension.getPensionId(), pension.getPensionType()));
        }
    }


    private void setRoomData() {
        this.fld_bed_number.setText(String.valueOf(room.getBedCapacity()));
        this.fld_room_adult_price.setText(String.valueOf(room.getAdultPrice()));
        this.fld_room_child_price.setText(String.valueOf(room.getChildPrice()));
        this.fld_room_stock.setText(String.valueOf(room.getStock()));
        this.rd_room_tv.setSelected(room.isTv());
        this.rd_room_minibar.setSelected(room.isMinibar());
        this.rd_room_game_cnsl.setSelected(room.isGameConsole());
        this.rd_room_cash_box.setSelected(room.isCashBox());

        ComboItem defaultHotel = new ComboItem(this.room.getHotelId(), this.hotelManager.getById(room.getHotelId()).getHotelName());
        this.cmb_room_hotel_name.getModel().setSelectedItem(defaultHotel);

        ComboItem defaultType = new ComboItem(this.room.getId(), this.room.getType());
        this.cmb_room_type.getModel().setSelectedItem(defaultType);

        ComboItem defaultPension = new ComboItem(this.room.getPensionId(), this.pensionManager.getById(room.getPensionId()).getPensionType());
        this.cmb_room_pension.getModel().setSelectedItem(defaultPension);

        Date startDate = Date.valueOf(this.seasonManager.getById(room.getSeasonId()).getStartDate());
        Date finishDate = Date.valueOf(this.seasonManager.getById(room.getSeasonId()).getFinishDate());
        String seasonDates = startDate.toString() + " - " + finishDate.toString();

        ComboItem defaultSeason = new ComboItem(this.room.getSeasonId(), seasonDates);
        this.cmb_room_season.getModel().setSelectedItem(defaultSeason);
    }

    private void saveOrUpdateRoom() {
        ComboItem selectedHotel = (ComboItem) cmb_room_hotel_name.getSelectedItem();
        ComboItem selectedSeason = (ComboItem) cmb_room_season.getSelectedItem();
        ComboItem selectedPension = (ComboItem) cmb_room_pension.getSelectedItem();
        ComboItem selectedRoomType = (ComboItem) cmb_room_type.getSelectedItem();


        this.room.setHotelId(selectedHotel.getKey());
        this.room.setSeasonId(selectedSeason.getKey());
        this.room.setPensionId(selectedPension.getKey());
        String roomType = roomManager.getById(selectedRoomType.getKey()).getType();
        this.room.setType(roomType);


        this.room.setAdultPrice(Double.parseDouble(fld_room_adult_price.getText()));
        this.room.setChildPrice(Double.parseDouble(fld_room_child_price.getText()));
        this.room.setStock(Integer.parseInt(fld_room_stock.getText()));
        this.room.setBedCapacity(Integer.parseInt(fld_bed_number.getText()));

        this.room.setTv(rd_room_tv.isSelected());
        this.room.setMinibar(rd_room_minibar.isSelected());
        this.room.setGameConsole(rd_room_game_cnsl.isSelected());
        this.room.setCashBox(rd_room_cash_box.isSelected());

        boolean result;

        if (this.room.getId() != 0) {
            result = this.roomManager.update(this.room);
        } else {
            result = this.roomManager.save(this.room);
        }

        if (result) {
            Helper.showMsg("done", "");
            dispose();
        } else {
            Helper.showMsg("error", "");
        }
    }
}
