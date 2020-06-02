package forms;

import models.entities.prisonerhistory;
import utils.DBConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static forms.EditMainForm.tablePrisoner;

public class EditPrisonerForm extends JDialog {
    String id = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 0).toString();
    String idCard = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 1).toString();
    String name = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 2).toString();
    String age = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 3).toString();
    String gender = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 4).toString();
    String DateOfBirth = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 5).toString();
    String DateOfArrest = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 6).toString();
    String DateOfRelease = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 7).toString();
    String crimeSelected = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 8).toString();
    String punishmentSelected = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 9).toString();
    int dangerSelected = Integer.parseInt(tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 10).toString());
    String cellroomSelected = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 11).toString();
    String address = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 12).toString();
    String city = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 13).toString();
    String country = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 14).toString();
    CardLayout card = new CardLayout();
    JLabel[] labels;
    JPanel pnlImg;

    public EditPrisonerForm() {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Prisoner Edit");
        setLayout(null);
        setBounds(300, 30, 600, 650);

        JLabel lblPrisonerIdField = new JLabel("ID");
        lblPrisonerIdField.setBounds(80, 30, 80, 25);
        add(lblPrisonerIdField);

        JLabel lblPrisonerId = new JLabel();
        lblPrisonerId.setBounds(200, 30, 200, 25);
        lblPrisonerId.setText(id);
        add(lblPrisonerId);

        JLabel lblPrisonerIdCardField = new JLabel("ID Card");
        lblPrisonerIdCardField.setBounds(80, 70, 80, 25);
        add(lblPrisonerIdCardField);

        JLabel lblPrisonerIdCard = new JLabel();
        lblPrisonerIdCard.setBounds(200, 70, 200, 25);
        lblPrisonerIdCard.setText(idCard);
        add(lblPrisonerIdCard);

        JLabel lblPrisonerNameField = new JLabel("Name");
        lblPrisonerNameField.setBounds(80, 110, 80, 25);
        add(lblPrisonerNameField);

        JLabel lblPrisonerName = new JLabel();
        lblPrisonerName.setBounds(200, 110, 200, 25);
        lblPrisonerName.setText(name);
        add(lblPrisonerName);

        JLabel lblPrisonerAgeField = new JLabel("Age");
        lblPrisonerAgeField.setBounds(80, 150, 80, 25);
        add(lblPrisonerAgeField);

        JLabel lblPrisonerAge = new JLabel();
        lblPrisonerAge.setBounds(200, 150, 200, 25);
        lblPrisonerAge.setText(age);
        add(lblPrisonerAge);

        JLabel lblPrisonerGenderField = new JLabel("Gender");
        lblPrisonerGenderField.setBounds(80, 190, 80, 25);
        add(lblPrisonerGenderField);

        JLabel lblPrisonerGender = new JLabel();
        lblPrisonerGender.setBounds(200, 190, 200, 25);
        lblPrisonerGender.setText(gender);
        add(lblPrisonerGender);

        JLabel DoBField = new JLabel("Date of Birth");
        DoBField.setBounds(80, 230, 80, 25);
        add(DoBField);

        JLabel DoB = new JLabel();
        DoB.setBounds(200, 230, 200, 25);
        DoB.setText(DateOfBirth);
        add(DoB);

        JLabel DoAField = new JLabel("Date of Arrest");
        DoAField.setBounds(80, 270, 80, 25);
        add(DoAField);

        JLabel DoA = new JLabel();
        DoA.setBounds(200, 270, 80, 25);
        DoA.setText(DateOfArrest);
        add(DoA);

        JLabel DoRField = new JLabel("Date of Release ");
        DoRField.setBounds(80, 310, 100, 25);
        add(DoRField);

        JLabel DoR = new JLabel();
        DoR.setBounds(200, 310, 80, 25);
        DoR.setText(DateOfRelease);
        add(DoR);

        DBConnection db = new DBConnection();
        JLabel lblCrime = new JLabel("Crime");
        lblCrime.setBounds(80, 350, 80, 25);
        add(lblCrime);

        String stringCrime = "Select," + db.getAllName("crime");
        String[] crime = stringCrime.split(",");
        JComboBox boxCrime = new JComboBox(crime);
        boxCrime.setSelectedItem(crimeSelected);
        boxCrime.setBounds(200, 350, 200, 25);
        add(boxCrime);

        JLabel dangerLv = new JLabel("Danger level");
        dangerLv.setBounds(80, 390, 80, 25);
        add(dangerLv);

        Integer[] dangerLevel = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        JComboBox boxDanger = new JComboBox(dangerLevel);
        boxDanger.setSelectedIndex(dangerSelected - 1);
        boxDanger.setBounds(200, 390, 200, 25);
        add(boxDanger);

        JLabel lblPunishment = new JLabel("Punishment");
        lblPunishment.setBounds(80, 430, 80, 25);
        add(lblPunishment);

        String stringPunishment = "Select," + db.getAllName("punishment");
        String[] punishment = stringPunishment.split(",");
        JComboBox boxPunishment = new JComboBox(punishment);
        boxPunishment.setSelectedItem(punishmentSelected);
        boxPunishment.setBounds(200, 430, 200, 25);
        add(boxPunishment);

        JLabel lblcellroom = new JLabel("Cell room");
        lblcellroom.setBounds(80, 470, 80, 25);
        add(lblcellroom);

        String stringCellroom = "Select," + db.getAllName("cellroom");
        String[] cellroom = stringCellroom.split(",");
        JComboBox boxCellroom = new JComboBox(cellroom);
        boxCellroom.setSelectedItem(cellroomSelected);
        boxCellroom.setBounds(200, 470, 200, 25);
        add(boxCellroom);

        pnlImg = new JPanel();
        pnlImg.setLayout(card);
        pnlImg.setBounds(350, 30, 200, 200);
        add(pnlImg);

        JButton btnNext = new JButton("");
        btnNext.setBounds(560,120,20,20);
        btnNext.addActionListener(e -> {
            card.next(pnlImg);
            pnlImg.revalidate();
            pnlImg.repaint();
        });
        add(btnNext);

        File dir = new File("src\\images\\" + idCard);
        labels = new JLabel[dir.listFiles().length];
        for (int i = 0; i < dir.listFiles().length; i++) {
            String path = "src\\images\\" + idCard + "\\" + i + ".jpg";
            try {
                Image image = ImageIO.read(new File(path));
                Image imageScaled = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                labels[i] = new JLabel();
                labels[i].setIcon(new ImageIcon(imageScaled));
                pnlImg.add(labels[i]);
                pnlImg.validate();
                pnlImg.repaint();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }


        JLabel lblWarn = new JLabel();
        lblWarn.setBounds(165, 570, 300, 25);
        add(lblWarn);

        JButton btn = new JButton("Save");
        btn.setBounds(180, 530, 120, 25);
        btn.addActionListener(e -> {

            System.out.println(Integer.parseInt(boxDanger.getSelectedItem().toString()));
            System.out.println(dangerSelected);
            if (crimeSelected.equals(boxCrime.getSelectedItem().toString()) && dangerSelected == Integer.parseInt(boxDanger.getSelectedItem().toString()) && punishmentSelected.equals(boxPunishment.getSelectedItem().toString()) && cellroomSelected.equals(boxCellroom.getSelectedItem().toString())) {
                lblWarn.setText("Save successfully");
                lblWarn.setForeground(Color.green);
            } else {
                crimeSelected = boxCrime.getSelectedItem().toString();
                dangerSelected = boxDanger.getSelectedIndex() + 1;
                punishmentSelected = boxPunishment.getSelectedItem().toString();
                cellroomSelected = boxCellroom.getSelectedItem().toString();
                if (crimeSelected.equals("Select") || dangerSelected == 0 || punishmentSelected.equals("Select") || cellroomSelected.equals("Select")) {
                    lblWarn.setText("Please select all required information");
                    lblWarn.setForeground(Color.red);
                } else {
                    Timestamp timeArrest = getTimeStamp(DoA.getText());
                    Timestamp timeRelease = calculateRelease(punishmentSelected, timeArrest);
                    String query = "update prisoner\n" +
                            "set crime = " + db.getColumnID("crime", crimeSelected) + ", dangerlevel = " + dangerSelected + ", punishment = " + db.getColumnID("punishment", punishmentSelected) +
                            ", cellroom = " + db.getColumnID("cellroom", cellroomSelected) +
                            ", dateofrelease ='" + timeRelease +
                            "' where prisonerid = " + id;
                    if (db.updatePrisoner(query)) ;
                    {
                        lblWarn.setForeground(Color.green);
                        lblWarn.setText("Save successfully");
                        prisonerhistory ph = new prisonerhistory();
                        ph.setPrisoneridcard(idCard);
                        ph.setPrisonername(name);
                        ph.setPrisonerage(Integer.parseInt(age));
                        ph.setAddress(address);
                        ph.setGender(gender);
                        ph.setCrime(db.getColumnID("crime", crimeSelected));
                        ph.setDangerlevel(dangerSelected);
                        ph.setPunishment(db.getColumnID("punishment", punishmentSelected));
                        ph.setCellroom(db.getColumnID("cellroom", cellroomSelected));
                        ph.setCity(db.getColumnID("city", city));
                        ph.setCountry(db.getColumnID("country", country));
                        ph.setDateofbirth(getTimeStamp(DoB.getText()));
                        ph.setDateofarrest(getTimeStamp(getCurrentTime()));
                        ph.setDateofrelease(timeRelease);
                        db.Create(ph);
                        this.dispose();

                    }
                }
            }
        })
        ;
        add(btn);


        setVisible(true);
    }

    public Timestamp getTimeStamp(String date) {
        try {
            Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String parsedDateStr = formatter.format(initDate);
            Date parsedDate = formatter.parse(parsedDateStr);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Timestamp calculateRelease(String punishment, Timestamp time) {
        long arrestTimeInMil = time.getTime();
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

    public String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

}

