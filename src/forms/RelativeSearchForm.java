package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;

public class RelativeSearchForm extends JDialog {
    JTable tblRelative;
    public String relativeIdCard;
    JLabel lblWarn;
    public RelativeSearchForm(PrisonerForm owner, String title, boolean modal) {
        super(owner, title, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        setBounds(100, 100, 1000, 300);
        tblRelative = new JTable();
        DBConnection db = new DBConnection();
        tblRelative.setModel(db.findRelative(owner.tfRelativeIDCard.getText()));
        JScrollPane spRelative = new JScrollPane(tblRelative);
        add(spRelative);
        JPanel pnl = new JPanel();
        pnl.setLayout(new FlowLayout());
        pnl.add(new JLabel());
        pnl.add(new JLabel());

        JButton btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(80,25));
        btnAdd.addActionListener(e -> {
            try {
                relativeIdCard = "" + db.getColumnID("relative", (String) tblRelative.getValueAt(0, 0));
                owner.relativeId = Integer.parseInt(relativeIdCard);
                lblWarn.setText("Add sucessfully");
                lblWarn.setForeground(Color.green);
                owner.btnAdd.setEnabled(false);
            }catch (Exception ex)
            {
                ex.printStackTrace();
                lblWarn.setText("Could not find relative");
                lblWarn.setForeground(Color.red);

            }
        });
        pnl.add(btnAdd);
        add(pnl);
        lblWarn = new JLabel();
        pnl.add(lblWarn);

        setVisible(true);
    }
}
