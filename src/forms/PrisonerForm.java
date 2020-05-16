package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import models.entities.prisoner;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.*;

public class PrisonerForm extends JDialog {
        JTextField tfName,tfAge, tfPhone, tfAddress;
        JButton btnSave;
        JComboBox boxGender,boxCity,boxCountry,boxCrime,boxPunishment;
        JDatePickerImpl dateBirthPicker,dateArrestPicker;
        JLabel lblWarn;
        JLabel lblLastId;
public PrisonerForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Prisoner Detail");
        setBounds(200,100,800,600);
        setLayout(null);

        JLabel lblId = new JLabel("Prisoner ID");
        lblId.setBounds(30,30,80,25);
        add(lblId);

        DBConnection db = new DBConnection();
        int lastestId = db.getLastId()+1;
         lblLastId = new JLabel(String.valueOf(lastestId));
        lblLastId.setBounds(150,30,50,25);
        add(lblLastId);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(30,70,80,25);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(150,70,200,25);
        add(tfName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(30,110,80,25);
        add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(150,110,200,25);
        add(tfAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(30,150,200,25);
        add(lblGender);

        String[] gender = {"Select","Male","Female"};
        boxGender = new JComboBox(gender);
        JSplitPane splitGender = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitGender.add(boxGender);
        splitGender.setBounds(150,150,210,25);
        add(splitGender);

        JLabel lblDoB = new JLabel("Date of Birth");
        lblDoB.setBounds(30,190,80,25);
        add(lblDoB);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl dateBirthPanel = new JDatePanelImpl(model,p);
        dateBirthPicker = new JDatePickerImpl(dateBirthPanel, new DateLabelFormatter());
        dateBirthPicker.setBounds(150,190,200,25);
        add(dateBirthPicker);

        JLabel lblDoA = new JLabel("Date of Arrest");
        lblDoA.setBounds(30,230,80,25);
        add(lblDoA);

        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl dateArrestPanel = new JDatePanelImpl(model2,p);
        dateArrestPicker = new JDatePickerImpl(dateArrestPanel,new DateLabelFormatter());
        dateArrestPicker.setBounds(150,230,200,25);
        add(dateArrestPicker);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(30,270,80,25);
        add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(150,270,200,25);
        add(tfPhone);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(30,310,80,25);
        add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(150,310,200,25);
        add(tfAddress);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(30,350,80,25);
        add(lblCity);

        String stringCity = "Select,"+ db.getAllName("city");
        String[] city = stringCity.split(",");
        boxCity = new JComboBox(city);
        JSplitPane splitCity = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCity.add(boxCity);
        splitCity.setBounds(150,350,210,25);
        add(splitCity);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(30,390,80,25);
        add(lblCountry);

        String stringCountry = "Select,"+db.getAllName("country");
        String[] country = stringCountry.split(",");
        boxCountry = new JComboBox(country);
        JSplitPane splitCountry = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCountry.add(boxCountry);
        splitCountry.setBounds(150,390,210,25);
        add(splitCountry);

        JLabel lblCrime = new JLabel("Crime");
        lblCrime.setBounds(30,430,80,25);
        add(lblCrime);

        String stringCrime = "Select,"+db.getAllName("crime");
        String[] crime = stringCrime.split(",");
        boxCrime = new JComboBox(crime);
        JSplitPane splitCrime = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCrime.add(boxCrime);
        splitCrime.setBounds(150,430,210,25);
        add(splitCrime);

        JLabel lblPunishment = new JLabel("Punishment");
        lblPunishment.setBounds(30,470,80,25);
        add(lblPunishment);

        String stringPunishment = "Select,"+db.getAllName("punishment");
        String[] punishment = stringPunishment.split(",");
        boxPunishment = new JComboBox(punishment);
        JSplitPane splitPunishment = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPunishment.add(boxPunishment);
        splitPunishment.setBounds(150,470,210,25);
        add(splitPunishment);

        btnSave = new JButton("Save");
        btnSave.setBounds(530,390,80,25);
        btnSave.addActionListener(this::actionPerformed);
        add(btnSave);
        lblWarn = new JLabel();
        lblWarn.setBounds(480,430,240,25);
        add(lblWarn);

        setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
        String name = tfName.getText();
        String age = tfAge.getText();
        String gender = boxGender.getSelectedItem().toString();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String city = boxCity.getSelectedItem().toString();
        String country = boxCountry.getSelectedItem().toString();
        String crime = boxCrime.getSelectedItem().toString();
        String punishment = boxPunishment.getSelectedItem().toString();
        Timestamp DoB = getTimeStamp(dateBirthPicker.getJFormattedTextField().getText()) ;
        Timestamp DoA = getTimeStamp(dateArrestPicker.getJFormattedTextField().getText());
        if ( name.isEmpty() || gender.equals("Select") || age.isEmpty() || phone.isEmpty() ||address.isEmpty()||city.equals("Select")||
        country.equals("Select")||crime.equals("Select")||punishment.equals("Select")||DoA==null || DoB==null){
                lblWarn.setText("Please enter all required information");
                lblWarn.setForeground(Color.red);
        }
        else{
                DBConnection db = new DBConnection();
                prisoner p1 = new prisoner();
                p1.setPrisonername(name);
                p1.setPrisonerage(Integer.parseInt(age));
                p1.setGender(gender);
                p1.setPhone(phone);
                p1.setAddress(address);
                p1.setCity(db.getColumnID("city",city));
                p1.setCountry(db.getColumnID("country",country));
                p1.setCrime(db.getColumnID("crime",crime));
                p1.setPunishment(db.getColumnID("punishment",punishment));
                p1.setDateofbirth(DoB);
                p1.setDateofarrest(DoA);
                db.Create(p1);
                lblWarn.setBounds(520,430,240,25);
                lblWarn.setText("Save successfully");
                lblWarn.setForeground(Color.green);
                lblLastId.setText(String.valueOf(db.getLastId()));

                tfName.setText(null);
                tfAge.setText(null);
                tfPhone.setText(null);
                tfAddress.setText(null);
                boxCity.setSelectedIndex(0);
                boxPunishment.setSelectedIndex(0);
                boxCrime.setSelectedIndex(0);
                boxGender.setSelectedIndex(0);
                boxCountry.setSelectedIndex(0);
                dateArrestPicker.getModel().setValue(null);
                dateBirthPicker.getModel().setValue(null);
        }

        }
        // convert string to time stamp
        public Timestamp getTimeStamp(String date){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(date);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                return timestamp;
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        }



