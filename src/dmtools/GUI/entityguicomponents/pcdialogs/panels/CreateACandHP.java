/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.entityguicomponents.pcdialogs.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author A3
 */
public class CreateACandHP extends JPanel {

    private JTextField acTF;
    private JTextField mHpTF;
    private JTextField cHpTF;
    
    private JLabel acLabel;
    private JLabel mHpLabel;
    

    public CreateACandHP() {
        super();
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Health"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, 0, 5);

        //AC
        acLabel = new JLabel("AC");
        acTF = new JTextField();
        acTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 0;
        add(acLabel, c);
        c.gridx = 1;
        add(acTF, c);

        //Max HP
        mHpLabel = new JLabel("HP");
        mHpTF = new JTextField();
        mHpTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 1;
        add(mHpLabel, c);
        c.gridx = 1;
        add(mHpTF, c);

    }
    
    public void highlight(boolean b, String s) {
        if (b) {
            switch (s) {
                case "AC":
                    acLabel.setForeground(Color.red);
                case "HP":
                    mHpLabel.setForeground(Color.red);
            }
        }
        else {
            switch (s) {
                case "AC":
                    acLabel.setForeground(null);
                case "HP":
                    mHpLabel.setForeground(null);
            }
        }
    }

    public int getAC() throws NumberFormatException, IllegalArgumentException {
        if (acTF.getText().equals("")) {
            throw new IllegalArgumentException();
        }
        try {
            int returnAC = Integer.parseInt(acTF.getText());
            return returnAC;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public int getMaxHP() throws NumberFormatException, IllegalArgumentException {
        if (mHpTF.getText().equals("")) {
            throw new IllegalArgumentException();
        }

        try {
            int returnMaxHP = Integer.parseInt(mHpTF.getText());
            if (returnMaxHP <= 0) {
                throw new NumberFormatException();
            } else {
                return returnMaxHP;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public int getCurrentHP() throws NumberFormatException, 
            IllegalArgumentException {
        if (cHpTF.getText().equals("")) {
            throw new IllegalArgumentException();
        }

        try {
            int returnCurHP = Integer.parseInt(cHpTF.getText());
            if (returnCurHP < 0) {
                throw new NumberFormatException();
            } else {
                return returnCurHP;
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}
