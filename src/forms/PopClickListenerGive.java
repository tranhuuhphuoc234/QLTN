package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PopClickListenerGive extends MouseAdapter {

    public void mouseReleased(MouseEvent e){
        int r =  GivePriority.tbl.rowAtPoint(e.getPoint());
        if(r>= 0 && r < GivePriority.tbl.getRowCount()){
            GivePriority.tbl.setRowSelectionInterval(r,r);
        }
        else{
            GivePriority.tbl.clearSelection();
        }
        int rowindex = GivePriority.tbl.getSelectedRow();
        if(rowindex < 0 )
            return;
        if(e.isPopupTrigger()  && e.getComponent() instanceof JTable)
        {
       }
            givePriority(e);
        }

    public void mousePressed(MouseEvent e){
        int r =  GivePriority.tbl.rowAtPoint(e.getPoint());
        if(r>= 0 && r < GivePriority.tbl.getRowCount()){
            GivePriority.tbl.setRowSelectionInterval(r,r);
        }
        else{
            GivePriority.tbl.clearSelection();
        }
        int rowindex = GivePriority.tbl.getSelectedRow();
        if(rowindex < 0 )
            return;
        if(e.isPopupTrigger()  && e.getComponent() instanceof JTable) {

            givePriority(e);
        }
    }
    public void givePriority(MouseEvent e)
    {
        String username = GivePriority.tbl.getValueAt(GivePriority.tbl.getSelectedRow(),0).toString();
        DBConnection db = new DBConnection();
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem giveItem = new JMenuItem("Give Priority");
        giveItem.addActionListener(e1 -> {
            db.callProc("givepriority",username);
        });
        popupMenu.add(giveItem);
        popupMenu.show(e.getComponent(),e.getX(),e.getY());
    }}