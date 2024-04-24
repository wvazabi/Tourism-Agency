package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    // Method to set the look and feel of the UI to Nimbus
    public static void setTheme() {
        // Setting look and feel to Nimbus
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;
            }
        }
    }

    // Method to check if an array of JTextFields is empty
    public static boolean isFieldListEmpty(JTextField[] fieldList){
        for (JTextField field :fieldList){
            if(isFieldEmpty(field)) return true;
        }
        return false;
    }

    // Static method to check if a JTextField is empty
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // Method to display a message dialog with a given message and title
    public static void showMsg(String str, String title) {
        String msg;
        String header = title;
        switch (str) {
            case "fill":
                msg = "Please fill empty fields";
                header = "Alert !";
                break;
            case "done":
                msg = "Successfully completed";
                header = "Completed";
                break;
            case "error":
                msg = "Error";
                header = "Error Message";
                break;
            default:
                msg = str;
                header = title;
        }

        JOptionPane.showMessageDialog(null,
                msg,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to display a message dialog with a given message
    public static void showMsg(String str) {
        String msg;
        String header;
        switch (str) {
            case "fill":
                msg = "Please fill empty fields";
                header = "Alert !";
                break;
            case "done":
                msg = "Successfully completed";
                header = "Completed";
                break;
            case "error":
                msg = "Error";
                header = "Error Message";
                break;
            case "notFound":
                msg = "Record does not found!";
                header = "Not Found!";
                break;
            default:
                msg = str;
                header = "Message";
        }

        JOptionPane.showMessageDialog(null,
                msg,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to set the location of a window
    public static int setLoc(String type, Dimension size) {

        switch (type) {
            case "x":
                return (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y":
                return (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default:
                return 0;
        }
    }

    // Method to ask the user for confirmation
    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            msg = "Are you sure !!";
        } else {
            msg = str;
        }
        // If user clicks yes, return true
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure?", JOptionPane.YES_NO_OPTION) == 0;
    }
}
