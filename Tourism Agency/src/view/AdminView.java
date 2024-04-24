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
    // GUI components
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_wecome;
    private JButton btn_log_out;
    private JTabbedPane tab_menu;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_user;

    // Data model for the table
    private DefaultTableModel tmdl_user = new DefaultTableModel();

    // Popup menu for user actions
    private JPopupMenu userMenu;

    // User manager instance to handle user-related operations
    private UserManager userManager;

    // Current user
    private User user;

    // Constructor
    public AdminView(User user) {
        // Add the container to the frame
        this.add(container);
        // Initialize GUI settings
        this.guiInitilaze(1000, 500, "User List");
        // Initialize the user manager
        this.userManager = new UserManager();
        // Set the current user
        this.user = user;

        // If the user is null, dispose the frame
        if (this.user == null) {
            dispose();
        }

        // Set welcome label
        this.lbl_wecome.setText("Welcome user: " + user.getUsername().toUpperCase());

        // Log out button action listener
        btn_log_out.addActionListener(e -> {
            dispose(); // Close the frame
            LoginView loginView = new LoginView(); // Open the login view
        });

        // Load user table
        loadUserTable();
        // Load user components
        loadUserComponent();
    }

    // Method to load user table
    public void loadUserTable() {
        // Define table columns
        Object[] col_user = {"User ID", "Username", "Password", "User Role"};
        // Get user list from the database
        ArrayList<User> userArrayList = userManager.findAll();

        // Check if user list is not null
        if (userArrayList != null) {
            // Convert user list to an object list for the table
            ArrayList<Object[]> userObjectList = this.userManager.getForTable(col_user.length, userArrayList);
            // Create the table
            this.createTable(this.tmdl_user, this.tbl_user, col_user, userObjectList);
        } else {
            // Show error message or handle the error
            Helper.showMsg("error");
        }
    }

    // Method to load user components
    public void loadUserComponent() {
        // Select a row when clicked
        this.tableRowSelect(tbl_user);
        // Create a popup menu for user actions
        this.userMenu = new JPopupMenu();

        // Add action for "Add New" menu item
        this.userMenu.add("Add New").addActionListener(e -> {
            // Open user view for adding a new user
            UserView userView = new UserView(null);
            // Add window listener to reload user table when the user view is closed
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        // Add action for "Update" menu item
        this.userMenu.add("Update").addActionListener(e -> {
            // Get the selected user ID
            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            // Open user view for updating the selected user
            UserView userView = new UserView(userManager.getById(selectedUserId));
            // Add window listener to reload user table when the user view is closed
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        // Add action for "Delete" menu item
        this.userMenu.add("Delete").addActionListener(e -> {
            // Get the selected user ID
            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            // Confirm the deletion action
            if (Helper.confirm("sure")) {
                // Delete the user from the database
                if (this.userManager.delete(selectedUserId)) {
                    loadUserTable(); // Reload user table
                    Helper.showMsg("done"); // Show success message
                } else {
                    Helper.showMsg("error"); // Show error message if deletion fails
                }
            }
        });

        // Set the popup menu for the user table
        this.tbl_user.setComponentPopupMenu(this.userMenu);
    }
}
