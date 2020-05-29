package forms;

import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PrisonerHistoryForm extends JDialog {
    public PrisonerHistoryForm(){
        setTitle("Prisoner History");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        JTable table = new JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent) c;
                    jc.setToolTipText(getValueAt(row, column).toString());
                }
                return c;
            }
        };
        DBConnection db = new DBConnection();
        String idcard = EditMainForm.tablePrisoner.getValueAt(EditMainForm.tablePrisoner.getSelectedRow(),1).toString();
        table.setModel(db.findPrisoner("idcard",idcard));
        JScrollPane sp = new JScrollPane(table);
        add(sp,BorderLayout.NORTH);

        setBounds(50,50,1200,400);
        setVisible(true);
    }
}
