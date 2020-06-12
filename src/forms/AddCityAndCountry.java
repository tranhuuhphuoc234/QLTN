package forms;

import models.entities.city;
import models.entities.country;
import utils.DBConnection;

import javax.swing.*;

public class AddCityAndCountry extends JDialog {
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
            country c = new country();
            c.setCountryname(contryname);
            db.Create(c);
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
        String strCountry = db.getAllName("country");
        String [] arrCountry = strCountry.split(",");
        JComboBox boxCountry = new JComboBox(arrCountry);
        boxCountry.setBounds(200,70,200,25);
        pnlCity.add(boxCountry);

        JButton btnAddCity = new JButton("Add");
        btnAddCity.setBounds(160,120,120,25);
        btnAddCity.addActionListener(e -> {
            String cityname = tfCityName.getText();
            int country = db.getColumnID("country",boxCountry.getSelectedItem().toString());
            city c = new city();
            c.setCityname(cityname);
            c.setCountry(country);
            db.Create(c);
        });

        pnlCity.add(btnAddCity);

        tabbedPane.add(pnlCity,"City");
        add(tabbedPane);
        setVisible(true);
    }
}
