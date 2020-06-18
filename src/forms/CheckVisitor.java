package forms;

import models.entities.VisitsChedule;
import models.entities.listvisitor;
import forms. VisitsCheduleForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CheckVisitor extends JDialog {
    ArrayList<listvisitor> listVisitors;
    private JPanel contentPane;
    DefaultTableModel model;
    public static JTable table;
    public CheckVisitor(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(250, 200, 738, 253);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 724, 216);
        contentPane.add(scrollPane);

        //----TABLE CHECK RELATIVE----
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                         "visitorid", "visitorname", "visitorphone", "visitoraddress", "city", "country", "relationship"
                }
        ));
        table.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(table);
        table.addMouseListener(new PopClickCL());
        setVisible(true);
    }
    public ArrayList<listvisitor>  getListRelative(String value) {
        ArrayList<listvisitor> listVisitors = new ArrayList<>();
        String sql = "select * from visitor where visitorid = '" + value + "'";

        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listvisitor l = new listvisitor();
                l.setVisitorid(rs.getString("visitorid"));
                l.setVisitorname(rs.getString("visitorname"));
                l.setVisitorphone(rs.getString("vistorphone"));
                l.setVisitoraddress(rs.getString("visitoraddress"));
                l.setCity(rs.getInt("city"));
                l.setCountry(rs.getInt("country"));
                l.setRelationship(rs.getString("relationship"));
                listVisitors.add(l);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return listVisitors;
    }

    public void showTableCheckRelative(String value) {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listVisitors = getListRelative(value);
        for (listvisitor l : listVisitors) {
            model.addRow(new Object[]{
                    l.getVisitorid(), l.getVisitorname(), l.getVisitorphone(), l.getVisitoraddress(), l.getCity(), l.getCountry(), l.getRelationship()
            });
        }
    }





}
