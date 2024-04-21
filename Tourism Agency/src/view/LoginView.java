package view;

import core.Helper;

import javax.swing.*;

public class LoginView extends Layout{
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_title;
    private JPanel w_button;
    private JTextField fld_username;
    private JButton btn_login;
    private JPasswordField fld_pass;
    private JLabel lbl_username;
    private JLabel lbl_pass;

    public LoginView() {
        this.add(container);
        this.guiInitilaze(400,400,"Tourism Agency");

        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_username)) {
                System.out.println("test");
            }

        });
    }
}
