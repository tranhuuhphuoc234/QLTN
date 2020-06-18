package forms;

import models.entities.VisitsChedule;
import models.entities.relative;
import forms. VisitsCheduleForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CheckRelative extends JDialog {
    ArrayList<relative> listRelative;
    private JPanel contentPane;
    DefaultTableModel model;
    public static JTable table;
    public CheckRelative(){
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
                         "relativeidcard", "relativename", "relativeage", "relativephone", "relativeaddress", "city", "country", "relationship", "prisonerid"
                }
        ));
        table.setDefaultEditor(Object.class, null);
        scrollPane.setViewportView(table);
        table.addMouseListener(new PopClickCL());
        setVisible(true);
    }
    public ArrayList<relative> getListRelative(String value) {
        ArrayList<relative> listRelative = new ArrayList<>();
        String sql = "select * from relative where relativeidcard = '" + value + "'";

        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                relative l = new relative();
                l.setRelativeidcard(rs.getString("relativeidcard"));
                l.setRelativename(rs.getString("relativename"));
                l.setRelativeage(rs.getInt("relativeage"));
                l.setRelativephone(rs.getString("relativephone"));
                l.setRelativeaddress(rs.getString("relativeaddress"));
                l.setCity(rs.getInt("city"));
                l.setCountry(rs.getInt("country"));
                l.setRelationship(rs.getString("relationship"));
                l.setPrisonerid(rs.getInt("prisonerid"));
                listRelative.add(l);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return listRelative;
    }

    public void showTableCheckRelative(String value) {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listRelative = getListRelative(value);
        for (relative l : listRelative) {
            model.addRow(new Object[]{
                    l.getRelativeidcard(), l.getRelativename(), l.getRelativeage(), l.getRelativephone(), l.getRelativeaddress(), l.getCity(), l.getCountry(), l.getRelationship(), l.getPrisonerid()
            });
        }
    }





}
