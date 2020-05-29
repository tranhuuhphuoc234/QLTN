package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;

public class WarningRelativeForm extends JDialog{
    public WarningRelativeForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setModal(true);
        setTitle("Warning");
        setBounds(700,250,400,150);

        JLabel lbl = new JLabel();
        String selectedId = EditMainForm.tableRelative.getValueAt(EditMainForm.tableRelative.getSelectedRow(),0).toString();
        String text = "Are you sure you want to delete this relative ID Card: "+selectedId+" ?";
        lbl.setText(text);
        lbl.setFont(new Font("Dialog", Font.BOLD,12));
        lbl.setBounds(30,20,400,25);

        JButton btnYes = new JButton("Yes");
        btnYes.setBounds(100,60,80,25);
        btnYes.addActionListener(e -> {
            DBConnection db = new DBConnection();
            db.callProc("deleterelative",selectedId);
            EditMainForm.tableRelative.setModel(db.getRelative("All",""));
            dispose();
        });
        add(btnYes);

        JButton btnNo = new JButton("No");
        btnNo.setBounds(200,60,80,25);
        btnNo.addActionListener(e -> {
            this.dispose();
        });
        add(btnNo);
        add(lbl);
        setVisible(true);
    }
}
