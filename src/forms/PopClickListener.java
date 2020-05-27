package forms;

import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopClickListener extends MouseAdapter {
    public static String relativeid ;
    int r;
    public void mouseReleased(MouseEvent e){
        int r =  EditMainForm.tableRelative.rowAtPoint(e.getPoint());
        if(r>= 0 && r < EditMainForm.tableRelative.getRowCount()){
            EditMainForm.tableRelative.setRowSelectionInterval(r,r);
        }
        else{
            EditMainForm.tableRelative.clearSelection();
        }
        int rowindex = EditMainForm.tableRelative.getSelectedRow();
        if(rowindex < 0 )
            return;
        if(e.isPopupTrigger()  && e.getComponent() instanceof JTable)
        {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem item = new JMenuItem("Edit");
            item.addActionListener(e1 -> {
                relativeid = EditMainForm.tableRelative.getValueAt(EditMainForm.tableRelative.getSelectedRow(),0).toString();
                EditRelativeForm erf = new EditRelativeForm();

            });
            popupMenu.add(item);
            JMenuItem removeItem = new JMenuItem("Remove");
            removeItem.addActionListener(e1 -> {
                DBConnection db = new DBConnection();
                db.callProc("deleterelative",EditMainForm.tableRelative.getValueAt(EditMainForm.tableRelative.getSelectedRow(),0).toString());
                EditMainForm.tableRelative.setModel(db.getRelative("All",""));

            });
            popupMenu.add(removeItem);
            popupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
    }
    public void mousePressed(MouseEvent e){
        int r =  EditMainForm.tableRelative.rowAtPoint(e.getPoint());
        if(r>= 0 && r < EditMainForm.tableRelative.getRowCount()){
            EditMainForm.tableRelative.setRowSelectionInterval(r,r);
        }
        else{
            EditMainForm.tableRelative.clearSelection();
        }
        int rowindex = EditMainForm.tableRelative.getSelectedRow();
        if(rowindex < 0 )
            return;
        if(e.isPopupTrigger()  && e.getComponent() instanceof JTable)
        {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem editItem = new JMenuItem("Edit");
            editItem.addActionListener(e1 -> {
                relativeid = EditMainForm.tableRelative.getValueAt(EditMainForm.tableRelative.getSelectedRow(),0).toString();
                EditRelativeForm erf = new EditRelativeForm();

            });
            popupMenu.add(editItem);
            JMenuItem removeItem = new JMenuItem("Remove");
            removeItem.addActionListener(e1 -> {
                DBConnection db = new DBConnection();
                db.callProc("deleterelative",EditMainForm.tableRelative.getValueAt(EditMainForm.tableRelative.getSelectedRow(),0).toString());
                EditMainForm.tableRelative.setModel(db.getRelative("All",""));

            });
            popupMenu.add(removeItem);
            popupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
    }}
