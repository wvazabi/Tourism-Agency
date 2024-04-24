package view;

import javax.swing.*;

public class ReservationView extends Layout{
    private JPanel container;
    private JButton checkInDateButton;

    public ReservationView() {
        this.add(container);
        this.guiInitilaze(400,400,"Make Reservation");

    }
}
