package view;

import business.HotelManager;
import business.PensionManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;
import entity.Role;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationView extends Layout {
    private JPanel container;
    private JTextField fld_guest_name;
    private JTextField fld_guest_id;
    private JTextField fld_guest_phone;
    private JTextField fld_guest_email;
    private JTextField fld_hotel_name;
    private JTextField fld_check_out_date;
    private JTextField fld_check_in_date;
    private JTextField fld_pension_type;
    private JTextField fld_number_of_night;
    private JTextField fld_number_of_guest;
    private JTextField fld_total_cost;
    private JButton btn_save;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private PensionManager pensionManager;

    public ReservationView(Reservation resevation) {
        this.reservation = resevation;
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();

        this.add(container);
        this.guiInitilaze(550, 500, "Reservation Update/Edit View");


        this.fld_guest_name.setText(resevation.getGuestName());
        this.fld_guest_id.setText(String.valueOf(reservation.getGuestCitizenId()));
        this.fld_guest_phone.setText(reservation.getGuestPhone());
        this.fld_guest_email.setText(reservation.getGuestMail());
        this.fld_hotel_name.setText(hotelManager.getById(this.roomManager.getById(this.reservation.getRoomId()).getHotelId()).getHotelName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Tarih formatı
        fld_check_in_date.setText(reservation.getCheckInDate().format(formatter));
        fld_check_out_date.setText(reservation.getCheckOutDate().format(formatter));
        fld_pension_type.setText(this.pensionManager.getById(this.roomManager.getById(this.reservation.getRoomId()).getPensionId()).getPensionType()); // Örneğin yarım pansiyon, tam pansiyon gibi
        fld_number_of_night.setText(String.valueOf(reservation.getNumberOfNights()));
        fld_number_of_guest.setText(String.valueOf(reservation.getGuestCount()));
        //this.lbl_total_cost.setText(String.valueOf(this.reservation.getTotalPrice()));


        btn_save.addActionListener(e -> {

//
//            this.reservationManager.update(this.reservation);


            JTextField[] textFields = new JTextField[]{fld_guest_name, fld_guest_email, fld_guest_phone, fld_guest_id, fld_number_of_guest, fld_check_in_date, fld_check_out_date, fld_hotel_name, fld_pension_type};
            if (Helper.isFieldListEmpty(textFields)) {
                Helper.showMsg("fill");
            } else {
                if (this.reservation == null) {
                    System.out.println("reservation object not found");
                } else {
                    // Rezervasyon nesnesini güncelleme işlemi
                    this.reservation.setGuestName(fld_guest_name.getText());
                    this.reservation.setNumberOfNights(Integer.parseInt(fld_number_of_night.getText()));
                    this.reservation.setGuestPhone(fld_guest_phone.getText());
                    this.reservation.setGuestMail(fld_guest_email.getText());
                    this.reservation.setCheckInDate(LocalDate.parse(fld_check_in_date.getText(), formatter));
                    this.reservation.setCheckOutDate(LocalDate.parse(fld_check_out_date.getText(), formatter));
                    this.reservation.setGuestCount(Integer.parseInt(fld_number_of_guest.getText()));
                    //this.reservation.setTotalPrice(Double.valueOf(lbl_total_cost.getText()));

                    // Rezervasyon yöneticisi aracılığıyla güncelleme işlemini gerçekleştirme
                    boolean result = this.reservationManager.update(this.reservation);

                    // Sonucu kontrol etme ve mesaj gösterme
                    if (result) {
                        Helper.showMsg("done");
                        dispose();
                    } else {
                        Helper.showMsg("error");
                    }
                }
            }



        });
    }
}
