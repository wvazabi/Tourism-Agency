package view;

import javax.swing.*;

public class LoginView extends JFrame{
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_title;
    private JPanel w_button;
    private JTextField textField1;
    private JButton loginButton;
    private JPasswordField passwordField1;

    public LoginView() {
        this.add(container);
        this.setTitle("Login View");
        this.setSize(400,400);
        this.setVisible(true);
    }
}
