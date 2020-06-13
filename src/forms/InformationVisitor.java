package forms;

import models.entities.Visitor;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class InformationVisitor extends JDialog {
    private JPanel panel;
    JDatePickerImpl dateVisitPicker;
    private String header[] = {"IdCard", "Name", "Gender", "PhoneNumber", "Address", "City", "Country", "Relationship"};
    private DefaultTableModel tableModel = new DefaultTableModel(header, 0);

    public InformationVisitor() throws HeadlessException {
        setDefaultCloseOperation(2);
        setTitle("Add Visitor");
        panel = (JPanel) getContentPane();
        panel.setLayout(null);

        // JLabel
        JLabel lblCMND = new JLabel("IdCard");
        lblCMND.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCMND.setBounds(148, 11, 100, 30);
        this.add(lblCMND);

        JLabel lblFullName = new JLabel("Name");
        lblFullName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblFullName.setBounds(148, 55, 102, 30);
        this.add(lblFullName);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblGender.setBounds(148, 95, 100, 30);
        this.add(lblGender);


        JLabel lblPhoneNumber = new JLabel("PhoneNumber");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPhoneNumber.setBounds(148, 135, 100, 30);
        this.add(lblPhoneNumber);


        JLabel lblAddresss = new JLabel("Address");
        lblAddresss.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAddresss.setBounds(148, 175, 100, 30);
        this.add(lblAddresss);

        JLabel lblCity = new JLabel("City");
        lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCity.setBounds(660, 11, 100, 30);
        this.add(lblCity);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCountry.setBounds(660, 80, 100, 30);
        this.add(lblCountry);

        JLabel lblrelationship = new JLabel("Relationship");
        lblrelationship.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblrelationship.setBounds(660, 142, 100, 30);
        panel.add(lblrelationship);

        // JTextField
        JTextField textFieldidcard = new JTextField();
        textFieldidcard.setBounds(275, 18, 245, 19);
        this.add(textFieldidcard);
        textFieldidcard.setColumns(10);

        JTextField textFieldName = new JTextField();
        textFieldName.setBounds(275, 62, 245, 19);
        this.add(textFieldName);
        textFieldName.setColumns(10);

        JTextField textFieldphone = new JTextField();
        textFieldphone.setBounds(275, 142, 245, 19);
        this.add(textFieldphone);
        textFieldphone.setColumns(10);

        JTextField textField_relationship = new JTextField();
        textField_relationship.setBounds(770, 149, 245, 19);
        panel.add(textField_relationship);
        textField_relationship.setColumns(10);

        JTextField textFieldsearch = new JTextField();
        textFieldsearch.setBounds(10, 236, 178, 19);
        this.add(textFieldsearch);
        textFieldsearch.setColumns(10);


        //======================
        // JCombobox
        JComboBox comboGender = new JComboBox();
        comboGender.setModel(new DefaultComboBoxModel(new String[]{"", "Female", "Male"}));
        comboGender.setBounds(275, 101, 245, 21);
        comboGender.setForeground(new Color(255, 255, 255));
        comboGender.setBackground(new Color(0, 0, 0));
        this.add(comboGender);

        //===============================================

        // Select City, Province, Country
        DBConnection db = new DBConnection();

        String STCity = "," + db.getAllName("City");
        String[] Citys = STCity.split(",");
        JComboBox comboBox_City = new JComboBox(Citys);
        JSplitPane splitCity = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCity.add(comboBox_City);
        splitCity.setBounds(770, 20, 245, 21);

        add(splitCity);
        //===============================

        //================================
        String stringCountry = "," + db.getAllName("Country");
        String[] country = stringCountry.split(",");
        JComboBox comboBox_Country = new JComboBox(country);
        JSplitPane splitCountry = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCountry.add(comboBox_Country);
        splitCountry.setBounds(770, 86, 245, 21);
        add(splitCountry);

        // JScrollPane

        JScrollPane scrollPaneAddress = new JScrollPane();
        scrollPaneAddress.setBounds(275, 181, 245, 45);
        JTextPane textPaneAddress = new JTextPane();
        scrollPaneAddress.setViewportView(textPaneAddress);
        this.add(scrollPaneAddress);


        JScrollPane scrollPane_table = new JScrollPane();
        scrollPane_table.setBounds(10, 271, 1170, 158);
        this.add(scrollPane_table);

        //JTable

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Idcard", "Name", "Gender", "PhoneNumber", "Address", "City", "Country", "Relationship"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, Object.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        scrollPane_table.setViewportView(table);

        // JButton
        JButton btnAdd = new JButton("Add ");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnAdd.setBackground(new Color(152, 251, 152));
        btnAdd.setBounds(851, 196, 93, 30);
        this.add(btnAdd);
        btnAdd.addActionListener(e -> {
            String Idcard = textFieldidcard.getText();
            String FullName = textFieldName.getText();
            String Gender = comboGender.getSelectedItem().toString();
            String PhoneNumber = textFieldphone.getText();
            String Address = textPaneAddress.getText();
            int City = db.getColumnID("city", comboBox_City.getSelectedItem().toString());
            int Country = db.getColumnID("country", comboBox_Country.getSelectedItem().toString());
            String Relationship = textField_relationship.getText();
            Visitor visitor = new Visitor();
            visitor.setVisitoridcard(Idcard);
            visitor.setVisitorname(FullName);
            visitor.setVisitorphone(PhoneNumber);
            visitor.setVisitoraddress(Address);
            visitor.setCity(City);
            visitor.setCountry(Country);
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "insert into visitor(visitorid,visitorname,gender,vistorphone,visitoraddress,city,country,relationship)  values(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, Idcard);
                preparedStatement.setString(2, FullName);
                preparedStatement.setString(3, Gender);
                preparedStatement.setString(4, PhoneNumber);
                preparedStatement.setString(5, Address);
                preparedStatement.setString(6, String.valueOf(City));
                preparedStatement.setString(7, String.valueOf(Country));
                preparedStatement.setString(8, Relationship);
                if (!Idcard.equals("")) {
                    if (!FullName.equals("")) {
                        if (!Gender.equals("")) {
                            if (!PhoneNumber.equals("")) {
                                if (!Address.equals("")) {
//                                        if (City.setS){
//                                                if (!Country.equals("")){
                                    if (CheckIdcard(Idcard)) {
                                        if (CheckPhone(PhoneNumber)) {
                                            preparedStatement.execute();
                                            JOptionPane.showMessageDialog(rootPane, "Add Success");
                                            textFieldidcard.setText("");
                                            textFieldName.setText("");
                                            comboGender.setSelectedItem("");
                                            ;
                                            textFieldphone.setText("");
                                            textPaneAddress.setText("");
                                            comboBox_City.setSelectedItem("");
                                            comboBox_Country.setSelectedItem("");
                                            textField_relationship.setText("");
                                        } else {
                                            JOptionPane.showMessageDialog(rootPane, "PhoneNumber was available");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(rootPane, "CMND was available");
                                    }
//                                                }else {
//                                                    JOptionPane.showMessageDialog(rootPane,"Wrong Country");
//                                                }
//                                        }else {
//                                            JOptionPane.showMessageDialog(rootPane,"Wrong City");
//                                        }
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Wrong Address");
                                }
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Wrong PhoneNumber");
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Wrong Gender");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Wrong FullName");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Wrong CMND");
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRefresh.setBackground(new Color(175, 238, 238));
        btnRefresh.setBounds(1092, 439, 85, 21);
        this.add(btnRefresh);
        btnRefresh.addActionListener(e -> {
            textFieldsearch.setText("");
            String Search = textFieldsearch.getText();
            Connection c = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456");
                // command watch data
                String sql = "select visitorid, visitorname, gender, vistorphone, visitoraddress, cityname, countryname, relationship" + " from visitor join city on visitor.city=cityid join country on visitor.country=countryid";
                //if search by title
                if (Search.length() > 0) {
                    sql = sql + " where visitorid like '%" + Search + "%'";
                }
                //create object thu thi command select
                st = c.createStatement();
                //thu thi
                rs = st.executeQuery(sql);
                Vector data = null;
                tableModel.setRowCount(0);
                // if relatives aren't available
                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Relatives aren't available");
                    return;
                }
                //while not get data
                while (rs.next()) {
                    data = new Vector();
                    data.add(rs.getString("visitorid"));
                    data.add(rs.getString("visitorname"));
                    data.add(rs.getString("gender"));
                    data.add(rs.getString("vistorphone"));
                    data.add(rs.getString("visitoraddress"));
                    data.add(rs.getString("cityname"));
                    data.add(rs.getString("countryname"));
                    data.add(rs.getString("relationship"));
                    // add one row in table model
                    tableModel.addRow(data);
                }
                //add data in JTable
                table.setModel(tableModel);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    if (c != null) {
                        c.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }

        });

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSearch.setBounds(212, 236, 85, 21);
        this.add(btnSearch);
        btnSearch.addActionListener(e -> {
            String Search = textFieldsearch.getText();
            Connection c = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456");
                // command watch data
                String sql = "select * from visitor";
                //if search by title
                if (Search.length() > 0) {
                    sql = sql + " where visitorid like '%" + Search + "%'";
                }
                //create object thu thi command select
                st = c.createStatement();
                //thu thi
                rs = st.executeQuery(sql);
                Vector data = null;
                tableModel.setRowCount(0);
                // if relatives aren't available
                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Relatives aren't available");
                    return;
                }
                //while not get data
                while (rs.next()) {
                    data = new Vector();
                    data.add(rs.getString("visitorid"));
                    data.add(rs.getString("visitorname"));
                    data.add(rs.getString("gender"));
                    data.add(rs.getString("vistorphone"));
                    data.add(rs.getString("visitoraddress"));
                    data.add(rs.getString("city"));
                    data.add(rs.getString("country"));
                    data.add(rs.getString("relationship"));
                    // add one row in table model
                    tableModel.addRow(data);
                }
                //add data in JTable
                table.setModel(tableModel);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    if (c != null) {
                        c.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }

        });
        //============================
        panel.setBackground(new Color(148, 0, 211));
        setBounds(30, 70, 1201, 505);
        setVisible(true);
    }

    //Check CMND
    public boolean CheckIdcard(String Idcard) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            Statement stmt = con.createStatement();
            String query = "SELECT visitorid FROM visitor WHERE visitorid ='" + Idcard + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean CheckPhone(String PhoneNumber) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            Statement stmt = con.createStatement();
            String query = "SELECT vistorphone FROM visitor WHERE vistorphone ='" + PhoneNumber + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

