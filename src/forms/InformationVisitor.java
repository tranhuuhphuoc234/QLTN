package forms;

import models.entities.Visitor;
import utils.DBConnection;
import forms.DateLabelFormatter;
import models.entities.Visitor;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import static java.lang.Boolean.*;

public class InformationVisitor extends JFrame {
    private JPanel panel;
    JDatePickerImpl dateVisitPicker;
    private String header[]={ "IdCard", "Name", "Gender", "PhoneNumber", "Address", "City", "Country"};
    private DefaultTableModel tableModel= new DefaultTableModel(header,0);

    public InformationVisitor() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        JLabel lblDateofvisit = new JLabel("Date of Visit");
        lblDateofvisit.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDateofvisit.setBounds(150, 135, 100, 30);
        this.add(lblDateofvisit);

        JLabel lblPhoneNumber = new JLabel("PhoneNumber");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPhoneNumber.setBounds(150, 175, 100, 30  );
        this.add(lblPhoneNumber);

        JLabel lblprovince = new JLabel(" Province");
        lblprovince.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblprovince.setBounds(660, 80, 100, 30);
        this.add(lblprovince);

        JLabel lblAddresss = new JLabel("Address");
        lblAddresss.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAddresss.setBounds(150, 215, 100, 30);
        this.add(lblAddresss);

        JLabel lblCity = new JLabel("City");
        lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCity.setBounds(660, 11, 100, 30);
        this.add(lblCity);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCountry.setBounds(660, 145, 100, 30);
        this.add(lblCountry);



        // JTextField
        JTextField textFieldCMND = new JTextField();
        textFieldCMND.setBounds(275, 18, 245, 19);
        this.add(textFieldCMND);
        textFieldCMND.setColumns(10);

        JTextField textFieldName = new JTextField();
        textFieldName.setBounds(275, 62, 245, 19);
        this.add(textFieldName);
        textFieldName.setColumns(10);

        JTextField textFieldphone = new JTextField();
        textFieldphone.setBounds(275, 182, 245, 19);
        this.add(textFieldphone);
        textFieldphone.setColumns(10);

        JTextField textFieldsearch = new JTextField();
        textFieldsearch.setBounds(10, 290, 178, 19);
        this.add(textFieldsearch);
        textFieldsearch.setColumns(10);

        // DATE

        Properties p = new Properties();
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl dateVisitPanel = new JDatePanelImpl(model, p);
        dateVisitPicker = new JDatePickerImpl(dateVisitPanel, new DateLabelFormatter());
        dateVisitPicker.setBounds(275, 142, 245, 27                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             );
        add(dateVisitPicker);

        //======================
        // JCombobox
        JComboBox comboGender = new JComboBox();
        comboGender.setModel(new DefaultComboBoxModel(new String[] {"", "Female", "Male"}));
        comboGender.setBounds(275, 101, 245, 21);
        comboGender.setForeground(new Color(255, 255, 255));
        comboGender.setBackground(new Color(0, 0, 0));
        this.add(comboGender);

        //===============================================

        // Select City, Province, Country
        DBConnection db = new DBConnection();

        String STCity = ","+db.getAllName("City");
        String[] Citys = STCity.split(",");
        JComboBox comboBox_City= new JComboBox(Citys);
        JSplitPane splitCity = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCity.add(comboBox_City);
        splitCity.setBounds(770, 20, 245, 21);

        add(splitCity);
        //===============================
        String STProvince = ","+db.getAllName("Province");
        String[] PV = STProvince.split(",");
        JComboBox comboBox_Province = new JComboBox(PV);
        JSplitPane splitProvince= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitProvince.add(comboBox_Province);
        splitProvince.setBounds(770, 86, 245, 21);
        add(splitProvince);

        //================================
        String stringCountry = ","+db.getAllName("Country");
        String[] country = stringCountry.split(",");
        JComboBox comboBox_Country = new JComboBox(country);
        JSplitPane splitCountry = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCountry.add(comboBox_Country);
        splitCountry.setBounds(770, 151, 245, 21);
        add(splitCountry);

        // JScrollPane

        JScrollPane scrollPaneAddress = new JScrollPane();
        scrollPaneAddress.setBounds(275, 221, 245, 45);
        JTextPane textPaneAddress = new JTextPane();
        scrollPaneAddress.setViewportView(textPaneAddress);
        this.add(scrollPaneAddress);


        JScrollPane scrollPane_table = new JScrollPane();
        scrollPane_table.setBounds(10, 319, 1170, 158);
        this.add(scrollPane_table);

        //JTable

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "CMND", "FullName", "Gender", "Date of Visit", "PhoneNumber", "Address", "City","Province", "Country"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, String.class, Object.class, Object.class, String.class, String.class, String.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        scrollPane_table.setViewportView(table);
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                //String date="yyyy-MM-dd";
//                int i = table.getSelectedRow();
//                TableModel model1=table.getModel();
//                textFieldCMND.setText(model1.getValueAt(i,0).toString());
//                textFieldName.setText(model1.getValueAt(i,1).toString());
//                comboGender.setSelectedItem(model1.getValueAt(i,2).toString());
//                dateVisitPicker.getModel().setDay(i);
//                textFieldphone.setText(model1.getValueAt(i,4).toString());
//                textPaneAddress.setText(model1.getValueAt(i,5).toString());
//                comboBox_City.setSelectedItem(model1.getValueAt(i,6).toString());
//                comboBox_Province.setSelectedItem(model1.getValueAt(i,7).toString());
//                comboBox_Country.setSelectedItem(model1.getValueAt(i,8).toString());
//            }
//        });

        // JButton
        JButton btnAdd = new JButton("Add ");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnAdd.setBackground(new Color(152, 251, 152));
        btnAdd.setBounds(850, 215, 93, 30);
        this.add(btnAdd);
        btnAdd.addActionListener(e -> {
            String CMND=textFieldCMND.getText();
            String FullName=textFieldName.getText();
            String Gender=comboGender.getSelectedItem().toString();
            Timestamp DOV;
            DOV = getTimeStamp(dateVisitPicker.getJFormattedTextField().getText());
            String PhoneNumber=textFieldphone.getText();
            String Address=textPaneAddress.getText();
            String City=comboBox_City.getSelectedItem().toString();
            String Province=comboBox_Province.getSelectedItem().toString();
            String Country=comboBox_Country.getSelectedItem().toString();
            Visitor visitor = new Visitor();
            visitor.setVisitoridcard(CMND);
            visitor.setVisitorname(FullName);
            visitor.setVisitorphone(PhoneNumber);
            visitor.setVisitoraddress(Address);
            visitor.setCity(db.getColumnID("city",City));
            visitor.setCountry(db.getColumnID("conuntry",Country));
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=VisitorManage;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "insert into Visitor(CMND,FullName,Gender,DateofVisit,PhoneNumber,Address,City,Province,Country)  values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, CMND);
                preparedStatement.setString(2, FullName);
                preparedStatement.setString(3,Gender);
                preparedStatement.setTimestamp(4, DOV);
                preparedStatement.setString(5,PhoneNumber );
                preparedStatement.setString(6, Address);
                preparedStatement.setString(7,City);
                preparedStatement.setString(8,Province);
                preparedStatement.setString(9,Country);
                if (!CMND.equals("")){
                    if (!FullName.equals("")){
                        if(!Gender.equals("")){
                            if (!DOV.equals(null)){
                                if (!PhoneNumber.equals("")) {
                                    if (!Address.equals("")){
                                        if (!City.equals("")){
                                            if (!Province.equals("")){
                                                if (!Country.equals("")){
                                                    if (CheckCMND(CMND)){
                                                        if (CheckPhone(PhoneNumber)){
                                                            preparedStatement.executeUpdate();
                                                            JOptionPane.showMessageDialog(rootPane,"Add Success");
                                                            textFieldCMND.setText("");
                                                            textFieldName.setText("");
                                                            comboGender.setSelectedItem("");
                                                            dateVisitPicker.getModel().setValue(null);       ;
                                                            textFieldphone.setText("");
                                                            textPaneAddress.setText("");
                                                            comboBox_City.setSelectedItem("");
                                                            comboBox_Province.setSelectedItem("");
                                                            comboBox_Country.setSelectedItem("");
                                                        }else {
                                                            JOptionPane.showMessageDialog(rootPane,"PhoneNumber was available");
                                                        }
                                                    }else {
                                                        JOptionPane.showMessageDialog(rootPane,"CMND was available");
                                                    }
                                                }else {
                                                    JOptionPane.showMessageDialog(rootPane,"Wrong Country");
                                                }
                                            }else {
                                                JOptionPane.showMessageDialog(rootPane,"Wrong Province");
                                            }
                                        }else {
                                            JOptionPane.showMessageDialog(rootPane,"Wrong City");
                                        }
                                    }else {
                                        JOptionPane.showMessageDialog(rootPane,"Wrong Address");
                                    }
                                }else {
                                    JOptionPane.showMessageDialog(rootPane,"Wrong PhoneNumber");
                                }
                            }else {
                                JOptionPane.showMessageDialog(rootPane,"Wrong Date of Visit");
                            }
                        }else {
                            JOptionPane.showMessageDialog(rootPane,"Wrong Gender");
                        }
                    }else {
                        JOptionPane.showMessageDialog(rootPane,"Wrong FullName");
                    }
                }else {
                    JOptionPane.showMessageDialog(rootPane,"Wrong CMND");
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
        btnRefresh.setBounds(1092, 487, 85, 21);
        this.add(btnRefresh);
        btnRefresh.addActionListener(e -> {
            textFieldsearch.setText("");
            String Search=textFieldsearch.getText();
            Connection c=null;
            Statement st=null;
            ResultSet rs=null;
            try {
                c=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=VisitorManage;user=sa;password=123456");
                // command watch data
                String sql="select * from Visitor";
                //if search by title
                if(Search.length()>0){
                    sql = sql + " where CMND like '%" + Search +"%'";
                }
                //create object thu thi command select
                st=c.createStatement();
                //thu thi
                rs=st.executeQuery(sql);
                Vector data = null;
                tableModel.setRowCount(0);
                // if relatives aren't available
                if (rs.isBeforeFirst()==false){
                    JOptionPane.showMessageDialog(this,"Relatives aren't available");
                    return;
                }
                //while not get data
                while(rs.next()){
                    data=new Vector();
                    data.add(rs.getString("CMND"));
                    data.add(rs.getString("FullName"));
                    data.add(rs.getString("Gender"));
                    data.add(rs.getTimestamp("DateofVisit"));
                    data.add(rs.getString("PhoneNumber"));
                    data.add(rs.getString("Address"));
                    data.add(rs.getString("City"));
                    data.add(rs.getString("Province"));
                    data.add(rs.getString("Country"));
                    // add one row in table model
                    tableModel.addRow(data);
                }
                //add data in JTable
                table.setModel(tableModel);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    if (c != null){
                        c.close();
                    }
                    if (st !=null){
                        st.close();
                    }
                    if (rs != null){
                        rs.close();
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }

        });

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSearch.setBounds(215, 288, 85, 21);
        this.add(btnSearch);
        btnSearch.addActionListener(e -> {
            String Search=textFieldsearch.getText();
            Connection c=null;
            Statement st=null;
            ResultSet rs=null;
            try {
                c=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=VisitorManage;user=sa;password=123456");
                // command watch data
                String sql="select * from Visitor";
                //if search by title
                if(Search.length()>0){
                    sql = sql + " where CMND like '%" + Search +"%'";
                }
                //create object thu thi command select
                st=c.createStatement();
                //thu thi
                rs=st.executeQuery(sql);
                Vector data = null;
                tableModel.setRowCount(0);
                // if relatives aren't available
                if (rs.isBeforeFirst()==false){
                    JOptionPane.showMessageDialog(this,"Relatives aren't available");
                    return;
                }
                //while not get data
                while(rs.next()){
                    data=new Vector();
                    data.add(rs.getString("CMND"));
                    data.add(rs.getString("FullName"));
                    data.add(rs.getString("Gender"));
                    data.add(rs.getTimestamp("DateofVisit"));
                    data.add(rs.getString("PhoneNumber"));
                    data.add(rs.getString("Address"));
                    data.add(rs.getString("City"));
                    data.add(rs.getString("Province"));
                    data.add(rs.getString("Country"));
                    // add one row in table model
                    tableModel.addRow(data);
                }
                //add data in JTable
                table.setModel(tableModel);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    if (c != null){
                        c.close();
                    }
                    if (st !=null){
                        st.close();
                    }
                    if (rs != null){
                        rs.close();
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }

        });
        //============================
        panel.setBackground(new Color(148, 0, 211));
        setBounds(170, 70, 1201, 552);
        setVisible(true);
    }
    //Check CMND
    public boolean CheckCMND(String CMND) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=VisitorManage;user=sa;password=123456";
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            Statement stmt = con.createStatement();
            String query = "SELECT CMND FROM Visitor WHERE CMND ='" + CMND + "'";
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
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=VisitorManage;user=sa;password=123456";
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            Statement stmt = con.createStatement();
            String query = "SELECT PhoneNumber FROM Visitor WHERE PhoneNumber ='" + PhoneNumber + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

