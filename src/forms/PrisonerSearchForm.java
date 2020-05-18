package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;

public class PrisonerSearchForm extends JDialog {
    public PrisonerSearchForm(PrisonerForm2 owner, String title, boolean modal) {
        super(owner, title, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBounds(100,100,1200,300);
        JTable tblPrisoner = new JTable();
        DBConnection db = new DBConnection();
        tblPrisoner.setModel(db.findPrisoner(owner.tfIdCard.getText()));
        JScrollPane spPrisoner = new JScrollPane(tblPrisoner);
        add(spPrisoner,BorderLayout.NORTH);
        setVisible(true);
    }
}
