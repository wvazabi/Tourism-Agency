package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JLabel;

public class EmployeeView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JTabbedPane tpnl_employee;
    private JPanel pnl_hotel_list;
    private JPanel pnl_period_list;
    private JPanel pnl_room_list;
    private JPanel pnl_search_room;
    private JPanel pnl_reservation;
    private JPanel pnl_hotel_srch;
    private JTextField fld_srch_hotel_name;
    private JTextField fld_srch_hotel_adress;
    private JComboBox cmb_srch_hotel_star;
    private JButton btn_srch_hotel;
    private JTextField fld_dlt_hotel_id;
    private JButton btn_delete_hotel;
    private JPanel pnl_add_delete_hotel;
    private JTable tbl_hotel_list;
    private JTable tbl_season;
    private JScrollPane scrl_pane_season;
    private JTable tbl_room;
    private JScrollPane scrl_pane_room;
    private JPanel pnl_pension;
    private JTable tbl_pension;
    private JScrollPane scl_pane_pension;
    private JPanel pnl_room_srch;
    private JComboBox cmb_room_srch_adress;
    private JTextField fld_srch_check_in;
    private JTextField fld_srch_check_out;
    private JComboBox cmb_room_srch_adult_count;
    private JComboBox cmb_room_srch_child_count;
    private JButton btn_room_search;
    private JLabel pnp_address;
    private JLabel lbl_check_in;
    private JLabel lbl_check_out;
    private JLabel lbl_adult;
    private JLabel lbl_child;
    private JTable tbl_room_list;
    private JPanel pnl_make_reservation;
    private JTextField fld_selected_room_id;
    private JTextField fld_check_in_date;
    private JTextField fld_number_of_adults;
    private JTextField fld_number_of_child;
    private JTextField fld_name_surname;
    private JTextField fld_check_out_date;
    private JTextField fld_hotel_name;
    private JTextField fld_pension_type;
    private JTextField fld_number_of_nights;
    private JTextField fld_citizen_id;
    private JTextField fld_phone_number;
    private JTextField fld_email;
    private JLabel lbl_total_amaount;
    private JTextField fld_total_amount;
    private JButton btn_make_reservation;
    private JTable tbl_reservation;
    private JTextField fld_reservation_id_delete;
    private JButton btn_delete_reservation;
    private JScrollPane srl;
    private JTable tbl_price;
    private JPanel pnl_price;
    double totalCost;


    private DefaultComboBoxModel<Star> cmbModel;


    private Object[] col_season;
    private Object[] col_room;
    private Object[] col_pension;
    private Object[] col_roomSearch;
    private Object[] col_reservation;
    private Object[] col_price;


    //private Object[] col_hotel;
    private Object[] row_hotel_list;
    private Object[] row_pension_list;
    private Object[] row_reservation_list;


    private Hotel hotel;
    private User user;
    private Season season;
    private Room room;
    private Pension pension;
    private Reservation reservation;
    private Price price;

    //Tablolar üzerinde işlem yapabilmemiz için table modellere ihtiyacımız var
    private DefaultTableModel tmdl_hotel;
    private DefaultTableModel tmdl_season;
    private DefaultTableModel tmdl_room;
    private DefaultTableModel tmdl_pension;
    private DefaultTableModel tmdl_room_search;
    private DefaultTableModel tmdl_reservation;
    private DefaultTableModel tmdl_price;

    private JPopupMenu hotelMenu;
    private JPopupMenu seasonMenu;
    private JPopupMenu roomMenu;
    private JPopupMenu pensionMenu;
    private JPopupMenu reservationMenu;
    private JPopupMenu priceMenu;


    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private PensionManager pensionManager;
    private ReservationManager reservationManager;
    private PriceManager priceManager;

    String selectedRoomId;


    String checkInDate;
    String checkOutDate;
    int numberOfAdults;
    int numberOfChildren;
    String selectedHotelName;
    int selectedPensionId;
    long numberOfNights;


    /**
     * Constructs an EmployeeView object with the provided User object.
     * Initializes components, loads data into tables, and sets up action listeners.
     *
     * @param user The User object representing the current user
     */
    public EmployeeView(User user) {
        // Adding container to the view
        this.add(container);

        // Initializing GUI with specified dimensions and title
        this.guiInitilaze(1500, 750, "Employee Management");

        // Initializing managers for hotel, season, pension, room, and reservation
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.priceManager = new PriceManager();

        // Setting the current user
        this.user = user;

        // Setting check-in and check-out dates to current date and two weeks later, respectively
        this.fld_srch_check_in.setText(LocalDate.now().toString());
        this.fld_srch_check_out.setText(LocalDate.now().plusWeeks(2).toString());

        // Setting welcome message for the employee user
        this.lbl_welcome.setText("Welcome employee user: " + user.getUsername().toUpperCase());

        // Adding action listener for logout button
        btn_logout.addActionListener(e -> {
            dispose(); // Close the current view
            LoginView loginView = new LoginView(); // Open a new login view
        });

        loadReservationComponent();
        loadReservationTable(null);
        // Loading hotel-related components and data
        loadHotelComponent();
        loadHotelTable();

        // Loading season-related components and data
        loadSeasonComponent();
        loadSeasonTable(null);

        // Loading pension-related components and data
        loadPensionComponent();
        loadPensionTable(null);

        // Loading room-related components and data
        loadRoomComponent();
        loadRoomTable(null);

        // Loading room search-related components and data
        loadRoomSearchComponent();
        loadRoomSearchTable(null);

        // Loading reservation-related components and data


        loadPriceComponent();
        loadPriceTable(null);

        // Adding action listener for logout button (redundant)
        btn_logout.addActionListener(e -> {
            dispose(); // Close the current view
            LoginView loginView = new LoginView(); // Open a new login view
        });
    }

    private void loadPriceTable(ArrayList<Object[]> priceList) {
        // Define column headers for the reservation table
        this.col_price = new Object[]{"Price ID", "Hotel ID", "Season Start Date", "Season End Date", "Pension Type", "Room Type", "Age Type", "Price"};
        // If reservation list is null, retrieve reservations from the database
        if (priceList == null) {
            priceList = this.priceManager.getForTable(this.col_price.length, this.priceManager.findAll());
        }

        // Create table with provided table model, table, column headers, and reservation list
        this.createTable(this.tmdl_price, this.tbl_price, this.col_price, priceList);


    }

    private void loadPriceComponent() {

        this.tableRowSelect(tbl_price);
        // Define table model for reservations
        tmdl_price = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // Allows only certain columns to be editable
                return column != 1; // All columns except the 2nd one (column index 1) will be editable
            }
        };


        this.priceMenu = new JPopupMenu();

        priceMenu.add("Add").addActionListener(e -> {

            PriceView priceView = new PriceView(new Price());



            priceView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPriceTable(null);
                    loadRoomTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadHotelTable();

                }
            });

        });

        priceMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectPriceId = this.getTableSelectedRow(tbl_price, 0);
                if (this.priceManager.delete(selectPriceId)) {

                    Helper.showMsg("done", "");

                    loadPriceTable(null);
                    loadRoomTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadHotelTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });

        priceMenu.add("Update").addActionListener(e -> {

            int selectedPriceId = this.getTableSelectedRow(tbl_price,0);

            PriceView priceView = new PriceView(this.priceManager.getById(selectedPriceId));


            priceView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPriceTable(null);
                    loadRoomTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadHotelTable();
                }
            });

        });

        tbl_price.setComponentPopupMenu(priceMenu);



    }


    /**
     * Loads data into the reservation table with the provided reservation list.
     * If the reservation list is null, retrieves reservations from the database.
     *
     * @param reservationList The list of reservations to be loaded into the table
     */
    private void loadReservationTable(ArrayList<Object[]> reservationList) {
        // Define column headers for the reservation table
        this.col_reservation = new Object[]{"Reservation ID", "Guest ID", "Guest Name", "Guest Phone", "Guest E-Mail", "Hotel Name", "Check-in Date", "Check-out Date", "Pension Type", "Number of Night", "Number of Guest", "Total Cost"};
        // If reservation list is null, retrieve reservations from the database
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(this.col_reservation.length, this.reservationManager.findAll());
        }

        // Create table with provided table model, table, column headers, and reservation list
        this.createTable(this.tmdl_reservation, this.tbl_reservation, this.col_reservation, reservationList);
    }

    /**
     * Loads reservation-related components and sets up action listeners.
     */
    private void loadReservationComponent() {

        // Allow row selection in the reservation table
        this.tableRowSelect(tbl_reservation);
        // Define table model for reservations
        tmdl_reservation = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // Allows only certain columns to be editable
                return column != 1; // All columns except the 2nd one (column index 1) will be editable
            }
        };

        this.reservationMenu = new JPopupMenu();


        reservationMenu.add("Update").addActionListener(e -> {

            int selectReservationId = this.getTableSelectedRow(tbl_reservation,0);
            ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectReservationId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                   loadReservationTable(null);
                   loadHotelTable();
                   loadRoomTable(null);
                   loadRoomSearchTable(null);
                }
            });

        });

        tbl_reservation.setComponentPopupMenu(reservationMenu);


        // Set selected reservation ID to the delete field when a reservation is selected
        tbl_reservation.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_reservation_id = tbl_reservation.getValueAt(tbl_reservation.getSelectedRow(), 0).toString();
                fld_reservation_id_delete.setText(select_reservation_id);
            } catch (Exception ignored) {
            }
        });

        // Action listener for delete reservation button
        btn_delete_reservation.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_reservation_id_delete)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int reservation_id = Integer.parseInt(fld_reservation_id_delete.getText());
                    if (this.reservationManager.delete(reservation_id)) {
                        Helper.showMsg("done");
                        Room roomStock = this.roomManager.getById(this.reservation.getRoomId());
                        int stock = this.roomManager.getById(this.reservation.getRoomId()).getStock() + 1;
                        roomStock.setStock(stock);
                        this.roomManager.update(roomStock);
                        loadRoomTable(null);
                        loadHotelTable();
                        loadReservationTable(null);
                        fld_reservation_id_delete.setText(null);
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }

        });

    }

    /**
     * Loads data into the room search table with the provided room list.
     * If the room list is null, retrieves rooms from the database.
     *
     * @param roomList The list of rooms to be loaded into the table
     */
    private void loadRoomSearchTable(ArrayList<Object[]> roomList) {
        // Define column headers for the room search table
        this.col_roomSearch = new Object[]{"Room ID", "Hotel Name", "Hotel ID", "Hotel Address/City", "Hotel Phone", "Hotel Email", "Star", "Room Type", "Stock", "Bed Count"};
        // If room list is null, retrieve rooms from the database
        if (roomList == null) {
            roomList = this.roomManager.getForTableForRoomSrch(this.col_roomSearch.length, this.roomManager.findAll());
        }

        // Create table with provided table model, table, column headers, and room list
        this.createTable(this.tmdl_room_search, this.tbl_room_list, this.col_roomSearch, roomList);

    }

    /**
     * Loads room search-related components and sets up action listeners.
     */
    private void loadRoomSearchComponent() {

        // Allow row selection in the room list table
        this.tableRowSelect(tbl_room_list);
        // Define table model for room search
        tmdl_room_search = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // Allows only certain columns to be editable
                return column != 1; // All columns except the 2nd one (column index 1) will be editable
            }
        };

        // Set selected room information when a room is selected
        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                this.selectedRoomId = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 0).toString();
                fld_selected_room_id.setText(selectedRoomId);
                this.checkInDate = fld_srch_check_in.getText();
                this.checkOutDate = fld_srch_check_out.getText();
                fld_check_in_date.setText(checkInDate);
                fld_check_out_date.setText(fld_srch_check_out.getText());
                this.numberOfAdults = Integer.parseInt(cmb_room_srch_adult_count.getSelectedItem().toString());
                this.numberOfChildren = Integer.parseInt(cmb_room_srch_child_count.getSelectedItem().toString());
                fld_number_of_adults.setText(String.valueOf(numberOfAdults));
                fld_number_of_child.setText(String.valueOf(numberOfChildren));
                this.selectedHotelName = tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 1).toString();
                fld_hotel_name.setText(selectedHotelName);
                this.selectedPensionId = this.roomManager.getById(Integer.parseInt(selectedRoomId)).getPensionId();
                fld_pension_type.setText(this.pensionManager.getById(selectedPensionId).getPensionType());
                // Calculate the number of nights between two dates
                this.numberOfNights = ChronoUnit.DAYS.between(LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));
                fld_number_of_nights.setText(String.valueOf(numberOfNights));
                this.room = this.roomManager.getById(Integer.parseInt(selectedRoomId));
                Double adultPrice = 0.0;
                Double childPrice = 0.0;


               calculateTotalCost(this.numberOfAdults,this.numberOfChildren, (int) this.numberOfNights);


            } catch (Exception ignored) {
            }
        });

        // Action listener for making a reservation
        btn_make_reservation.addActionListener(e -> {
            reservation = new Reservation();
            reservation.setGuestCount(this.numberOfAdults + this.numberOfChildren);
            reservation.setTotalPrice(this.totalCost);
            reservation.setRoomId(Integer.parseInt(selectedRoomId));
            reservation.setGuestCitizenId(Integer.parseInt(this.fld_citizen_id.getText()));
            reservation.setGuestMail(this.fld_email.getText());
            reservation.setCheckInDate(LocalDate.parse(this.checkInDate));
            reservation.setCheckOutDate(LocalDate.parse(this.checkOutDate));
            reservation.setGuestPhone(this.fld_phone_number.getText());
            reservation.setGuestName(this.fld_name_surname.getText());
            reservation.setNumberOfNights((int) this.numberOfNights);

            this.reservationManager.save(reservation);

            // Decrease the stock of the room
            this.room = roomManager.getById(this.reservation.getRoomId());
            System.out.println("room stock " + this.room.getStock());

            int newStok = (this.room.getStock() - 1);

            System.out.println("new Stok" + newStok);
            this.room.setStock(newStok);
            this.roomManager.update(this.room);

            // Reload tables
            loadRoomSearchTable(null);
            loadReservationTable(null);
            loadHotelTable();
            loadRoomTable(null);
        });
    }

    public void calculateTotalCost(int numberOfAdults, int numberOfChildren, int numberOfNights) {
        ArrayList<Price> prices = this.priceManager.findByRoomId(this.room.getId());

        double adultPrice = 0.0;
        double childPrice = 0.0;

        System.out.println("Prices:");

        for (Price price : prices) {
            System.out.println(price.getPrice());  // This assumes Price has a meaningful toString() method
        }


        for (Price price : prices) {
            if (price.getAge().equals("Adult")) {
                adultPrice = price.getPrice();
                break; // Exit the loop once adult price is found
            }
        }

        for (Price price : prices) {
            if (price.getAge().equals("Child")) {
                childPrice = price.getPrice();
                break; // Exit the loop once child price is found
            }
        }



        this.totalCost = ((adultPrice * numberOfAdults) + (childPrice * numberOfChildren)) * numberOfNights;
        fld_total_amount.setText(String.valueOf(totalCost));

    }


    private void loadRoomTable(ArrayList<Object[]> roomList) {

        this.col_room = new Object[]{"Room ID", "Hotel Name", "Hotel ID", "Pension Type", "Pension ID", "Season Period", "Room Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "TV", "Minibar", "Game Console", "Cash Box"};

        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }

        this.createTable(this.tmdl_room, this.tbl_room, this.col_room, roomList);


    }

    private void loadRoomComponent() {
        this.tableRowSelect(tbl_room);
        tmdl_room = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // Sadece belirli bir sütunun düzenlenemez olmasını sağlar
                return column != 1; // 1. sütun (sütun indeksi 0) dışındaki tüm sütunlar düzenlenebilir olacak
            }
        };

        this.roomMenu = new JPopupMenu();

        roomMenu.add("Add").addActionListener(e -> {

            RoomSaveView roomSaveView = new RoomSaveView(new Room());


            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadHotelTable();

                }
            });

        });
        roomMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectRoomId)) {
                    Helper.showMsg("done", "");

                    loadRoomTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        roomMenu.add("Update").addActionListener(e -> {

            int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
            RoomSaveView roomSaveView = new RoomSaveView(this.roomManager.getById(selectRoomId));
            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadHotelTable();

                }
            });

        });

        tbl_room.setComponentPopupMenu(roomMenu);
    }

    private void loadPensionComponent() {
        this.tableRowSelect(tbl_pension);
        tmdl_pension = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // Sadece belirli bir sütunun düzenlenemez olmasını sağlar
                return column != 1; // 1. sütun (sütun indeksi 0) dışındaki tüm sütunlar düzenlenebilir olacak
            }
        };

        this.pensionMenu = new JPopupMenu();

        pensionMenu.add("Add").addActionListener(e -> {

            PensionView pensionView = new PensionView(new Pension());


            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable(null);
                    loadHotelTable();

                }
            });

        });
        pensionMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectPensionId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.pensionManager.delete(selectPensionId)) {
                    Helper.showMsg("done", "");

                    loadPensionTable(null);
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        pensionMenu.add("Update").addActionListener(e -> {

            int selectPensionId = this.getTableSelectedRow(tbl_pension, 0);
            PensionView pensionView = new PensionView(this.pensionManager.getById(selectPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable(null);
                    loadHotelTable();

                }
            });

        });

        tbl_pension.setComponentPopupMenu(pensionMenu);
    }

    private void loadPensionTable(ArrayList<Object[]> pensionList) {

        this.col_pension = new Object[]{"Pension ID", "Hotel Name", "Hotel ID", "Pension Type"};

        if (pensionList == null) {
            pensionList = this.pensionManager.getForTable(this.col_pension.length, this.pensionManager.findAll());
        }

        this.createTable(this.tmdl_pension, this.tbl_pension, this.col_pension, pensionList);


    }


    public void loadSeasonTable(ArrayList<Object[]> seasonList) {
        this.col_season = new Object[]{"Season ID", "Hotel Name", "Hotel ID", "Start Date", "Finish Date"};

        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(this.col_season.length, this.seasonManager.findAll());
        }

        this.createTable(this.tmdl_season, this.tbl_season, this.col_season, seasonList);


    }

    public void loadSeasonComponent() {

        this.tableRowSelect(tbl_season);
        tmdl_season = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { // Sadece belirli bir sütunun düzenlenemez olmasını sağlar
                return column != 1; // 1. sütun (sütun indeksi 0) dışındaki tüm sütunlar düzenlenebilir olacak
            }
        };

        this.seasonMenu = new JPopupMenu();

        seasonMenu.add("Add").addActionListener(e -> {

            SeasonView seasonView = new SeasonView(new Season());


            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadSeasonTable(null);
                    loadHotelTable();

                }
            });

        });
        seasonMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectSeasonId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectSeasonId)) {
                    Helper.showMsg("done", "");

                    loadSeasonTable(null);
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        seasonMenu.add("Update").addActionListener(e -> {

            int selectSeasonId = this.getTableSelectedRow(tbl_season, 0);

            SeasonView seasonView = new SeasonView(this.seasonManager.getById(selectSeasonId));

            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);
                    loadHotelTable();

                }
            });

        });

        tbl_season.setComponentPopupMenu(seasonMenu);


    }

    public void loadHotelTable() {

        // tablonun kolonlarını oluşturuyoruz Java da bütün veri tipleri objden üretildiği için obj kulllanıyoruz
        Object[] col_hotel = {"Hotel ID", "Hotel Name", "Hotel Address", "Hotel Mail", "Hotel Phone", "Hotel Star", "Hotel Car Park", "Hotel Wifi", "Hotel Pool", "Hotel Fitness", "Hotel Concierge", "Hotel Spa", "Hotel Room Service"};
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
            } catch (Exception ignored) {
            }
        });


        btn_delete_hotel.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_dlt_hotel_id)) {
                Helper.showMsg("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int hotel_id = Integer.parseInt(fld_dlt_hotel_id.getText());
                    if (this.hotelManager.delete(hotel_id)) {
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


        //JPopupMenu
        this.hotelMenu = new JPopupMenu();
        //içersine string veya menu alabiliyor

        this.hotelMenu.add("Add New").addActionListener(e -> {
            HotelSaveView hotelSaveView = new HotelSaveView(null);

            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });

        });

        this.hotelMenu.add("Update").addActionListener(e -> {
            int selectedHotelId = this.getTableSelectedRow(tbl_hotel_list, 0);
            HotelSaveView hotelSaveView = new HotelSaveView(hotelManager.getById(selectedHotelId));

            //User view penceresi kapatıldığında verilerin güncellemesi için adapter
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });


//        this.userMenu.add("Delete").addActionListener(e -> {
//            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
//            if (Helper.confirm("sure")) {
//                if(this.userManager.delete(selectedUserId)) {
//                    loadUserTable();
//                    Helper.showMsg("done");
//                } else {
//                    Helper.showMsg("error");
//                }
//
//            }
//
//        });

        this.tbl_hotel_list.setComponentPopupMenu(this.hotelMenu);
    }


}
