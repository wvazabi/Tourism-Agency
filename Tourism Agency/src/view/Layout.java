package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {

    // Method to initialize the GUI window
    public void guiInitilaze(int width, int height, String title){
        // Set close operation to dispose the frame when closed
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Set the title of the frame
        this.setTitle(title);
        // Set the size of the frame
        this.setSize(width,height);
        // Set the location of the frame using the Helper class
        this.setLocation(Helper.setLoc("x",this.getSize()), Helper.setLoc("y",this.getSize()));
        // Make the frame visible
        this.setVisible(true);
    }

    // Method to create a table with specified columns and rows
    public void createTable(DefaultTableModel model , JTable table, Object[] columns, ArrayList<Object[]> rows){
        // Set the column identifiers for the table model
        model.setColumnIdentifiers(columns);
        // Set the table model for the table
        table.setModel(model);
        // Disable reordering of columns in the table
        table.getTableHeader().setReorderingAllowed(false);
        // Set the maximum width for the first column in the table
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        // Disable editing of cells in the table
        table.setEnabled(false);

        // Clear the table model when updating the table
        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        // If no rows are provided, initialize an empty ArrayList
        if(rows==null){
            rows=new ArrayList<>();
        }
        // Add rows to the table model
        for(Object[] row : rows){
            model.addRow(row);
        }

    }

    // Method to get the selected row from the table
    public int getTableSelectedRow(JTable table,int index){
        // Return the ID of the selected row
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index) .toString());
    }

    // Method to select a row when clicked on in the table
    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Select the clicked row
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }
}
