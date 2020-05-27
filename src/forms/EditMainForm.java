package forms;

import utils.DBConnection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditMainForm extends JFrame {
    JTextField tfSearchRelative;
    JComboBox  boxRelativeSelect;
    public static JTable tableRelative;
    public EditMainForm(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,100,1200,600);
        DBConnection db = new DBConnection();

        JTabbedPane tp = new JTabbedPane();
        JPanel prisoner = new JPanel();

        JPanel relative = new JPanel();
        relative.setLayout(new BorderLayout());
        Border line = BorderFactory.createLineBorder(Color.BLACK);

        JPanel pnlSearchRelative = new JPanel();
        pnlSearchRelative.setLayout(null);
        pnlSearchRelative.setPreferredSize(new Dimension(1,200));
        pnlSearchRelative.setBorder(line);

        tfSearchRelative = new JTextField();
        tfSearchRelative.setBounds(380,60,250,30);
        pnlSearchRelative.add(tfSearchRelative);

        String[] relativeSelect = {"Select","ID Card","Prisoner ID","Name"};
        boxRelativeSelect = new JComboBox(relativeSelect);
        boxRelativeSelect.setBounds(650,60,150,30);
        pnlSearchRelative.add(boxRelativeSelect);

        JButton btnSearchRelative = new JButton("Search");
        btnSearchRelative.setBounds(530,130,120,30);
        btnSearchRelative.addActionListener(this::searchRelative);
        pnlSearchRelative.add(btnSearchRelative);

        relative.add(pnlSearchRelative,BorderLayout.NORTH);

        JPanel pnlResultRelative = new JPanel();
        pnlResultRelative.setLayout(new BorderLayout());
        pnlResultRelative.setBorder(line);

        tableRelative = new JTable();
        tableRelative.addMouseListener(new PopClickListener());
        tableRelative.setModel(db.getRelative("All",""));
        JScrollPane spRelative = new JScrollPane(tableRelative);
        spRelative.setPreferredSize(new Dimension(1,300));
        pnlResultRelative.add(spRelative,BorderLayout.NORTH);

        relative.add(pnlResultRelative,BorderLayout.SOUTH);


        tp.add("Prisoner",prisoner);
        tp.add("Relative",relative);
        add(tp);
        setVisible(true);

    }
    public void searchRelative(ActionEvent e)
    {
        DBConnection db = new DBConnection();
        String select = boxRelativeSelect.getSelectedItem().toString();
        String value = tfSearchRelative.getText();
        tableRelative.setModel(db.getRelative(select,value));
    }
}
