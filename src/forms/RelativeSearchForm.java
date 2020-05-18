package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;

public class RelativeSearchForm extends JDialog {
    public RelativeSearchForm(PrisonerForm2 owner, String title, boolean modal) {
        super(owner, title, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        setBounds(100, 100, 1000, 300);
        JTable tblRelative = new JTable();
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
        pnl.add(btnAdd);
        add(pnl);

        setVisible(true);
    }
}
