package forms;

import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class EditRelativeForm extends JDialog {
    JComboBox boxCity,boxCountry;
    DBConnection db = new DBConnection();
    DefaultTableModel tbl = db.findRelative(PopClickListener.relativeid);
    String idCard = tbl.getValueAt(0,0).toString();
    String name = tbl.getValueAt(0,1).toString();
    String age = tbl.getValueAt(0,2).toString();
    String phone = tbl.getValueAt(0,3).toString();
    String address = tbl.getValueAt(0,4).toString();
    String cityRel = tbl.getValueAt(0,5).toString();
    String countryRel = tbl.getValueAt(0,6).toString();
    String relationship = tbl.getValueAt(0,7).toString();
    String prisonerid = tbl.getValueAt(0,8).toString();
    public EditRelativeForm() {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Edit");
        setLayout(null);
        setBounds(300,120,500,600);

        JLabel lblRelativeIdField = new JLabel("ID Card");
        lblRelativeIdField.setBounds(80, 30, 80, 25);
        add(lblRelativeIdField);

        JLabel lblRelativeId = new JLabel();
        lblRelativeId.setBounds(200,30,200,25);
        lblRelativeId.setText(idCard);
        add(lblRelativeId);

        JLabel lblRelativeNameField = new JLabel("Name");
        lblRelativeNameField.setBounds(80,70,80,25);
        add(lblRelativeNameField);

        JLabel lblRelativeName = new JLabel();
        lblRelativeName.setBounds(200,70,200,25);
        lblRelativeName.setText(name);
        add(lblRelativeName);

        JLabel lblRelativeAgeField = new JLabel("Age");
        lblRelativeAgeField.setBounds(80,110,80,25);
        add(lblRelativeAgeField);

        JLabel lblRelativeAge = new JLabel();
        lblRelativeAge.setBounds(200,110,80,25);
        lblRelativeAge.setText(age);
        add(lblRelativeAge);

        JLabel lblRelativePhoneField = new JLabel("Phone");
        lblRelativePhoneField.setBounds(80,150,80,25);
        add(lblRelativePhoneField);

        JTextField tfRelativePhone = new JTextField();
        tfRelativePhone.setBounds(200,150,200,25);
        tfRelativePhone.setText(phone);
        add(tfRelativePhone);

        JLabel lblRelativeAddressField = new JLabel("Address");
        lblRelativeAddressField.setBounds(80,190,80,25);
        add(lblRelativeAddressField);

        JTextField tfRelativeAddress = new JTextField();
        tfRelativeAddress.setBounds(200,190,200,25);
        tfRelativeAddress.setText(address);
        add(tfRelativeAddress);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(80, 230, 80, 25);
        add(lblCity);

        String stringCity = "Select," + db.getAllName("city");
        String[] city = stringCity.split(",");
        boxCity = new JComboBox(city);
        boxCity.setBounds(200, 230, 200, 25);
        boxCity.setSelectedItem(cityRel);
        boxCity.addActionListener(e -> {
            String columnName = db.getLocation("country", boxCity.getSelectedItem().toString());
            boxCountry.setSelectedItem(columnName);
            boxCountry.enable();
            if (boxCity.getSelectedIndex() != 0) {
                boxCountry.disable();
            }
        });

        add(boxCity);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(80, 270, 80, 25);
        add(lblCountry);

        String stringCountry = "Select," + db.getAllName("country");
        String[] country = stringCountry.split(",");
        boxCountry = new JComboBox(country);
        boxCountry.setBounds(200, 270, 200, 25);
        boxCountry.setSelectedItem(countryRel);
        boxCountry.disable();
        add(boxCountry);

        JLabel lblRelativeRelationshipField = new JLabel("Relationship");
        lblRelativeRelationshipField.setBounds(80,310,200,25);
        add(lblRelativeRelationshipField);

        JTextField tfRelationship = new JTextField();
        tfRelationship.setBounds(200,310,200,25);
        tfRelationship.setText(relationship);
        add(tfRelationship);

        JLabel lblPrisonerIdField = new JLabel("Prisoner ID");
        lblPrisonerIdField.setBounds(80,350,200,25);
        add(lblPrisonerIdField);

        JTextField tfPrisonerId = new JTextField();
        tfPrisonerId.setBounds(200,350,200,25);
        tfPrisonerId.setText(prisonerid);
        add(tfPrisonerId);

        JLabel lblWarn = new JLabel();
        lblWarn.setBounds(160,440,220,25);
        add(lblWarn);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(190,400,120,25);
        btnSave.addActionListener(e -> {
            try{
            phone = tfRelativePhone.getText();
            address = tfRelativeAddress.getText();
            cityRel = String.valueOf(db.getColumnID("city",boxCity.getSelectedItem().toString()));
            countryRel = String.valueOf(db.getColumnID("country",boxCountry.getSelectedItem().toString()));
            relationship = tfRelationship.getText();
            prisonerid = tfPrisonerId.getText();
            if ( phone.isEmpty()||address.isEmpty()||relationship.isEmpty()||prisonerid.isEmpty())
            {
                lblWarn.setText("Please enter all required information");
                lblWarn.setForeground(Color.red);
            }
            else if (!db.check("checkPrisonerId",prisonerid))
            {
                lblWarn.setText("Could not find prisoner");
                lblWarn.setForeground(Color.red);
            }
            else
            {
                if(db.editRelative(idCard,phone,address,cityRel,countryRel,relationship,prisonerid))
                {
                    lblWarn.setText("Save successfully");
                    lblWarn.setForeground(Color.green);
                    EditMainForm.tableRelative.setModel(db.getRelative("All",""));
                }
            }

        }catch (Exception ex)
            {
                ex.printStackTrace();
                lblWarn.setText("Please enter all required information");
                lblWarn.setForeground(Color.red);
            }
        });
        add(btnSave);



        setVisible(true);
    }
}
