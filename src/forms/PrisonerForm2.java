package forms;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.*;
public class PrisonerForm2 extends JDialog {
    JTextField tfName,tfAge,tfAddress,tfRelativeName,tfRelativeAge,tfRelativeAddress,tfRelativePhone,tfRelationship;
    public JTextField tfIdCard,tfRelativeIDCard;
    JButton btnSave;
    JComboBox boxGender,boxCity,boxCountry,boxCrime,boxPunishment,boxDanger,boxRelativeCity,boxRelativeCountry;
    JDatePickerImpl dateBirthPicker,dateArrestPicker;
    JPanel pnlImg;
    JLabel lblWarn;
    JLabel lblLastId;
    CardLayout card = new CardLayout();
    public PrisonerForm2() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Prisoner Details");
        setBounds(200,100,800,600);

        JPanel tabInfo = new JPanel();
        tabInfo.setLayout(null);
        JLabel lblId = new JLabel("Prisoner ID");
        lblId.setBounds(30,30,80,25);
        tabInfo.add(lblId);

        DBConnection db = new DBConnection();
        int lastestId = db.getLastId()+1;
        lblLastId = new JLabel(String.valueOf(lastestId));
        lblLastId.setBounds(150,30,50,25);
        tabInfo.add(lblLastId);

        JLabel lblIdCard  = new JLabel("ID Card");
        lblIdCard.setBounds(30,70,80,25);
        tabInfo.add(lblIdCard);

        tfIdCard = new JTextField();
        tfIdCard.setBounds(150,70,200,25);
        tabInfo.add(tfIdCard);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(370,70,80,25);
        tabInfo.add(btnSearch);
        btnSearch.addActionListener(this::searchPrisoner);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(30,110,80,25);
        tabInfo.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(150,110,200,25);
        tabInfo.add(tfName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setBounds(30,150,80,25);
        tabInfo.add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(150,150,200,25);
        tabInfo.add(tfAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(30,190,200,25);
        tabInfo.add(lblGender);

        String[] gender = {"Select","Male","Female"};
        boxGender = new JComboBox(gender);
        JSplitPane splitGender = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitGender.add(boxGender);
        splitGender.setBounds(150,190,210,25);
        tabInfo.add(splitGender);

        JLabel lblDoB = new JLabel("Date of Birth");
        lblDoB.setBounds(30,230,80,25);
        tabInfo.add(lblDoB);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl dateBirthPanel = new JDatePanelImpl(model,p);
        dateBirthPicker = new JDatePickerImpl(dateBirthPanel, new DateLabelFormatter());
        dateBirthPicker.setBounds(150,230,200,25);
        tabInfo.add(dateBirthPicker);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(30,270,80,25);
        tabInfo.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(150,270,200,25);
        tabInfo.add(tfAddress);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(30,310,80,25);
        tabInfo.add(lblCity);

        String stringCity = "Select,"+ db.getAllName("city");
        String[] city = stringCity.split(",");
        boxCity = new JComboBox(city);
        JSplitPane splitCity = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCity.add(boxCity);
        splitCity.setBounds(150,310,210,25);
        tabInfo.add(splitCity);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(30,350,80,25);
        tabInfo.add(lblCountry);

        String stringCountry = "Select,"+db.getAllName("country");
        String[] country = stringCountry.split(",");
        boxCountry = new JComboBox(country);
        JSplitPane splitCountry = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCountry.add(boxCountry);
        splitCountry.setBounds(150,350,210,25);
        tabInfo.add(splitCountry);

        pnlImg = new JPanel();
        pnlImg.setLayout(card);
        pnlImg.setBounds(480,10,250,250);
        Border border = BorderFactory.createLineBorder(Color.gray);
        pnlImg.setBorder(border);
        tabInfo.add(pnlImg);



        JButton btnUpload = new JButton("Upload image");
        btnUpload.setBounds(530,280,150,25);
        btnUpload.addActionListener(this::uploadImg);
        tabInfo.add(btnUpload);

        JButton nextCard = new JButton("Next");
        nextCard.setBounds(710,280,60,25);
        tabInfo.add(nextCard);
        nextCard.addActionListener(this::nextCard);

        btnSave = new JButton("Save");
        btnSave.setBounds(340,450,100,25);
        tabInfo.add(btnSave);

        JPanel tabCrime = new JPanel();
        tabCrime.setLayout(null);;

        JLabel lblCrime = new JLabel("Crime");
        lblCrime.setBounds(30,30,80,25);
        tabCrime.add(lblCrime);

        String stringCrime = "Select,"+db.getAllName("crime");
        String[] crime = stringCrime.split(",");
        boxCrime = new JComboBox(crime);
        JSplitPane splitCrime = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCrime.add(boxCrime);
        splitCrime.setBounds(150,30,210,25);
        tabCrime.add(splitCrime);

        JLabel dangerLv = new JLabel("Danger level");
        dangerLv.setBounds(30,70,80,25);
        tabCrime.add(dangerLv);

        Integer [] dangerLevel = {1,2,3,4,5,6,7,8,9,10};
        boxDanger = new JComboBox(dangerLevel);
        JSplitPane splitDanger = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitDanger.add(boxDanger);
        splitDanger.setBounds(150,70,210,25);
        tabCrime.add(splitDanger);

        JLabel lblPunishment = new JLabel("Punishment");
        lblPunishment.setBounds(30,110,80,25);
        tabCrime.add(lblPunishment);

        String stringPunishment = "Select,"+db.getAllName("punishment");
        String[] punishment = stringPunishment.split(",");
        boxPunishment = new JComboBox(punishment);
        JSplitPane splitPunishment = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPunishment.add(boxPunishment);
        splitPunishment.setBounds(150,110,210,25);
        tabCrime.add(splitPunishment);

        ///Tab Relative\
        JPanel tabRelavtive = new JPanel();
        tabRelavtive.setLayout(null);
        JLabel lblRelativeIDCard = new JLabel("ID Card");
        lblRelativeIDCard.setBounds(30,30,80,25);
        tabRelavtive.add(lblRelativeIDCard);

        tfRelativeIDCard = new JTextField();
        tfRelativeIDCard.setBounds(150,30,200,25);
        tabRelavtive.add(tfRelativeIDCard);

        JButton btnRelativeSearch = new JButton("Search");
        btnRelativeSearch.setBounds(370,30,80,25);
        btnRelativeSearch.addActionListener(this::searchRelative);
        tabRelavtive.add(btnRelativeSearch);


        JLabel lblRelativeName = new JLabel("Name");
        lblRelativeName.setBounds(30,70,80,25);
        tabRelavtive.add(lblRelativeName);

        tfRelativeName = new JTextField();
        tfRelativeName.setBounds(150,70,200,25);
        tabRelavtive.add(tfRelativeName);

        JLabel lblRelativeAge = new JLabel("Age");
        lblRelativeAge.setBounds(30,110,80,25);
        tabRelavtive.add(lblRelativeAge);

        tfRelativeAge = new JTextField();
        tfRelativeAge.setBounds(150,110,200,26);
        tabRelavtive.add(tfRelativeAge);

        JLabel lblRelativePhone = new JLabel("Phone ");
        lblRelativePhone.setBounds(30,150,80,25);
        tabRelavtive.add(lblRelativePhone);

        tfRelativePhone = new JTextField();
        tfRelativePhone.setBounds(150,150,200,25);
        tabRelavtive.add(tfRelativePhone);

        JLabel lblRelativeAddress = new JLabel("Address");
        lblRelativeAddress.setBounds(30,190,80,25);
        tabRelavtive.add(lblRelativeAddress);

        tfRelativeAddress = new JTextField();
        tfRelativeAddress.setBounds(150,190,200,25);
        tabRelavtive.add(tfRelativeAddress);

        JLabel lblRelativeCity = new JLabel("City");
        lblRelativeCity.setBounds(30,230,80,25);
        tabRelavtive.add(lblRelativeCity);

        boxRelativeCity = new JComboBox(city);
        boxRelativeCity.setBounds(150,230,200,25);
        tabRelavtive.add(boxRelativeCity);

        JLabel lblRelativeCountry = new JLabel("Country");
        lblRelativeCountry.setBounds(30,270,80,25);
        tabRelavtive.add(lblRelativeCountry);

        boxRelativeCountry = new JComboBox(country);
        boxRelativeCountry.setBounds(150,270,200,25);
        tabRelavtive.add(boxRelativeCountry);

        JLabel lblRelationship = new JLabel("Relatiobship");
        lblRelationship.setBounds(30,310,80,25);
        tabRelavtive.add(lblRelationship);

        tfRelationship = new JTextField();
        tfRelationship.setBounds(150,310,200,25);
        tabRelavtive.add(tfRelationship);






        JTabbedPane tp = new JTabbedPane();
        tp.add("Information",tabInfo);
        tp.add("Crime",tabCrime);
        tp.add("Relative",tabRelavtive);
        add(tp);


        setVisible(true);
    }
    public void searchRelative(ActionEvent e){
        RelativeSearchForm rsf = new RelativeSearchForm(this,"Relative Detail",true);
    }
    public void searchPrisoner(ActionEvent e){
        PrisonerSearchForm psf = new PrisonerSearchForm(this,"Prisoner history",true);

    }
    public void nextCard(ActionEvent e){
        card.next(pnlImg);
        pnlImg.validate();
        pnlImg.repaint();
    }
    public void uploadImg (ActionEvent e){
        try
        {
            /// UI window for file chooser
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView());
            fc.setMultiSelectionEnabled(true);
            // back to UI swing
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            /// add filter for image only
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file","jpg","jpeg","png");
            fc.addChoosableFileFilter(filter);
            fc.setAcceptAllFileFilterUsed(false);
            int result = fc.showOpenDialog(getParent());

            if(result == JFileChooser.APPROVE_OPTION)
            {
                pnlImg.removeAll();
                //create array
                File [] selectedFiles = fc.getSelectedFiles();
                JLabel[] labels = new JLabel[selectedFiles.length];
                for ( int i = 0 ; i< selectedFiles.length;i++) {
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

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
