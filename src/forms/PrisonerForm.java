package forms;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import models.entities.prisoner;
import models.entities.prisonerhistory;
import models.entities.relative;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.*;

public class PrisonerForm extends JDialog {
    JTextField tfName, tfAge, tfAddress, tfRelativeName, tfRelativeAge, tfRelativeAddress, tfRelativePhone, tfRelationship, tfPrisonerId;
    public JTextField tfIdCard, tfRelativeIDCard;
    JButton btnSave, btnAdd;
    JComboBox boxGender, boxCity, boxCountry, boxCrime, boxPunishment, boxDanger, boxRelativeCity, boxRelativeCountry, boxCellroom;
    JDatePickerImpl dateBirthPicker, dateArrestPicker;
    JPanel pnlImg;
    JLabel lblWarn, lblLastId, lblWarnRel;
    File[] selectedFiles;
    JLabel[] labels;
    CardLayout card = new CardLayout();

    public PrisonerForm() {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Information Details");
        setBounds(200, 100, 800, 600);

        JPanel tabInfo = new JPanel();
        tabInfo.setLayout(null);
        JLabel lblId = new JLabel("Prisoner ID");
        lblId.setBounds(30, 30, 80, 25);
        tabInfo.add(lblId);

        DBConnection db = new DBConnection();
        int lastestId = db.getLastId() + 1;
        lblLastId = new JLabel(String.valueOf(lastestId));
        lblLastId.setBounds(150, 30, 50, 25);
        tabInfo.add(lblLastId);

        JLabel lblIdCard = new JLabel("ID Card");
        lblIdCard.setBounds(30, 70, 80, 25);
        tabInfo.add(lblIdCard);

        tfIdCard = new JTextField();
        tfIdCard.setBounds(150, 70, 200, 25);
        tabInfo.add(tfIdCard);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(370, 70, 80, 25);
        tabInfo.add(btnSearch);
        btnSearch.addActionListener(this::searchPrisoner);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(30, 110, 80, 25);
        tabInfo.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(150, 110, 200, 25);
        tabInfo.add(tfName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(30, 150, 80, 25);
        tabInfo.add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(150, 150, 200, 25);
        tabInfo.add(tfAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(30, 190, 200, 25);
        tabInfo.add(lblGender);

        String[] gender = {"Select", "Male", "Female"};
        boxGender = new JComboBox(gender);
        boxGender.setBounds(150, 190, 200, 25);
        tabInfo.add(boxGender);

        JLabel lblDoB = new JLabel("Date of Birth");
        lblDoB.setBounds(30, 230, 80, 25);
        tabInfo.add(lblDoB);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl dateBirthPanel = new JDatePanelImpl(model, p);
        dateBirthPicker = new JDatePickerImpl(dateBirthPanel, new DateLabelFormatter());
        dateBirthPicker.setBounds(150, 230, 200, 25);
        tabInfo.add(dateBirthPicker);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(30, 270, 80, 25);
        tabInfo.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(150, 270, 200, 25);
        tabInfo.add(tfAddress);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(30, 310, 80, 25);
        tabInfo.add(lblCity);
        String stringCity = "";
        if (db.checkTable("city")) {
             stringCity = "Select," + db.getAllName("city");
        }
        String[] city = stringCity.split(",");
        boxCity = new JComboBox(city);
        boxCity.setBounds(150, 310, 200, 25);
        boxCity.addActionListener(e -> {
            String columnName = db.getLocation("country", boxCity.getSelectedItem().toString());
            boxCountry.setSelectedItem(columnName);
            boxCountry.enable();
            if (boxCity.getSelectedIndex() != 0) {
                boxCountry.disable();
            }
        });
        tabInfo.add(boxCity);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(30, 350, 80, 25);
        tabInfo.add(lblCountry);
        String stringCountry = "";
        if(db.checkTable("country")) {
            stringCountry = "Select," + db.getAllName("country");
        }
        String[] country = stringCountry.split(",");
        boxCountry = new JComboBox(country);
        boxCountry.setBounds(150, 350, 200, 25);
        tabInfo.add(boxCountry);

        pnlImg = new JPanel();
        pnlImg.setLayout(card);
        pnlImg.setBounds(480, 10, 250, 250);
        Border border = BorderFactory.createLineBorder(Color.gray);
        pnlImg.setBorder(border);
        tabInfo.add(pnlImg);


        JButton btnUpload = new JButton("Upload image");
        btnUpload.setBounds(530, 280, 150, 25);
        btnUpload.addActionListener(this::uploadImg);
        tabInfo.add(btnUpload);

        JButton nextCard = new JButton("Next");
        nextCard.setBounds(710, 280, 60, 25);
        tabInfo.add(nextCard);
        nextCard.addActionListener(this::nextCard);

        btnSave = new JButton("Save");
        btnSave.setBounds(340, 450, 100, 25);
        btnSave.addActionListener(this::save);
        tabInfo.add(btnSave);

        lblWarn = new JLabel();
        lblWarn.setBounds(330, 485, 300, 25);
        tabInfo.add(lblWarn);

        JPanel tabCrime = new JPanel();
        tabCrime.setLayout(null);

        JLabel lblCrime = new JLabel("Crime");
        lblCrime.setBounds(30, 30, 80, 25);
        tabCrime.add(lblCrime);
        String stringCrime = "";
        if( db.checkTable("crime")) {
             stringCrime = "Select," + db.getAllName("crime");
        }
        String[] crime = stringCrime.split(",");
        boxCrime = new JComboBox(crime);
        boxCrime.setBounds(150, 30, 200, 25);
        tabCrime.add(boxCrime);

        JLabel dangerLv = new JLabel("Danger level");
        dangerLv.setBounds(30, 70, 80, 25);
        tabCrime.add(dangerLv);

        Integer[] dangerLevel = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boxDanger = new JComboBox(dangerLevel);
        boxDanger.setBounds(150, 70, 200, 25);
        tabCrime.add(boxDanger);

        JLabel lblPunishment = new JLabel("Punishment");
        lblPunishment.setBounds(30, 110, 80, 25);
        tabCrime.add(lblPunishment);
        String stringPunishment = "";
        if (db.checkTable("punishment")) {
            stringPunishment = "Select," + db.getAllName("punishment");
        }
        String[] punishment = stringPunishment.split(",");
        boxPunishment = new JComboBox(punishment);
        boxPunishment.setBounds(150, 110, 200, 25);
        tabCrime.add(boxPunishment);

        JLabel lblDoA = new JLabel("Date of Arrest");
        lblDoA.setBounds(30, 150, 80, 25);
        tabCrime.add(lblDoA);

        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl dateArrestPanel = new JDatePanelImpl(model2, p);
        dateArrestPicker = new JDatePickerImpl(dateArrestPanel, new DateLabelFormatter());
        dateArrestPicker.setBounds(150, 150, 200, 25);
        tabCrime.add(dateArrestPicker);

        JLabel lblcellroom = new JLabel("Cell room");
        lblcellroom.setBounds(30, 190, 80, 25);
        tabCrime.add(lblcellroom);
        String stringCellroom = "";
        if (db.checkTable("cellroom")) {
            stringCellroom = "Select," + db.getAllName("cellroom");
        }
        String[] cellroom = stringCellroom.split(",");
        boxCellroom = new JComboBox(cellroom);
        boxCellroom.setBounds(150, 190, 200, 25);
        tabCrime.add(boxCellroom);

        ///Tab Relative
        JPanel tabRelavtive = new JPanel();
        tabRelavtive.setLayout(null);
        JLabel lblRelativeIDCard = new JLabel("ID Card");
        lblRelativeIDCard.setBounds(30, 30, 80, 25);
        tabRelavtive.add(lblRelativeIDCard);

        tfRelativeIDCard = new JTextField();
        tfRelativeIDCard.setBounds(150, 30, 200, 25);
        tabRelavtive.add(tfRelativeIDCard);

        JButton btnRelativeSearch = new JButton("Search");
        btnRelativeSearch.setBounds(370, 30, 80, 25);
        btnRelativeSearch.addActionListener(this::searchRelative);
        tabRelavtive.add(btnRelativeSearch);


        JLabel lblRelativeName = new JLabel("Name");
        lblRelativeName.setBounds(30, 70, 80, 25);
        tabRelavtive.add(lblRelativeName);

        tfRelativeName = new JTextField();
        tfRelativeName.setBounds(150, 70, 200, 25);
        tabRelavtive.add(tfRelativeName);

        JLabel lblRelativeAge = new JLabel("Age");
        lblRelativeAge.setBounds(30, 110, 80, 25);
        tabRelavtive.add(lblRelativeAge);

        tfRelativeAge = new JTextField();
        tfRelativeAge.setBounds(150, 110, 200, 26);
        tabRelavtive.add(tfRelativeAge);

        JLabel lblRelativePhone = new JLabel("Phone ");
        lblRelativePhone.setBounds(30, 150, 80, 25);
        tabRelavtive.add(lblRelativePhone);

        tfRelativePhone = new JTextField();
        tfRelativePhone.setBounds(150, 150, 200, 25);
        tabRelavtive.add(tfRelativePhone);

        JLabel lblRelativeAddress = new JLabel("Address");
        lblRelativeAddress.setBounds(30, 190, 80, 25);
        tabRelavtive.add(lblRelativeAddress);

        tfRelativeAddress = new JTextField();
        tfRelativeAddress.setBounds(150, 190, 200, 25);
        tabRelavtive.add(tfRelativeAddress);

        JLabel lblRelativeCity = new JLabel("City");
        lblRelativeCity.setBounds(30, 230, 80, 25);
        tabRelavtive.add(lblRelativeCity);

        boxRelativeCity = new JComboBox(city);
        boxRelativeCity.setBounds(150, 230, 200, 25);
        boxRelativeCity.addActionListener(e -> {
            String columnName = db.getLocation("country", boxRelativeCity.getSelectedItem().toString());
            boxRelativeCountry.setSelectedItem(columnName);
            boxRelativeCountry.enable();
            if (boxRelativeCity.getSelectedIndex() != 0) {
                boxRelativeCountry.disable();
            }


        });
        tabRelavtive.add(boxRelativeCity);

        JLabel lblRelativeCountry = new JLabel("Country");
        lblRelativeCountry.setBounds(30, 270, 80, 25);
        tabRelavtive.add(lblRelativeCountry);

        boxRelativeCountry = new JComboBox(country);
        boxRelativeCountry.setBounds(150, 270, 200, 25);
        tabRelavtive.add(boxRelativeCountry);

        JLabel lblRelationship = new JLabel("Relationship");
        lblRelationship.setBounds(30, 310, 80, 25);
        tabRelavtive.add(lblRelationship);

        tfRelationship = new JTextField();
        tfRelationship.setBounds(150, 310, 200, 25);
        tabRelavtive.add(tfRelationship);

        JLabel lblPrisonerId = new JLabel("Prisoner ID");
        lblPrisonerId.setBounds(30, 350, 80, 25);
        tabRelavtive.add(lblPrisonerId);

        tfPrisonerId = new JTextField();
        tfPrisonerId.setBounds(150, 350, 200, 25);
        tabRelavtive.add(tfPrisonerId);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(300, 420, 80, 25);
        btnAdd.addActionListener(this::addRelative);
        tabRelavtive.add(btnAdd);

        lblWarnRel = new JLabel();
        lblWarnRel.setBounds(280, 460, 300, 25);
        tabRelavtive.add(lblWarnRel);

        JTabbedPane tp = new JTabbedPane();
        tp.add("Information", tabInfo);
        tp.add("Crime", tabCrime);
        tp.add("Relative", tabRelavtive);
        add(tp);



        setVisible(true);
    }

    public void searchRelative(ActionEvent e) {
        RelativeSearchForm rsf = new RelativeSearchForm(this, "Relative Detail", true);
    }

    public void searchPrisoner(ActionEvent e) {
        PrisonerSearchForm psf = new PrisonerSearchForm(this, "Prisoner history", true);
    }

    public void nextCard(ActionEvent e) {
        card.next(pnlImg);
        pnlImg.validate();
        pnlImg.repaint();
    }

    public void save(ActionEvent e) {
        DBConnection db = new DBConnection();
        try {
            String idcard = tfIdCard.getText();
            String name = tfName.getText();
            int age = Integer.parseInt(tfAge.getText());
            String gender = boxGender.getSelectedItem().toString();
            Timestamp DoB = getTimeStamp(dateBirthPicker.getJFormattedTextField().getText());
            Timestamp DoA = getTimeStamp(dateArrestPicker.getJFormattedTextField().getText());
            Timestamp DoR = calculateRelease(boxPunishment.getSelectedItem().toString());
            int crime = db.getColumnID("crime", boxCrime.getSelectedItem().toString());
            int dangerlevel = Integer.parseInt(boxDanger.getSelectedItem().toString());
            int punishment = db.getColumnID("punishment", boxPunishment.getSelectedItem().toString());
            int cellroom = db.getColumnID("cellroom", boxCellroom.getSelectedItem().toString());
            String address = tfAddress.getText();
            int city = db.getColumnID("city", boxCity.getSelectedItem().toString());
            int country = db.getColumnID("country", boxCountry.getSelectedItem().toString());
            if (idcard.isEmpty() || name.isEmpty() || gender.equals("Select") || address.isEmpty()) {
                lblWarn.setText("Please enter all required information");
                lblWarn.setForeground(Color.red);
            } else if (labels[0].getParent() == null) {
                lblWarn.setText("Please upload images");
                lblWarn.setForeground(Color.red);
            } else {
                prisoner p1 = new prisoner();
                prisonerhistory ph1 = new prisonerhistory();
                p1.setPrisoneridcard(idcard);
                p1.setPrisonername(name);
                p1.setPrisonerage(age);
                p1.setGender(gender);
                p1.setDateofbirth(DoB);
                p1.setDateofarrest(DoA);
                p1.setDateofrelease(DoR);
                p1.setCrime(crime);
                p1.setDangerlevel(dangerlevel);
                p1.setPunishment(punishment);
                p1.setCellroom(cellroom);
                p1.setAddress(address);
                p1.setCity(city);
                p1.setCountry(country);

                ph1.setPrisoneridcard(idcard);
                ph1.setPrisonername(name);
                ph1.setPrisonerage(age);
                ph1.setGender(gender);
                ph1.setDateofbirth(DoB);
                ph1.setDateofarrest(DoA);
                ph1.setDateofrelease(DoR);
                ph1.setCrime(crime);
                ph1.setDangerlevel(dangerlevel);
                ph1.setPunishment(punishment);
                ph1.setCellroom(cellroom);
                ph1.setAddress(address);
                ph1.setCity(city);
                ph1.setCountry(country);

                if (db.Create(p1) && db.Create(ph1)) {
                    for (int i = 0; i < selectedFiles.length; i++) {
                        File dir = new File("src\\images\\" + tfIdCard.getText());
                        dir.mkdirs();
                        File file = new File(selectedFiles[i].getAbsolutePath());
                        file.renameTo(new File("src\\images\\" + tfIdCard.getText() + "\\" + i + ".jpg"));
                    }
                    int lastId = db.getLastId() +1;
                    lblLastId.setText(String.valueOf(lastId));
                    tfIdCard.setText(null);
                    tfName.setText(null);
                    tfAge.setText(null);
                    boxGender.setSelectedIndex(0);
                    dateBirthPicker.getModel().setValue(null);
                    dateArrestPicker.getModel().setValue(null);
                    boxCrime.setSelectedIndex(0);
                    boxDanger.setSelectedIndex(0);
                    boxPunishment.setSelectedIndex(0);
                    boxCellroom.setSelectedIndex(0);
                    tfAddress.setText(null);
                    boxCity.setSelectedIndex(0);
                    boxCountry.setSelectedIndex(0);
                    pnlImg.removeAll();
                    pnlImg.repaint();
                    pnlImg.revalidate();
                    pnlImg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    lblWarn.setText("Save successfully");
                    lblWarn.setForeground(Color.green);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblWarn.setText("Please enter all required information");
            lblWarn.setForeground(Color.red);
        }

    }

    public void uploadImg(ActionEvent e) {
        try {
            /// UI window for file chooser
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView());
            fc.setMultiSelectionEnabled(true);
            // back to UI swing
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            /// add filter for image only
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file", "jpg", "jpeg", "png");
            fc.addChoosableFileFilter(filter);
            fc.setAcceptAllFileFilterUsed(false);
            int result = fc.showOpenDialog(getParent());

            if (result == JFileChooser.APPROVE_OPTION) {
                pnlImg.removeAll();
                //create array
                selectedFiles = fc.getSelectedFiles();
                labels = new JLabel[selectedFiles.length];
                for (int i = 0; i < selectedFiles.length; i++) {
                    Image image = ImageIO.read(selectedFiles[i]);
                    /// scaled image
                    Image imageScaled = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    pnlImg.setBorder(null);
                    labels[i] = new JLabel();
                    labels[i].setIcon(new ImageIcon(imageScaled));
                    pnlImg.add(labels[i]);
                    pnlImg.validate();
                    pnlImg.repaint();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void addRelative(ActionEvent e) {
        try {
            DBConnection db = new DBConnection();
            String idcard = tfRelativeIDCard.getText();
            String name = tfRelativeName.getText();
            int age = Integer.parseInt(tfRelativeAge.getText());
            String phone = tfRelativePhone.getText();
            String address = tfRelativeAddress.getText();
            int city = db.getColumnID("city", boxRelativeCity.getSelectedItem().toString());
            int country = db.getColumnID("country", boxRelativeCountry.getSelectedItem().toString());
            String relationship = tfRelationship.getText();
            int prisonerid = Integer.parseInt(tfPrisonerId.getText().toString());
            if (idcard.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || relationship.isEmpty() || prisonerid == 0) {
                lblWarnRel.setText("Please enter all required information");
                lblWarnRel.setForeground(Color.red);
            } else if (!db.check("checkPrisonerId", String.valueOf(prisonerid))) {

                lblWarnRel.setText("Could not find prisoner");
                lblWarnRel.setForeground(Color.red);

            } else if (db.check("checkRelativeIdCard", idcard)) {
                lblWarnRel.setText("Relative has already existed");
                lblWarnRel.setForeground(Color.red);
            } else {
                relative r1 = new relative();
                r1.setRelativeidcard(idcard);
                r1.setRelativename(name);
                r1.setRelativeage(age);
                r1.setRelativephone(phone);
                r1.setRelativeaddress(address);
                r1.setCity(city);
                r1.setCountry(country);
                r1.setRelationship(relationship);
                r1.setPrisonerid(prisonerid);
                if (db.Create(r1)) {
                    if (db.updateRelativePrisoner(String.valueOf(prisonerid), String.valueOf(db.callProc("findrelativeid", idcard)))) {
                        lblWarnRel.setText("Save successfully");
                        lblWarnRel.setForeground(Color.green);
                        tfRelativeIDCard.setText(null);
                        tfRelativeName.setText(null);
                        tfRelativeAge.setText(null);
                        tfRelativePhone.setText(null);
                        tfRelativeAddress.setText(null);
                        tfRelationship.setText(null);
                        tfPrisonerId.setText(null);
                        boxRelativeCity.setSelectedIndex(0);
                        boxRelativeCountry.setSelectedIndex(0);



                    }
                }
            }

        } catch (Exception ex) {
            lblWarnRel.setText("Please enter all required information");
            lblWarnRel.setForeground(Color.red);
            ex.printStackTrace();
        }

    }

    public Timestamp getTimeStamp(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Timestamp calculateRelease(String punishment) {
        long arrestTimeInMil = getTimeStamp(dateArrestPicker.getJFormattedTextField().getText()).getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(arrestTimeInMil);
        if (!punishment.equals("Chung thân")) {
            String[] part = punishment.split("\\s+");
            int intPart = Integer.parseInt(part[0]);
            if (part[1].equals("năm")) {
                cal.add(Calendar.YEAR, intPart);

            } else if (part[1].equals("tháng")) {
                cal.add(Calendar.MONTH, intPart);
            } else {
                cal.add(Calendar.DATE, intPart);
            }
        } else {
            cal.add(Calendar.YEAR, 200);
        }

        long releaseTimeInMil = cal.getTimeInMillis();
        Timestamp result = new Timestamp(releaseTimeInMil);

        return result;
    }
}


