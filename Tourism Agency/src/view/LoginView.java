package view;

import business.UserManager;
import core.Helper;
import entity.User;

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
    private final UserManager userManager;


    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400,400,"Tourism Agency");

        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_username) || Helper.isFieldEmpty(this.fld_pass)) {
                Helper.showMsg("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_pass.getText());
                if(loginUser == null) {
                    Helper.showMsg("notFound");
                } else {
                    System.out.println(loginUser.toString());
                    Helper.showMsg("Succesfully Login","Login Status");
                    AdminView adminView = new AdminView(loginUser);
                    dispose();
                }
            }

        });
    }
}
