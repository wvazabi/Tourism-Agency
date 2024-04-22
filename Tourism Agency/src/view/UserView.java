package view;

import business.UserManager;
import core.Helper;
import entity.Role;
import entity.User;

import javax.swing.*;

public class UserView extends Layout {
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
    private UserManager userManager;
    private DefaultComboBoxModel<Role> cmbModel;

    public UserView(User user) {
        this.add(container);
        this.guiInitilaze(500, 250, "User Edit / Update");

        // Eğer boş user gelirse new dolu user gelirse update işlemi olarak sayılacak
        this.user = user;
        this.userManager = new UserManager();

        // JComboBox için model oluştur. Role datalarını atar
        Role[] values = Role.values();
        cmbModel = new DefaultComboBoxModel<>(values);
        cmb_role.setModel(cmbModel);

        // user boş değilse fieldların doldurulması
        if (user != null) {
            fld_username.setText(user.getUsername());
            fld_password.setText(user.getPassword());
            cmb_role.setSelectedItem(user.getRole());
        }


        btn_save.addActionListener(e -> {

            // Kaydedilecek alanların boş olup olmadığını kontrol ediyor
            JTextField[] textFields = new JTextField[]{fld_username, fld_password};
            if (Helper.isFieldListEmpty(textFields)) {
                Helper.showMsg("fill");
            } else {

                boolean result;

                if (this.user == null) {
                    // user boş ise save işlemi
                    this.user = new User(fld_username.getText(),
                            fld_password.getText(),
                            (Role) cmb_role.getSelectedItem());
                    // save metodu return boolean dönüyor
                    result = this.userManager.save(this.user);
                } else {
                    // update işlemi user boş değilse
                    this.user.setUsername(fld_username.getText());
                    this.user.setPassword(fld_password.getText());
                    this.user.setRole((Role) cmb_role.getSelectedItem());
                    result = this.userManager.update(this.user);

                }
                if (result == true) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }

            }
        });
    }


}
