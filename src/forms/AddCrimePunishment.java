package forms;

import models.entities.crime;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class AddCrimePunishment extends JFrame {
    private JPanel panel;

    public AddCrimePunishment() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel=(JPanel) getContentPane();
        panel.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setToolTipText("");
        tabbedPane.setBounds(0, 0, 369, 227);
        panel.add(tabbedPane);

        JPanel panel_crime = new JPanel();
        tabbedPane.addTab("Crime", null, panel_crime, null);
        panel_crime.setLayout(null);

        JLabel lblinforcrime = new JLabel("Information Crime\r\n");
        lblinforcrime.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblinforcrime.setBounds(130, 10, 111, 24);
        panel_crime.add(lblinforcrime);

        JTextField textField_crime = new JTextField();
        textField_crime.setBounds(68, 70, 238, 19);
        textField_crime.setHorizontalAlignment(SwingConstants.CENTER);
        panel_crime.add(textField_crime);
        textField_crime.setColumns(10);

        JButton btaddcrime = new JButton("Add");
        btaddcrime.setFont(new Font("Tahoma", Font.BOLD, 12));
        btaddcrime.setBounds(144, 124, 85, 21);
        panel_crime.add(btaddcrime);
        btaddcrime.addActionListener(e -> {
            String tfcrime = textField_crime.getText();
            crime cr = new crime();
            cr.setCrime(tfcrime);
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "insert into crime(crimename)  values(?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tfcrime);
                if (!tfcrime.equals("")){
                    if (CheckCrime(tfcrime)){
                        ps.execute();
                        JOptionPane.showMessageDialog(rootPane,"Success");
                        textField_crime.setText("");
                    }else {
                        JOptionPane.showMessageDialog(rootPane,"Crime available");
                    }
                }else {
                    JOptionPane.showMessageDialog(rootPane,"Please fill in the blanks");
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        JPanel panel_punishment = new JPanel();
        tabbedPane.addTab("Punishment", null, panel_punishment, null);
        panel_punishment.setLayout(null);

        JTextField textField_punishment = new JTextField();
        textField_punishment.setBounds(77, 88, 96, 19);
        textField_punishment.setHorizontalAlignment(SwingConstants.CENTER);
        panel_punishment.add(textField_punishment);
        textField_punishment.setColumns(10);

        JComboBox comboBox_datepusinhment = new JComboBox();
        comboBox_datepusinhment.setModel(new DefaultComboBoxModel(new String[] {"Select", "ngay ", "thang ", "nam"}));
        comboBox_datepusinhment.setBounds(193, 87, 96, 21);
        panel_punishment.add(comboBox_datepusinhment);

        JButton btaddpunishment = new JButton("Add");
        btaddpunishment.setFont(new Font("Tahoma", Font.BOLD, 12));
        btaddpunishment.setBounds(204, 134, 85, 21);
        panel_punishment.add(btaddpunishment);

        JLabel lblinforpunishment = new JLabel("Information Punishment");
        lblinforpunishment.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblinforpunishment.setBounds(109, 10, 146, 32);
        panel_punishment.add(lblinforpunishment);



        //=====================================
        setBounds(500, 150, 383, 264);
        setVisible(true);
    }
    public boolean CheckCrime(String Crime) {
        String Url = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
        try (Connection connection = DriverManager.getConnection(Url)) {
            Statement statement = connection.createStatement();
            String sql = "SELECT crimename FROM crime WHERE crimename =N'" + Crime + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        AddCrimePunishment addCrimePunishment=new AddCrimePunishment();
    }
}
