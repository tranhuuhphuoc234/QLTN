package forms;

import javax.swing.*;
import java.awt.*;
import utils.*;
public class PrisonerForm extends JDialog {
    public PrisonerForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Prisoner Detail");
        setBounds(200,100,800,600);
        setLayout(null);
        JLabel lblId = new JLabel("Prisoner ID");
        lblId.setBounds(30,30,80,25);
        add(lblId);
        setVisible(true);




    }
}
