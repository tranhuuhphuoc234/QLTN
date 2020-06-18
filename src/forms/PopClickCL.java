package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static forms.CheckRelative.table;
import static forms.EditMainForm.tablePrisoner;

public class PopClickCL extends MouseAdapter {
//    public static String relativeid ;
    public void mouseReleased(MouseEvent e){
        int r =  CheckRelative.table.rowAtPoint(e.getPoint());
        if(r>= 0 && r < CheckRelative.table.getRowCount()){
            CheckRelative.table.setRowSelectionInterval(r,r);
        }
        else{
            CheckRelative.table.clearSelection();
        }
        int rowindex = CheckRelative.table.getSelectedRow();
        if(rowindex < 0 )
            return;
        if(e.isPopupTrigger()  && e.getComponent() instanceof JTable)
        {
            if ( ((JTable) e.getComponent()).getColumnCount() == 9) {
                ViewProfile(e);
            }
        }
    }

    public void ViewProfile(MouseEvent e){
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Profile Prisoner");
        editItem.addActionListener(e1 -> {
            ViewProfilePrisoner vpp=new ViewProfilePrisoner();
        });
        popupMenu.add(editItem);
        popupMenu.show(e.getComponent(),e.getX(),e.getY());
    }
}
