package forms;

import utils.DBConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class EditMainForm extends JDialog {
    JTextField tfSearchRelative;
    JComboBox  boxRelativeSelect;
    public static JTable tableRelative,tablePrisoner;
    CardLayout card = new CardLayout();
    JLabel[] labels;
    JPanel pnlImg;
    String idcard;
    File dir;
    public EditMainForm() throws HeadlessException {
        setModal(true);
        setTitle("Prisoner/Relative List");
        setDefaultCloseOperation(2);
        setBounds(100,100,1400,600);
        DBConnection db = new DBConnection();

        JTabbedPane tp = new JTabbedPane();
        JPanel prisoner = new JPanel();

        JPanel relative = new JPanel();
        relative.setLayout(new BorderLayout());
        Border line = BorderFactory.createLineBorder(Color.BLACK);

        JPanel pnlSearchRelative = new JPanel();
        pnlSearchRelative.setLayout(null);
        pnlSearchRelative.setPreferredSize(new Dimension(1,200));
        pnlSearchRelative.setBorder(line);

        tfSearchRelative = new JTextField();
        tfSearchRelative.setBounds(380,60,250,30);
        pnlSearchRelative.add(tfSearchRelative);

        String[] relativeSelect = {"Select","ID Card","Prisoner ID","Name"};
        boxRelativeSelect = new JComboBox(relativeSelect);
        boxRelativeSelect.setBounds(650,60,150,30);
        pnlSearchRelative.add(boxRelativeSelect);

        JButton btnSearchRelative = new JButton("Search");
        btnSearchRelative.setBounds(530,130,120,30);
        btnSearchRelative.addActionListener(this::searchRelative);
        pnlSearchRelative.add(btnSearchRelative);

        relative.add(pnlSearchRelative,BorderLayout.NORTH);

        JPanel pnlResultRelative = new JPanel();
        pnlResultRelative.setLayout(new BorderLayout());
        pnlResultRelative.setBorder(line);

        tableRelative = new JTable();
        tableRelative.addMouseListener(new PopClickListenerRelative());
        tableRelative.setModel(db.getRelative("All",""));
        JScrollPane spRelative = new JScrollPane(tableRelative);
        spRelative.setPreferredSize(new Dimension(1,300));
        pnlResultRelative.add(spRelative,BorderLayout.NORTH);

        relative.add(pnlResultRelative,BorderLayout.SOUTH);

        prisoner.setLayout(new BorderLayout());
        JPanel pnlSearchPrisoner = new JPanel();
        pnlSearchPrisoner.setLayout(null);
        pnlSearchPrisoner.setPreferredSize(new Dimension(1,250));
        pnlSearchPrisoner.setBorder(line);

        JLabel lblPrisonerId = new JLabel("Prisoner ID");
        lblPrisonerId.setBounds(30,30,80,25);
        pnlSearchPrisoner.add(lblPrisonerId);

        JTextField tfPrisonerId = new JTextField();
        tfPrisonerId.setBounds(100,30,200,25);
        pnlSearchPrisoner.add(tfPrisonerId);

        JLabel lblPrisonerIdCard = new JLabel("ID Card");
        lblPrisonerIdCard.setBounds(30,70,80,25);
        pnlSearchPrisoner.add(lblPrisonerIdCard);

        JTextField tfPrisonerIdCard = new JTextField();
        tfPrisonerIdCard.setBounds(100,70,200,25);
        pnlSearchPrisoner.add(tfPrisonerIdCard);

        JLabel lblPrisonerName = new JLabel("Name");
        lblPrisonerName.setBounds(30,110,80,25);
        pnlSearchPrisoner.add(lblPrisonerName);

        JTextField tfPrisonerName = new JTextField();
        tfPrisonerName.setBounds(100,110,200,25);
        pnlSearchPrisoner.add(tfPrisonerName);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(30,150,80,25);
        pnlSearchPrisoner.add(lblGender);

        String[] stringGender = {"Select","Male","Female"};
        JComboBox boxGender = new JComboBox(stringGender);
        boxGender.setBounds(100,150,200,25);
        pnlSearchPrisoner.add(boxGender);

        String[] intDanger ={"Danger level","1","2","3","4","5","6","7","8","9","10"};
        JComboBox boxDanger = new JComboBox(intDanger);
        boxDanger.setBounds(400,30,200,25);
        pnlSearchPrisoner.add(boxDanger);

        JButton btnFind = new JButton("Find");
        btnFind.setBounds(475,180,120,25);
        String stringCrime = "";
        if (db.checkTable("crime")) {
            stringCrime = "Crime," + db.getAllName("crime");
        }
        String[] crime = stringCrime.split(",");
        JComboBox boxCrime = new JComboBox(crime);
        boxCrime.setBounds(400, 70, 200, 25);
        pnlSearchPrisoner.add(boxCrime);

        String stringPunishment = "";
        if(db.checkTable("punishment")) {
            stringPunishment = "Punishment," + db.getAllName("punishment");
        }
        String[] punishment = stringPunishment.split(",");
        JComboBox boxPunishment = new JComboBox(punishment);
        boxPunishment.setBounds(400, 110, 200, 25);
        pnlSearchPrisoner.add(boxPunishment);

        String stringCellroom = "";
        if( db.checkTable("cellroom")) {
            stringCellroom = "Cellroom," + db.getAllName("cellroom");
        }
        String[] cellroom = stringCellroom.split(",");
        JComboBox boxCellroom = new JComboBox(cellroom);
        boxCellroom.setBounds(700,30,200,25);
        pnlSearchPrisoner.add(boxCellroom);
        String stringCity = "";
        if (db.checkTable("city")) {
             stringCity = "City," + db.getAllName("city");
        }
        String[] city = stringCity.split(",");
        JComboBox boxCity = new JComboBox(city);
        boxCity.setBounds(700, 70, 200, 25);
        pnlSearchPrisoner.add(boxCity);

        String stringCountry = "";
        if(db.checkTable("country")) {
             stringCountry = "Country," + db.getAllName("country");
        }
        String[] country = stringCountry.split(",");
        JComboBox boxCountry = new JComboBox(country);
        boxCountry.setBounds(700, 110, 200, 25);
        pnlSearchPrisoner.add(boxCountry);

        pnlImg = new JPanel();
        pnlImg.setLayout(card);
        pnlImg.setBounds(1000,25,200,200);
        pnlImg.setBorder(line);
        pnlSearchPrisoner.add(pnlImg);

        JButton btnNext = new JButton(">");
        btnNext.setBounds(1220,135,45,25);
        btnNext.addActionListener(this::nextCard);
        pnlSearchPrisoner.add(btnNext);


        prisoner.add(pnlSearchPrisoner,BorderLayout.NORTH);

        JPanel pnlResultPrisoner = new JPanel();
        pnlResultPrisoner.setPreferredSize(new Dimension(1,275));
        pnlResultPrisoner.setLayout(new BorderLayout());
        pnlResultPrisoner.setBorder(line);

        btnFind.addActionListener(e -> {
            String id = tfPrisonerId.getText();
            String idcard = tfPrisonerIdCard.getText() ;
            String name =tfPrisonerName.getText();
            String gender = boxGender.getSelectedItem().toString();
            String danger = boxDanger.getSelectedItem().toString();
            String crimeSelected = boxCrime.getSelectedItem().toString();
            String punishmentSelected = boxPunishment.getSelectedItem().toString();
            String cellroomSelected = boxCellroom.getSelectedItem().toString();
            String citySelected = boxCity.getSelectedItem().toString();
            String countrySelected = boxCountry.getSelectedItem().toString();
            String query = "select prisonerid,prisoneridcard,prisonername,prisonerage,gender,convert(nvarchar,dateofbirth,103) as dateofbirth,convert(nvarchar,dateofarrest,103) as dateofarrest,convert(nvarchar,dateofrelease,103) as dateofrelease,crimename,dangerlevel,punishmentname,cellroomname,address,cityname,countryname\n" +
                    "from prisoner join crime on crime= crimeid\n" +
                    "join punishment on punishment = punishmentid\n" +
                    "join cellroom on cellroom = cellroomid\n" +
                    "join city on city = cityid\n"+
                    "join country on prisoner.country = countryid\n"+
                    "where ";
            String result ="";
            if (!id.isEmpty())
            {
                query+= " prisonerid = "+id+" AND";
            }
            if (!idcard.isEmpty())
            {
                query+= " prisoneridcard = '"+idcard+"' AND";
            }
            if (!name.isEmpty())
            {
                query+= " prisonername = N'"+name+"' AND";
            }
            if(!gender.equals("Select"))
            {
                query+= " gender = N'"+gender+"' AND";
            }
            if(!danger.equals("Danger level"))
            {
                query+= " dangerlevel = "+danger+" AND";
            }
            if(!crimeSelected.equals("Crime"))
            {
                query+=" crimename = N'"+crimeSelected+"' AND";
            }
            if(!punishmentSelected.equals("Punishment"))
            {
                query+=" punishmentname = N'"+punishmentSelected+"' AND";
            }
            if(!cellroomSelected.equals("Cellroom")){
                query+=" cellroomname = N'"+cellroomSelected+"' AND" ;
            }
            if(!citySelected.equals("City"))
            {
                query+=" cityname = N'"+citySelected+"' AND";
            }
            if(!countrySelected.equals("Country"))
            {
                query+=" countryname = N'"+countrySelected+"' AND";
            }
            if (query.substring(query.length()-6).equals("where "))
            {
                result = query.substring(0,query.length()-6);
            }
            else {
                result = query.substring(0, query.length() - 3);
            }
            tablePrisoner.setModel(db.findPrisoner("",result));

        });
        pnlSearchPrisoner.add(btnFind);

         tablePrisoner = new JTable(){
            // show tooltip
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {



                        JComponent jc = (JComponent) c;
                        jc.setToolTipText(getValueAt(row, column).toString());

                }
                return c;
            }
        };
        tablePrisoner.setModel(db.findPrisoner("All",""));
        tablePrisoner.addMouseListener(new PopClickListenerPrisoner());
        tablePrisoner.getSelectionModel().addListSelectionListener(e -> {
          idcard = "";
          try {
              idcard = tablePrisoner.getValueAt(tablePrisoner.getSelectedRow(), 1).toString();
          } catch (Exception e1)
          {
              e1.printStackTrace();
          }
            dir = new File("src\\images\\"+idcard);
            if(dir.exists())
            {
                findImg();
            }
        });
        JScrollPane spPrisoner = new JScrollPane(tablePrisoner);
        pnlResultPrisoner.add(spPrisoner,BorderLayout.NORTH);

        prisoner.add(pnlResultPrisoner,BorderLayout.SOUTH);
        tp.add("Prisoner",prisoner);
        tp.add("Relative",relative);
        add(tp);
        setVisible(true);

    }
    public void searchRelative(ActionEvent e)
    {
        DBConnection db = new DBConnection();
        String select = boxRelativeSelect.getSelectedItem().toString();
        String value = tfSearchRelative.getText();
        tableRelative.setModel(db.getRelative(select,value));
    }
    public void nextCard(ActionEvent e) {
        card.next(pnlImg);
        pnlImg.validate();
        pnlImg.repaint();
    }
    public void findImg(){{
        dir = new File("src\\images\\"+idcard);
        labels = new JLabel[dir.listFiles().length];
        pnlImg.removeAll();
        for (int i = 0; i<dir.listFiles().length;i++) {
            String path = "src\\images\\"+idcard+"\\"+i+".jpg";
            try {
                Image image = ImageIO.read(new File(path));
                Image imageScaled = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                pnlImg.setBorder(null);
                labels[i] = new JLabel();
                labels[i].setIcon(new ImageIcon(imageScaled));
                pnlImg.add(labels[i]);
                pnlImg.validate();
                pnlImg.repaint();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }}
    }
}
