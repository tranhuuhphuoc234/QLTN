package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;

public class WarningPrisonerForm extends JDialog {
    public WarningPrisonerForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setModal(true);
        setTitle("Warning");
        setBounds(700,250,400,150);

        JLabel lbl = new JLabel();
        String selectedId = EditMainForm.tablePrisoner.getValueAt(EditMainForm.tablePrisoner.getSelectedRow(),0).toString();
        String text = "Are you sure you want to delete this prisoner ID: "+selectedId+" ?";
        lbl.setText(text);
        lbl.setFont(new Font("Dialog", Font.BOLD,14));
        lbl.setBounds(10,20,450,25);

        JButton btnYes = new JButton("Yes");
        btnYes.setBounds(100,60,80,25);
        btnYes.addActionListener(e -> {
            DBConnection db = new DBConnection();
            db.callProc("deleteprisoner",selectedId);
            EditMainForm.tablePrisoner.setModel(db.findPrisoner("All",""));
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
