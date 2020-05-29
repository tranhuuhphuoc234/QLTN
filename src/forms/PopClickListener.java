package forms;

import utils.DBConnection;

import javax.swing.*;
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
            if ( ((JTable) e.getComponent()).getColumnCount() == 9) {
                relativeEdit(e);
            }
            else {
                prisonerEdit(e);
            }

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
        if(e.isPopupTrigger()  && e.getComponent() instanceof JTable) {
            if (((JTable) e.getComponent()).getColumnCount() == 9) {
                relativeEdit(e);
            }
            else
            {
                prisonerEdit(e);
            }

        }
    }
    public void relativeEdit(MouseEvent e){
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit relative");
        editItem.addActionListener(e1 -> {
            relativeid = EditMainForm.tableRelative.getValueAt(EditMainForm.tableRelative.getSelectedRow(),0).toString();
            EditRelativeForm erf = new EditRelativeForm();

        });
        popupMenu.add(editItem);
        JMenuItem removeItem = new JMenuItem("Remove relative");
        removeItem.addActionListener(e1 -> {
            WarningRelativeForm wrf = new WarningRelativeForm();
        });
        popupMenu.add(removeItem);
        popupMenu.show(e.getComponent(),e.getX(),e.getY());
    }
    public void prisonerEdit(MouseEvent e)
    {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Edit prisoner");
        editItem.addActionListener(e1 -> {
            EditPrisonerForm epf = new EditPrisonerForm();
        });
        popupMenu.add(editItem);

        JMenuItem historyItem = new JMenuItem("History prisoner");
        historyItem.addActionListener(e1 -> {
            PrisonerHistoryForm phf = new PrisonerHistoryForm();
        });
        popupMenu.add(historyItem);

        JMenuItem removeItem = new JMenuItem("Remove prisoner");
        removeItem.addActionListener(e1 -> {
            WarningPrisonerForm wf = new WarningPrisonerForm();

        });
        popupMenu.add(removeItem);



        popupMenu.show(e.getComponent(),e.getX(),e.getY());
    }
}
