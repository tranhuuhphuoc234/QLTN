package Forms;

import models.entities.CrimePunishment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class AddRuleoflaw extends JFrame {
    private JPanel panel;

    public AddRuleoflaw() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel=(JPanel) getContentPane();
        panel.setLayout(null);
        panel.setBackground(SystemColor.activeCaption);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //JLabel
        JLabel lblTR = new JLabel("Crime");
        lblTR.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTR.setBounds(75, 52, 80, 23);
        this.add(lblTR);

        JLabel lblHP = new JLabel("Punishment");
        lblHP.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblHP.setBounds(75, 98, 80, 23);
        this.add(lblHP);

        //JTextField

        JTextField textFieldTR = new JTextField();
        textFieldTR.setBounds(165, 55, 207, 19);
        this.add(textFieldTR);
        textFieldTR.setColumns(10);

        //JScrollPane

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(165, 98, 207, 91);
        this.add(scrollPane);

        //JTextPane

        JTextPane textPaneHP = new JTextPane();
        scrollPane.setViewportView(textPaneHP);

        //JButton

        JButton BTAddTRHP = new JButton("Add");
        BTAddTRHP.setFont(new Font("Tahoma", Font.PLAIN, 12));
        BTAddTRHP.setBounds(280, 212, 91, 29);
        BTAddTRHP.setBackground(new Color(102, 255, 51));
        this.add(BTAddTRHP);
        BTAddTRHP.addActionListener(e -> {
            String TR = textFieldTR.getText();
            String HP = textPaneHP.getText();
            CrimePunishment crimePunishment =new CrimePunishment();
            crimePunishment.setCrime(TR);
            crimePunishment.setPunishment(HP);
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=ManageTRHP;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "insert into CrimePunishment(Crime,Punishment)  values(?,?)";
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,TR);
                ps.setString(2,HP);
                if (!TR.equals("")){
                    if (!HP.equals("")){
                        if (CheckCrime(TR)){
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(rootPane,"Success");
                            textFieldTR.setText("");
                            textPaneHP.setText("");
                        }else {
                            JOptionPane.showMessageDialog(rootPane,"Information available");
                        }
                    }else {
                        JOptionPane.showMessageDialog(rootPane,"Wrong Punishment");
                    }
                }else {
                    JOptionPane.showMessageDialog(rootPane,"Wrong Crime");
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        //===============================

        setBounds(100, 100, 450, 300);
        setVisible(true);
    }

    public boolean CheckCrime(String Crime) {
        String Url = "jdbc:sqlserver://localhost:1433;databaseName=ManageTRHP;user=sa;password=123456";
        try (Connection connection = DriverManager.getConnection(Url)) {
            Statement statement = connection.createStatement();
            String sql = "SELECT Crime FROM CrimePunishment WHERE Crime =N'" + Crime + "'";
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
        AddRuleoflaw addRuleoflaw =new AddRuleoflaw();
    }
}