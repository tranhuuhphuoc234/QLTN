package forms;

import models.entitites.prisoner;
import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SearchPrisonerForm extends JFrame {
    private ArrayList<prisoner> list;
    DefaultTableModel model;
    private JTable table;
    public SearchPrisonerForm(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Search");
        setBounds(150,110,1098, 393);
        getContentPane().setLayout(null);
        JPanel contentPane=new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 168, 1064, 178);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        table.setModel(new DefaultTableModel(

                new Object[][] {
                },
                new String[] {
                        "prisonerid", "prisoneridcard", "prisonername", "prisonerage", "gender", "dateofbirth", "dateofarrest", "crime", "dangerlevel", "punishment", "cellroom", "address", "city", "country", "relative"
                }
        ));
        getListPrisoner();
        showTable();


        table.getColumnModel().getColumn(7).setPreferredWidth(47);
        table.getColumnModel().getColumn(8).setPreferredWidth(69);
        table.getColumnModel().getColumn(9).setPreferredWidth(84);
        table.getColumnModel().getColumn(10).setPreferredWidth(53);
        table.getColumnModel().getColumn(11).setPreferredWidth(50);
        scrollPane.setViewportView(table);


        JLabel lblNewLabel = new JLabel("CellRoom:");
        lblNewLabel.setBounds(22, 47, 87, 13);
        contentPane.add(lblNewLabel);

        DBConnection db=new DBConnection();
        String stringCellroom = "All,"+db.getAllName("cellroom");
        final String[][] cellroom = {stringCellroom.split(",")};
        JComboBox cbCellroom = new JComboBox(cellroom[0]);
        JSplitPane splitCellroom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCellroom.add(cbCellroom);
        splitCellroom.setBounds(119, 43, 120, 21);
        add(splitCellroom);



        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(266, 43, 115, 21);
        contentPane.add(btnSearch);
        setVisible(true);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnection db=new DBConnection();
                String crt = cbCellroom.getSelectedItem().toString();
                if(crt == "all"){

                }
                Integer cellroom = db.getColumnID("cellroom", crt);
                getListPrisonerFind(cellroom);
                showFind(cellroom);
            }public void showTable(){
                model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                list = getListPrisoner();
                for (prisoner p : list) {
                    model.addRow(new Object[]{
                            p.getPrisonerid(), p.getPrisoneridcard(), p.getPrisonername(),p.getPrisonerage(), p.getGender(), p.getDateofbirth(), p.getDateofarrest(), p.getCrime(), p.getDangerlevel(), p.getPunishment(), p.getCellroom(), p.getAddress(), p.getCity(), p.getCountry(), p.getRelative(),
                    });
                }
            }
            public ArrayList<prisoner> getListPrisoner(){
                ArrayList<prisoner> list=new ArrayList<>();
                String sql = "select * from prisoner";

                try{
                    String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
                    Connection conn= DriverManager.getConnection(connectionUrl);
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        prisoner p = new prisoner();
                        p.setPrisonerid(rs.getInt("prisonerid"));
                        p.setPrisoneridcard(rs.getString("prisoneridcard"));
                        p.setPrisonername(rs.getString("prisonername"));
                        p.setPrisonerage(rs.getInt("prisonerage"));
                        p.setGender(rs.getString("gender"));
                        p.setDateofbirth(rs.getTimestamp("dateofbirth"));
                        p.setDateofarrest(rs.getTimestamp("dateofarrest"));
                        p.setCrime(rs.getInt("crime"));
                        p.setDangerlevel(rs.getInt("dangerlevel"));
                        p.setPunishment(rs.getInt("punishment"));
                        p.setCellroom(rs.getInt("cellroom"));
                        p.setAddress(rs.getString("address"));
                        p.setCity(rs.getInt("city"));
                        p.setCountry(rs.getInt("country"));
                        p.setRelative(rs.getInt("relative"));
                        list.add(p);
                    }
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
                return list;
            }
            public ArrayList<prisoner> getListPrisonerFind(Integer cellroom){
                ArrayList<prisoner> listfind=new ArrayList<>();
                String sql = "select * from prisoner where cellroom = " + cellroom;

                try{
                    String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
                    Connection conn= DriverManager.getConnection(connectionUrl);
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        prisoner p = new prisoner();
                        p.setPrisonerid(rs.getInt("prisonerid"));
                        p.setPrisoneridcard(rs.getString("prisoneridcard"));
                        p.setPrisonername(rs.getString("prisonername"));
                        p.setPrisonerage(rs.getInt("prisonerage"));
                        p.setGender(rs.getString("gender"));
                        p.setDateofbirth(rs.getTimestamp("dateofbirth"));
                        p.setDateofarrest(rs.getTimestamp("dateofarrest"));
                        p.setCrime(rs.getInt("crime"));
                        p.setDangerlevel(rs.getInt("dangerlevel"));
                        p.setPunishment(rs.getInt("punishment"));
                        p.setCellroom(rs.getInt("cellroom"));
                        p.setAddress(rs.getString("address"));
                        p.setCity(rs.getInt("city"));
                        p.setCountry(rs.getInt("country"));
                        p.setRelative(rs.getInt("relative"));
                        listfind.add(p);
                    }
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
                return listfind;
            }
            public void showFind(Integer cellroom){
                model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                list = getListPrisonerFind(cellroom);
                for (prisoner p : list) {
                    model.addRow(new Object[]{
                            p.getPrisonerid(), p.getPrisoneridcard(), p.getPrisonername(),p.getPrisonerage(), p.getGender(), p.getDateofbirth(), p.getDateofarrest(), p.getCrime(), p.getDangerlevel(), p.getPunishment(), p.getCellroom(), p.getAddress(), p.getCity(), p.getCountry(), p.getRelative(),
                    });
                }
            }
        });
    }
    public void showTable(){
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        list = getListPrisoner();
        for (prisoner p : list) {
            model.addRow(new Object[]{
                    p.getPrisonerid(), p.getPrisoneridcard(), p.getPrisonername(),p.getPrisonerage(), p.getGender(), p.getDateofbirth(), p.getDateofarrest(), p.getCrime(), p.getDangerlevel(), p.getPunishment(), p.getCellroom(), p.getAddress(), p.getCity(), p.getCountry(), p.getRelative(),
            });
        }
    }
    public ArrayList<prisoner> getListPrisoner(){
        ArrayList<prisoner> list=new ArrayList<>();
        String sql = "select * from prisoner";

        try{
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
            Connection conn= DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                prisoner p = new prisoner();
                p.setPrisonerid(rs.getInt("prisonerid"));
                p.setPrisoneridcard(rs.getString("prisoneridcard"));
                p.setPrisonername(rs.getString("prisonername"));
                p.setPrisonerage(rs.getInt("prisonerage"));
                p.setGender(rs.getString("gender"));
                p.setDateofbirth(rs.getTimestamp("dateofbirth"));
                p.setDateofarrest(rs.getTimestamp("dateofarrest"));
                p.setCrime(rs.getInt("crime"));
                p.setDangerlevel(rs.getInt("dangerlevel"));
                p.setPunishment(rs.getInt("punishment"));
                p.setCellroom(rs.getInt("cellroom"));
                p.setAddress(rs.getString("address"));
                p.setCity(rs.getInt("city"));
                p.setCountry(rs.getInt("country"));
                p.setRelative(rs.getInt("relative"));
                list.add(p);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    public ArrayList<prisoner> getListPrisonerFind(Integer cellroom){
        ArrayList<prisoner> listfind=new ArrayList<>();
        String sql = "select * from prisoner where cellroom = " + cellroom;

        try{
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLTN;user=sa;password=123456";
            Connection conn= DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                prisoner p = new prisoner();
                p.setPrisonerid(rs.getInt("prisonerid"));
                p.setPrisoneridcard(rs.getString("prisoneridcard"));
                p.setPrisonername(rs.getString("prisonername"));
                p.setPrisonerage(rs.getInt("prisonerage"));
                p.setGender(rs.getString("gender"));
                p.setDateofbirth(rs.getTimestamp("dateofbirth"));
                p.setDateofarrest(rs.getTimestamp("dateofarrest"));
                p.setCrime(rs.getInt("crime"));
                p.setDangerlevel(rs.getInt("dangerlevel"));
                p.setPunishment(rs.getInt("punishment"));
                p.setCellroom(rs.getInt("cellroom"));
                p.setAddress(rs.getString("address"));
                p.setCity(rs.getInt("city"));
                p.setCountry(rs.getInt("country"));
                p.setRelative(rs.getInt("relative"));
                listfind.add(p);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return listfind;
    }
    public void showFind(Integer cellroom){
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        list = getListPrisonerFind(cellroom);
        for (prisoner p : list) {
            model.addRow(new Object[]{
                    p.getPrisonerid(), p.getPrisoneridcard(), p.getPrisonername(),p.getPrisonerage(), p.getGender(), p.getDateofbirth(), p.getDateofarrest(), p.getCrime(), p.getDangerlevel(), p.getPunishment(), p.getCellroom(), p.getAddress(), p.getCity(), p.getCountry(), p.getRelative(),
            });
        }
    }
    final DefaultTableModel model1 = new DefaultTableModel(0, 3) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0;
        }
    };
}
