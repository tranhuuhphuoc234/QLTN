package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginForm extends JDialog {
    JPanel defaultpnl;
    JTextField tfUser;
    JPasswordField pfPass;
    JButton btnLog;
    JLabel lblWarn;
    char originalEchoChar;
    public String UserId;
    static String userName;

    public LoginForm(Frame owner, String title, boolean modal) {
        super(owner,title,modal);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        defaultpnl = (JPanel) getContentPane();
        defaultpnl.setLayout(null);
        setBounds(150, 150, 300, 300);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(10, 0, 70, 30);
        defaultpnl.add(lblUser);
        tfUser = new JTextField();
        tfUser.setBounds(80, 5, 140, 20);
        defaultpnl.add(tfUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(10, 30, 70, 30);
        defaultpnl.add(lblPass);
        pfPass = new JPasswordField();
        pfPass.setBounds(80, 35, 140, 20);
        defaultpnl.add(pfPass);
        JRadioButton chkPass = new JRadioButton();
        chkPass.setBounds(230, 30, 30, 30);
        originalEchoChar = pfPass.getEchoChar();
        chkPass.setToolTipText("Show password");
        chkPass.addActionListener(e -> {
            if (chkPass.isSelected()) {

                pfPass.setEchoChar((char) 0);
                chkPass.setToolTipText("Hide password");
            } else {
                pfPass.setEchoChar(originalEchoChar);
                chkPass.setToolTipText("Show password");
            }
        });
        defaultpnl.add(chkPass);
        btnLog = new JButton("Login");
        btnLog.setBounds(100, 65, 100, 20);
        btnLog.addActionListener(this::actionLogin);
        defaultpnl.add(btnLog);

        lblWarn = new JLabel();
        lblWarn.setBounds(70, 95, 180, 30);
        defaultpnl.add(lblWarn);
        setVisible(true);
    }

    public void actionLogin(ActionEvent e) {
        String username = tfUser.getText();
        String password = pfPass.getText();
        DBConnection db = new DBConnection();
        if (db.check("checkUser",username)) {
            if (db.checkPass(username, password)) {
                userName = username;
                UserId = tfUser.getText();
                tfUser.setText(null);
                pfPass.setText(null);
                pfPass.setEchoChar(originalEchoChar);
                getOwner().setVisible(false);
                MainForm mf = new MainForm();

            } else {
                lblWarn.setText("Wrong password");
                lblWarn.setForeground(Color.red);
            }
        } else {
            lblWarn.setText("Could find username");
            lblWarn.setForeground(Color.red);
        }
    }

}
