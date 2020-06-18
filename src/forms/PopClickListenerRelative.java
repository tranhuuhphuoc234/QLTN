package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopClickListenerRelative extends MouseAdapter {
    public static String relativeid;

    public void mouseReleased(MouseEvent e) {
        int r = EditMainForm.tableRelative.rowAtPoint(e.getPoint());
        if (r >= 0 && r < EditMainForm.tableRelative.getRowCount()) {
            EditMainForm.tableRelative.setRowSelectionInterval(r, r);
        } else {
            EditMainForm.tableRelative.clearSelection();
        }
        int rowindex = EditMainForm.tableRelative.getSelectedRow();
        if (rowindex < 0)
            return;
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
            relativeEdit(e);

        }
    }

    public void relativeEdit(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit relative");
        editItem.addActionListener(e1 -> {
            relativeid = EditMainForm.tableRelative.getValueAt(EditMainForm.tableRelative.getSelectedRow(), 0).toString();
            EditRelativeForm erf = new EditRelativeForm();

        });
        popupMenu.add(editItem);
        JMenuItem removeItem = new JMenuItem("Remove relative");
        removeItem.addActionListener(e1 -> {
            WarningRelativeForm wrf = new WarningRelativeForm();
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
