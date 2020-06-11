package forms;

import models.entities.VisitsChedule;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class VisitsCheduleForm extends JFrame {
    DefaultTableModel model;
    private ArrayList<VisitsChedule> list;
    private JTable table;
    private JTextField tftSearch;
    private JComboBox bs;

    public VisitsCheduleForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("VisitsChedule Form");
        setBounds(400, 230, 600, 350);
        setLayout(null);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.DARK_GRAY);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 586, 313);
        contentPane.add(tabbedPane);

        JMenuBar mnb = new JMenuBar();
        setJMenuBar(mnb);

        JMenu menu = new JMenu("Menu");
        mnb.add(menu);
        JMenuItem mni = new JMenuItem("Logout");
        menu.add(mni);
        mni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainForm mf = new MainForm();
            }
        });

        //----------------VisitCheduleForm----------------------

        JPanel panelVC = new JPanel();
        tabbedPane.addTab("VisitChedule", null, panelVC, null);
        panelVC.setLayout(null);
        panelVC.setBackground(Color.DARK_GRAY);

        JLabel lb1 = new JLabel("VISIT CHEDULE");
        lb1.setBounds(210, 20, 350, 40);
        lb1.setFont(new Font("UTM Aircona", Font.PLAIN, 20));
        lb1.setForeground(Color.YELLOW);
        panelVC.add(lb1);

        JPanel panelAdd = new JPanel();
        tabbedPane.addTab("Add", null, panelAdd, null);
        panelAdd.setBackground(Color.DARK_GRAY);
        panelAdd.setLayout(null);

        JButton btSearch = new JButton("Search");
        btSearch.setBounds(410, 60, 90, 20);
        panelVC.add(btSearch);
        btSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getListVisitSearch(bs.getSelectedItem().toString());
                showTableSearch();
            }
        });

        tftSearch = new JTextField();
        tftSearch.setEditable(false);
        tftSearch.setBounds(260, 60, 130, 19);
        panelVC.add(tftSearch);

        String[] cbSearch = {"All", "prisonerid", "visitorid"};
        bs = new JComboBox(cbSearch);
        bs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tftSearch.setText("");
                if (!bs.getSelectedItem().equals("All")) {
                    tftSearch.setEditable(true);
                } else {
                    tftSearch.setEditable(false);
                }
            }
        });
        bs.setBounds(100, 60, 150, 19);
        panelVC.add(bs);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 80, 510, 118);
        panelVC.add(scrollPane);
        scrollPane.setBackground(Color.DARK_GRAY);

        table = new JTable();
        table.setBackground(Color.DARK_GRAY);
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        table.setForeground(Color.white);
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                },
                new String[]{
                        "VisittorID", "VisitDate", "PrisonerID"
                }
        ));
        scrollPane.setViewportView(table);
        getListVisit();
        showTable();

//----------------AddVisitCheduleForm----------------------

        JLabel lblTitle = new JLabel("ADD VISIT CHEDULE");
        lblTitle.setBounds(200, 20, 350, 40);
        lblTitle.setFont(new Font("UTM Aircona", Font.PLAIN, 20));
        lblTitle.setForeground(Color.YELLOW);
        panelAdd.add(lblTitle);

        JLabel lblVisitorID = new JLabel("VisitorID:");
        lblVisitorID.setBounds(180, 77, 60, 13);
        panelAdd.add(lblVisitorID);
        lblVisitorID.setForeground(Color.white);

        JTextField tftVisitorID = new JTextField();
        tftVisitorID.setBounds(265, 74, 150, 19);
        panelAdd.add(tftVisitorID);
        tftVisitorID.setColumns(10);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JLabel lblVisit = new JLabel("VisitDate:");
        lblVisit.setBounds(170, 113, 60, 13);
        panelAdd.add(lblVisit);
        lblVisit.setForeground(Color.white);

        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl dateOfBirth = new JDatePanelImpl(model1, p);
        JDatePickerImpl dateArrestPicker = new JDatePickerImpl(dateOfBirth, new DateLabelFormatter());
        dateArrestPicker.setBounds(265, 108, 150, 19);
        panelAdd.add(dateArrestPicker);

        JLabel lblPrisonerid = new JLabel("PrisonerID:");
        lblPrisonerid.setBounds(170, 145, 70, 13);
        panelAdd.add(lblPrisonerid);
        lblPrisonerid.setForeground(Color.white);

        JTextField tftPrisonerID = new JTextField();
        tftPrisonerID.setColumns(10);
        tftPrisonerID.setBounds(265, 142, 150, 19);
        panelAdd.add(tftPrisonerID);


        JButton btnDone = new JButton("Done");
        btnDone.setBounds(246, 186, 85, 21);
        panelAdd.add(btnDone);
        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String visitorID = tftVisitorID.getText();
                Timestamp visitDate = getTimeStamp(dateArrestPicker.getJFormattedTextField().getText());
                Integer prisonerID = Integer.parseInt(tftPrisonerID.getText());
                VisitsChedule vc = new VisitsChedule();
                vc.setVisitorid(visitorID);
                vc.setVisitdate(visitDate);
                vc.setPrisonerid(prisonerID);
                DBConnection db = new DBConnection();
                if (db.checkprisonerID("checkprisonerid", visitorID) == prisonerID) {
                    if (db.check("checkrelative", visitorID)) {
                        int check = db.checkRelative(visitorID);
                        if(prisonerID != null){
                            if(check == prisonerID){
                                if (db.check("checkPrisonerId", String.valueOf(prisonerID))) {
                                    Timestamp lastVisitDate = db.checkVisitDate(visitorID);
                                    if (lastVisitDate == null) {
                                         db.Create(vc);
                                        JOptionPane.showMessageDialog(dateArrestPicker, "Add Success !!");
                                        getListVisit();
                                        showTable();
                                    }
                                    long lastTime = lastVisitDate.getTime();
                                    long currentTime = visitDate.getTime();
                                    long limitTime = 2592000000L;

                                    if (currentTime - lastTime >= limitTime) {
                                        db.Create(vc);
                                        JOptionPane.showMessageDialog(dateArrestPicker, "Add Success !!");
                                        getListVisit();
                                        showTable();
                                    } else {
                                        JOptionPane.showMessageDialog(dateArrestPicker, "Not enough time yet !!");
                                    }
                                }
                            }else {
                                JOptionPane.showMessageDialog(dateArrestPicker, "PrisonerID incorrect !!");
                            }
                        }else {
                            JOptionPane.showMessageDialog(dateArrestPicker, "VisitorID incorrect !!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(dateArrestPicker, "This visitor is not a relative of the prisoner with id " + prisonerID + " !!");
                }
            }
        });
        setVisible(true);
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

    public void showTable() {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        list = getListVisit();
        for (VisitsChedule l : list) {
            model.addRow(new Object[]{
                    l.getVisitorid(), l.getVisitdate(), l.getPrisonerid()
            });
        }
    }

    public ArrayList<VisitsChedule> getListVisit() {
        ArrayList<VisitsChedule> list = new ArrayList<>();
        String sql = "select * from visitschedule ";

        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VisitsChedule l = new VisitsChedule();
                l.setVisitorid(rs.getString("VisitorID"));
                l.setVisitdate(rs.getTimestamp("VisitDate"));
                l.setPrisonerid(rs.getInt("PrisonerID"));
                list.add(l);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public ArrayList<VisitsChedule> getListVisitSearch(String search) {
        ArrayList<VisitsChedule> listSearch = new ArrayList<>();
        String sql = "";
        if (search.equals("All")) {
            sql = "select * from visitschedule";

        } else if (search.equals("prisonerid")) {
            sql = "select * from visitschedule where prisonerid = " + tftSearch.getText();
        } else if (search.equals("visitorid")) {
            sql = "select * from visitschedule where visitorid = '" + tftSearch.getText() + "'";
        }
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(connectionUrl);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                VisitsChedule l = new VisitsChedule();
                l.setVisitorid(rs.getString("VisitorID"));
                l.setVisitdate(rs.getTimestamp("VisitDate"));
                l.setPrisonerid(rs.getInt("PrisonerID"));
                listSearch.add(l);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return listSearch;
    }

    public void showTableSearch() {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        list = getListVisitSearch(bs.getSelectedItem().toString());
        for (VisitsChedule l : list) {
            model.addRow(new Object[]{
                    l.getVisitorid(), l.getVisitdate(), l.getPrisonerid()
            });
        }
    }

    public boolean isCellEditable(int row, int col)/// lam bang k chinh sua dc
    {
        return false;
    }
}
