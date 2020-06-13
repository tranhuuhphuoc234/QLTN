package forms;


import utils.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    public MainForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Prison Management");
        setBounds(370, 55, 720, 600);
        setLayout(null);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lb1 = new JLabel("PRISON MANAGEMENT");
        lb1.setBounds(210, 20, 350, 40);
        lb1.setFont(new Font("UTM Aircona", Font.PLAIN, 25));
        lb1.setForeground(Color.RED);
        contentPane.add(lb1);

        JLabel lbimg = new JLabel();
        lbimg.setIcon(new ImageIcon("src\\models\\image\\logo.png"));
        lbimg.setBounds(0, 0, 720, 580);
        getContentPane().add(lbimg);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnSystem = new JMenu("System");
        menuBar.add(mnSystem);
        JMenuItem changePass = new JMenuItem("Change Password");
        changePass.addActionListener(e -> {
            ChangePassForm cpf = new ChangePassForm();
        });
        mnSystem.add(changePass);
        JMenuItem mniExit = new JMenuItem("Exit");
        mnSystem.add(mniExit);
        mniExit.addActionListener(e -> {
            this.dispose();
            MainLoginForm mlf = new MainLoginForm("");
        });


        JMenu mnNewMenu = new JMenu("Manager");
        menuBar.add(mnNewMenu);


        JMenuItem mntmAddsCell = new JMenuItem("Cell Room");
        mnNewMenu.add(mntmAddsCell);
        mntmAddsCell.addActionListener(e -> {
            CellRoomForm c = new CellRoomForm();
        });

        JMenuItem mntmVisitChedule = new JMenuItem("Visit Chedule");
        mnNewMenu.add(mntmVisitChedule);
        mntmVisitChedule.addActionListener(e -> {
            VisitsCheduleForm vcf = new VisitsCheduleForm();
        });
        JMenuItem itemAddPrisoner = new JMenuItem("Add Prisoner/Relative");
        itemAddPrisoner.addActionListener(e -> {
            PrisonerForm pf = new PrisonerForm();
        });
        mnNewMenu.add(itemAddPrisoner);

        JMenuItem itemEditPrisoner = new JMenuItem("Prisoner/Relative List");
        itemEditPrisoner.addActionListener(e -> {
            EditMainForm edm = new EditMainForm();
        });
        mnNewMenu.add(itemEditPrisoner);
        JMenuItem itemAddCity = new JMenuItem("Add City/Country");
        itemAddCity.addActionListener(e -> {
            AddCityAndCountry a = new AddCityAndCountry();
        });
        mnNewMenu.add(itemAddCity);
        DBConnection db = new DBConnection();
        if (db.checkAccess(LoginForm.userName)) {
            JMenuItem itemGivePriority = new JMenuItem("Give Priority");
            itemGivePriority.addActionListener(e -> {
                GivePriority g = new GivePriority();
            });
            mnNewMenu.add(itemGivePriority);

        }
        setVisible(true);

    }


}
