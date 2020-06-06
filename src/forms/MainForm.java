package forms;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;

public class MainForm extends JFrame {
    public MainForm(){
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
        JMenuItem mniLogout = new JMenuItem("Login");
        mnSystem.add(mniLogout);

        JMenuItem mniExit = new JMenuItem("Exit");
        mnSystem.add(mniExit);
        mniExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        JMenu mnNewMenu = new JMenu("Manager");
        menuBar.add(mnNewMenu);


        JMenuItem mntmAddsCell = new JMenuItem("Cell Room");
        mnNewMenu.add(mntmAddsCell);
        mntmAddsCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CellRoomForm crf = new CellRoomForm();
                dispose();
            }
        });

        JMenuItem mntmVisitChedule = new JMenuItem("Visit Chedule");
        mnNewMenu.add(mntmVisitChedule);
        mntmVisitChedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisitsCheduleForm vcf = new VisitsCheduleForm();
                dispose();
            }
        });
        setVisible(true);

    }

}
