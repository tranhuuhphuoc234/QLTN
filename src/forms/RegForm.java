package forms;

import models.entities.users;
import utils.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegForm extends JDialog {
    JPanel defaultpnl;
    JTextField tfUsername;
    JPasswordField pfPass;
    JLabel lblWarn;
    JButton btnReg;
    char originalEchoChar;

    public RegForm(Frame owner, String title, boolean modal) {
        super(owner,title,modal);
        //default setting
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        defaultpnl = (JPanel) getContentPane();
        setBounds(150, 150, 300, 300);
        defaultpnl.setLayout(null);


        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(10, 0, 70, 30);
        defaultpnl.add(lblUser);
        tfUsername = new JTextField();
        tfUsername.setBounds(80, 5, 140, 20);
        defaultpnl.add(tfUsername);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(10, 30, 70, 30);
        defaultpnl.add(lblPass);
        pfPass = new JPasswordField();
        pfPass.setBounds(80, 35, 140, 20);
        defaultpnl.add(pfPass);
        JRadioButton chkPass = new JRadioButton();
        chkPass.setBounds(230, 30, 30, 30);
        //add event show/hide password
        originalEchoChar = pfPass.getEchoChar();
        chkPass.setToolTipText("Show password");
        chkPass.addActionListener(e -> {
            if (chkPass.isSelected()) {
                chkPass.setToolTipText("Hide password");
                pfPass.setEchoChar((char) 0);
            } else {
                chkPass.setToolTipText("Show password");
                pfPass.setEchoChar(originalEchoChar);

            }
        });
        defaultpnl.add(chkPass);

        btnReg = new JButton("Register");
        btnReg.setBounds(100, 70, 100, 20);
        btnReg.addActionListener(this::actionCreate);
        defaultpnl.add(btnReg);

        lblWarn = new JLabel();
        lblWarn.setBounds(80, 100, 180, 30);
        defaultpnl.add(lblWarn);
        setVisible(true);
    }

    public void actionCreate(ActionEvent e) {
        // lay cac gia tri trong textfield
        String userName = tfUsername.getText();
        String password = pfPass.getText();
        //tao dieu kien k cho de trong
        if (password.isEmpty() || userName.isEmpty()) {
            lblWarn.setText("Please enter all required information");
            lblWarn.setForeground(Color.red);
        } else {
            //tao class users
                users u1 = new users();
                u1.setUser(userName);
                u1.setPassword(password);
            //tao class dbconnection
            DBConnection db = new DBConnection();
            if (!db.check("checkUser",userName)) {
                db.Create(u1);
                tfUsername.setText(null);
                pfPass.setText(null);
                pfPass.setEchoChar(originalEchoChar);
                lblWarn.setText("successfully register");
                lblWarn.setForeground(Color.green);
            } else {
                lblWarn.setText("username is already exists");
                lblWarn.setForeground(Color.red);
            }
        }
    }
}
