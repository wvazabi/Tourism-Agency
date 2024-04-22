package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_wecome;
    private JButton btn_log_out;
    private JTabbedPane tab_menu;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private User user;

    //Tablolar üzerinde işlem yapabilmemiz için table modellere ihtiyacımız var
    private DefaultTableModel tmdl_user = new DefaultTableModel();

    private JPopupMenu userMenu;

    private UserManager userManager;

    public AdminView(User user) {
        this.add(container);
        this.guiInitilaze(1000, 500, "User List");
        this.user = user;
        this.userManager = new UserManager();

        if (this.user == null) {
            dispose();
        }
        this.lbl_wecome.setText("Welcome user: " + user.getUsername().toUpperCase());

        btn_log_out.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView();
        });

        loadUserTable();
        loadUserComponent();

    }

    public void loadUserTable() {
        // tablonun kolonlarını oluşturuyoruz Java da bütün veri tipleri objden üretildiği için obj kulllanıyoruz
        Object[] col_user = {"User ID", "Username", "Password", "User Role"};
        ArrayList<User> userArrayList = userManager.findAll();


        if (userArrayList != null) { // Null kontrolü yapılıyor
            ArrayList<Object[]> userObjectList = this.userManager.getForTable(col_user.length, userArrayList); // Tablo için gerekli listeyi oluştur
            this.createTable(this.tmdl_user, this.tbl_user, col_user, userObjectList);
        } else {
            // Hata mesajı veya başka bir işlem yapılabilir
            Helper.showMsg("error");
        }


    }

    public void loadUserComponent() {

        this.tableRowSelect(tbl_user);
        this.userMenu = new JPopupMenu();
        //içersine string veya menu alabiliyor

        this.userMenu.add("Add New").addActionListener(e -> {
            UserView userView = new UserView(null);

            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });

        });
        this.userMenu.add("Update").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(userManager.getById(selectedUserId));

            //User view penceresi kapatıldığında verilerin güncellemesi için adapter
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.userMenu.add("Delete").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            if (Helper.confirm("sure")) {
                if(this.userManager.delete(selectedUserId)) {
                    loadUserTable();
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }

            }

        });

        this.tbl_user.setComponentPopupMenu(this.userMenu);
    }
}
