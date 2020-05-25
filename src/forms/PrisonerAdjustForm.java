package forms;

import javax.swing.*;
import java.awt.*;

public class PrisonerAdjustForm extends JFrame {
    public PrisonerAdjustForm(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,100,1200,600);

        JPanel pnlMain = new JPanel();
        add(pnlMain);
        setVisible(true);

    }
}
