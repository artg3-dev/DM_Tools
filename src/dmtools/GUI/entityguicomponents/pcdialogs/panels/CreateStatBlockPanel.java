/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.entityguicomponents.pcdialogs.panels;

import dmtools.game.entities.numericals.StatBlock;
import dmtools.game.entities.numericals.enums.Stat;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author A3
 */
public class CreateStatBlockPanel extends JPanel {

    private JTextField sTF;
    private JTextField dTF;
    private JTextField coTF;
    private JTextField iTF;
    private JTextField wTF;
    private JTextField chTF;

    private JLabel strLabel;
    private JLabel dexLabel;
    private JLabel conLabel;
    private JLabel intelligenceLabel;
    private JLabel wisLabel;
    private JLabel chaLabel;

    public CreateStatBlockPanel() {
        super();
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Stats"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, 0, 5);

        //Str
        strLabel = new JLabel(Stat.STR.name());
        sTF = new JTextField();
        sTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 0;
        add(strLabel, c);
        c.gridx = 1;
        add(sTF, c);

        //Dex
        dexLabel = new JLabel(Stat.DEX.name());
        dTF = new JTextField();
        dTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 1;
        add(dexLabel, c);
        c.gridx = 1;
        add(dTF, c);

        //Con
        conLabel = new JLabel(Stat.CON.name());
        coTF = new JTextField();
        coTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 2;
        add(conLabel, c);
        c.gridx = 1;
        add(coTF, c);

        //Int
        intelligenceLabel = new JLabel(Stat.INT.name());
        iTF = new JTextField();
        iTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 3;
        add(intelligenceLabel, c);
        c.gridx = 1;
        add(iTF, c);

        //Wis
        wisLabel = new JLabel(Stat.WIS.name());
        wTF = new JTextField();
        wTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 4;
        add(wisLabel, c);
        c.gridx = 1;
        add(wTF, c);

        //Cha
        chaLabel = new JLabel(Stat.CHA.name());
        chTF = new JTextField();
        chTF.setColumns(4);
        c.gridx = 0;
        c.gridy = 5;
        add(chaLabel, c);
        c.gridx = 1;
        add(chTF, c);

    }

    public int[] getStatValues() throws NumberFormatException {
        try {
            return new int[]{
                Integer.parseInt(sTF.getText()),
                Integer.parseInt(dTF.getText()),
                Integer.parseInt(coTF.getText()),
                Integer.parseInt(iTF.getText()),
                Integer.parseInt(wTF.getText()),
                Integer.parseInt(chTF.getText())
            };

        } catch (Exception e) {
            throw new NumberFormatException();
        }
    }

    public StatBlock getStatBlock() {
        return new StatBlock(getStatValues());
    }

    public ArrayList<Stat> checkValidity() {
        ArrayList<Stat> faultyStats = new ArrayList();
        HashMap<JTextField, Stat> stats = new HashMap();
        stats.put(sTF, Stat.STR);
        stats.put(dTF, Stat.DEX);
        stats.put(coTF, Stat.CON);
        stats.put(iTF, Stat.INT);
        stats.put(wTF, Stat.WIS);
        stats.put(chTF, Stat.CHA);

        for (JTextField i : stats.keySet()) {
            try {
                int statVal = Integer.parseInt(i.getText());
                if (statVal <= 0) {
                    faultyStats.add(stats.get(i));
                }
            } catch (NumberFormatException e) {
                faultyStats.add(stats.get(i));
            }
        }
        if (faultyStats.size() < 1) {
            return null;
        }
        return faultyStats;
    }

    public void highlight(boolean b, Stat s) {
        HashMap<Stat, JLabel> stats = new HashMap();
        stats.put(Stat.STR, strLabel);
        stats.put(Stat.DEX, dexLabel);
        stats.put(Stat.CON, conLabel);
        stats.put(Stat.INT, intelligenceLabel);
        stats.put(Stat.WIS, wisLabel);
        stats.put(Stat.CHA, chaLabel);

        if (b) {
            stats.get(s).setForeground(Color.red);
        } else {
            stats.get(s).setForeground(null);
        }
    }
    
    public void resetHighlight() {
        List<Stat> stats = new ArrayList(Arrays.asList(Stat.values()));
        stats.forEach((i) -> {
            highlight(false, i);
        });
    }
}
