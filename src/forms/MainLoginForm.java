package forms;

import javax.swing.*;
import java.awt.*;

public class MainLoginForm extends JFrame {
    JPanel defaultpnl;

    public MainLoginForm(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        defaultpnl = (JPanel) getContentPane();
        defaultpnl.setLayout(null);
        setBounds(100, 100, 300, 300);
        //create button
        JButton btnLog = new JButton("Login");
        btnLog.setBounds(90, 90, 105, 20);
        // add event
        btnLog.addActionListener(e -> {
            LoginForm lf = new LoginForm(this,"Login",true);

        });
        // add button to panel
        defaultpnl.add(btnLog);

        JButton btnReg = new JButton("Register");
        btnReg.setBounds(90, 120, 105, 20);
        btnReg.addActionListener(e -> {
            RegForm rf = new RegForm(this,"Register",true);
        });
        defaultpnl.add(btnReg);
        setVisible(true);
    }
}