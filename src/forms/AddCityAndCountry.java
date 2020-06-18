package forms;

import models.entities.city;
import models.entities.country;
import utils.DBConnection;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.lang.reflect.Array;

public class AddCityAndCountry extends JDialog {
    String strCountry = "";
    JComboBox boxCountry;
    String [] arrCountry;

    public AddCityAndCountry(){
        setTitle("Add City/Country");
        setModal(true);
        setDefaultCloseOperation(2);
        setBounds(100,100,500,300);
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel pnlCountry = new JPanel();
        pnlCountry.setLayout(null);
        JLabel countryName = new JLabel("Country Name");
        countryName.setBounds(80,60,160,25);
        pnlCountry.add(countryName);

        JTextField tfCountryName = new JTextField();
        tfCountryName.setBounds(200,60,200,25);
        pnlCountry.add(tfCountryName);

        JButton btnAddCountry = new JButton("Add");
        btnAddCountry.setBounds(180,110,120,25);
        btnAddCountry.addActionListener(e -> {
            DBConnection db = new DBConnection();
            String contryname = tfCountryName.getText();
            if ( !contryname.isEmpty()) {
                country c = new country();
                c.setCountryname(contryname);
                if (db.Create(c)){
                    JOptionPane.showMessageDialog(rootPane,"Save successfully");
                    tfCountryName.setText(null);
                }
            }
            else{
                JOptionPane.showMessageDialog(rootPane,"Please enter all required information");

            }
        });
        pnlCountry.add(btnAddCountry);

        tabbedPane.add(pnlCountry,"Country");
        JPanel pnlCity = new JPanel();
        pnlCity.setLayout(null);

        JLabel cityName = new JLabel("City Name");
        cityName.setBounds(80,40,120,25);
        pnlCity.add(cityName);

        JTextField tfCityName = new JTextField();
        tfCityName.setBounds(200,40,200,25);
        pnlCity.add(tfCityName);

        JLabel lblCountryName= new JLabel("Country Name");
        lblCountryName.setBounds(80,70,120,25);
        pnlCity.add(lblCountryName);

        DBConnection db = new DBConnection();
        if(db.checkTable("country")) {
            strCountry = "Select,"+db.getAllName("country");
        }
        arrCountry = strCountry.split(",");
        boxCountry = new JComboBox(arrCountry);
        boxCountry.setBounds(200,70,200,25);
        pnlCity.add(boxCountry);

        JButton btnAddCity = new JButton("Add");
        btnAddCity.setBounds(160,120,120,25);
        btnAddCity.addActionListener(e -> {
            String cityname = tfCityName.getText();
            if ( !cityname.isEmpty()||boxCountry.getSelectedItem().equals("Select")) {
                int country = db.getColumnID("country", boxCountry.getSelectedItem().toString());
                city c = new city();
                c.setCityname(cityname);
                c.setCountry(country);
                if ( db.Create(c)){
                    JOptionPane.showMessageDialog(rootPane,"Save successfully");
                    tfCityName.setText(null);
                    boxCountry.setSelectedIndex(0);
                }

            }
            else {
                JOptionPane.showMessageDialog(rootPane,"Please enter all required information");

            }


        });

        pnlCity.add(btnAddCity);
        tabbedPane.add(pnlCity,"City");
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                DBConnection db = new DBConnection();
                String strArr = db.getAllName("country");
                String [] Arr = strArr.split(",");
                boxCountry.setModel( new DefaultComboBoxModel(Arr));
            }
        });
        add(tabbedPane);

        setVisible(true);
    }
}

