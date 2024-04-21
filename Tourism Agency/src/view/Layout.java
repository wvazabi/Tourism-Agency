package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {

    public void guiInitilaze(int width, int height, String title){
        //Pencere kapatıldığında sadece pencerenin kapatılması sağlar, uygulamanın tamanının kapatılmasını önler
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(title);
        this.setSize(width,height);
        this.setLocation(Helper.setLoc("x",this.getSize()), Helper.setLoc("y",this.getSize()));
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel model , JTable table, Object[] columns, ArrayList<Object[]> rows){
        //tablonun kolonlarını belirleyebilmek için setcolumnidentifiers ya vektör ya obje istiyor
        model.setColumnIdentifiers(columns);
        //tabloya table model atanıyor
        table.setModel(model);
        //tabloların başlıklarını mouse ile değiştirmeyi kapatır
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        //çift tıklanıldığında düzenlenmesini engelleniyor
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if(rows==null){
            rows=new ArrayList<>();
        }
        for(Object[] row : rows){
            model.addRow(row);
        }

    }

    public int getTableSelectedRow(JTable table,int index){

        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index) .toString());
    }

    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                // Mouse ile tıkladığımız row'u seçili hale getiriyor
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }


}
