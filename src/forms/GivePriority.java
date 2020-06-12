package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;

public class GivePriority extends JDialog {
    public static JTable tbl;
    public  GivePriority(){
        setTitle("Give Priority");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100,100,500,300);
        setLayout(new BorderLayout());
        tbl = new JTable();
        DBConnection db = new DBConnection();
        tbl.setModel(db.getUsers());
        tbl.addMouseListener(new PopClickListenerGive());
        add(tbl,BorderLayout.NORTH);
        setVisible(true);
    }
}
