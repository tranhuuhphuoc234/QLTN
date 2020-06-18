package forms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

import static forms.CheckRelative.table;

public class ViewProfilePrisoner extends JDialog {
    Integer id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 8).toString());
    private String urlConnection = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
    private JPanel contentPane;
    private JLabel lblsetName;
    public ViewProfilePrisoner(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(250, 200, 350, 253);
        setTitle("Profile Prisoner");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIdcard = new JLabel("IDCard:");
        lblIdcard.setBounds(57, 49, 45, 13);
        contentPane.add(lblIdcard);

        JLabel lblsetIdcard = new JLabel("");
        lblsetIdcard.setBounds(167, 49, 119, 13);
        lblsetIdcard.setText(idcard(id));
        contentPane.add(lblsetIdcard);
        //-------------==-------------
        JLabel lblName = new JLabel("Name: ");
        lblName.setBounds(57, 72, 45, 13);
        contentPane.add(lblName);

        JLabel lblsetName = new JLabel("");
        lblsetName.setBounds(167, 72, 119, 13);
        lblsetName.setText(name(id));
        contentPane.add(lblsetName);
        //-------------==-------------
        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(57, 95, 45, 13);
        contentPane.add(lblAge);

        JLabel lblsetAge = new JLabel("");
        lblsetAge.setBounds(167, 95, 119, 13);
        lblsetAge.setText(age(id));
        contentPane.add(lblsetAge);
        //-------------==-------------
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setBounds(57, 118, 45, 13);
        contentPane.add(lblGender);

        JLabel lblsetGender = new JLabel("");
        lblsetGender.setBounds(167, 118, 119, 13);
        lblsetGender.setText(gender(id));
        contentPane.add(lblsetGender);
        //-------------==-------------
        JLabel lblDateofbirth = new JLabel("DateOfBirth");
        lblDateofbirth.setBounds(57, 141, 76, 13);
        contentPane.add(lblDateofbirth);

        JLabel lblsetDoB = new JLabel("");
        lblsetDoB.setBounds(167, 141, 119, 13);
        lblsetDoB.setText(dateofbirth(id));
        contentPane.add(lblsetDoB);
        //-------------==-------------
        JLabel lblCellroom = new JLabel("Cellroom:");
        lblCellroom.setBounds(57, 164, 45, 13);
        contentPane.add(lblCellroom);

        JLabel lblsetCellroom = new JLabel("");
        lblsetCellroom.setBounds(167, 164, 119, 13);
        contentPane.add(lblsetCellroom);
        lblsetCellroom.setText(String.valueOf(cellroom(id)));
        setVisible(true);
    }

    public String idcard(int value){
        try(Connection con = DriverManager.getConnection(urlConnection)){
            String query = "select prisoneridcard from prisoner where prisonerid = " + value;
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public String name(int value){
        try(Connection con = DriverManager.getConnection(urlConnection)){
            String query = "select prisonername from prisoner where prisonerid = " + value;
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public String age(int value){
        try(Connection con = DriverManager.getConnection(urlConnection)){
            String query = "select prisonerage from prisoner where prisonerid = " + value;
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public String gender(int value){
        try(Connection con = DriverManager.getConnection(urlConnection)){
            String query = "select gender from prisoner where prisonerid = " + value;
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public String dateofbirth(int value){
        try(Connection con = DriverManager.getConnection(urlConnection)){
            String query = "select dateofbirth from prisoner where prisonerid = " + value;
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "";
    }
    public int cellroom(int value){
        try(Connection con = DriverManager.getConnection(urlConnection)){
            String query = "select cellroom from prisoner where prisonerid = " + value;
            Statement stmt = con.createStatement();
            ResultSet rs= stmt.executeQuery(query);
            if(rs.next()){
                return rs.getInt("cellroom");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
