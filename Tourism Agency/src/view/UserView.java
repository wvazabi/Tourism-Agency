package view;

import entity.User;

import javax.swing.*;

public class UserView extends Layout{
    private JTextField fld_username;
    private JTextField fld_password;
    private JComboBox cmb_role;
    private JButton btn_save;
    private JPanel container;
    private JLabel lbl_welcome;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_role;
    private User user;

    public UserView(User user) {
        this.add(container);
        this.guiInitilaze(500,250,"User Edit / Update");
        // Eğer boş user gelirse new dolu user gelirse update işlemi olarak sayılacak
        this.user = user;
    }



}
