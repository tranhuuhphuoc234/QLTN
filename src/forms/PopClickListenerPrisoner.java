package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static forms.EditMainForm.tablePrisoner;

public class PopClickListenerPrisoner extends MouseAdapter {

    public void mouseReleased(MouseEvent e) {
        int r = tablePrisoner.rowAtPoint(e.getPoint());
        if (r >= 0 && r < tablePrisoner.getRowCount()) {
            tablePrisoner.setRowSelectionInterval(r, r);
        } else {
            tablePrisoner.clearSelection();
        }
        int rowindex = tablePrisoner.getSelectedRow();
        if (rowindex < 0)
            return;
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            prisonerEdit(e);

        }
    }

    public void prisonerEdit(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Edit prisoner");
        editItem.addActionListener(e1 -> {
            EditPrisonerForm epf = new EditPrisonerForm();
            DBConnection db = new DBConnection();
            tablePrisoner.setModel(db.findPrisoner("All", ""));
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
        DBConnection db = new DBConnection();
        if (!db.checkAccess(LoginForm.userName)) {
            removeItem.setEnabled(false);
        } else {
            removeItem.setEnabled(true);
        }


        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}
