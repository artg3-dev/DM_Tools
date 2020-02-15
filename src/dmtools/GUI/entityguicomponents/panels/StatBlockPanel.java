/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.entityguicomponents.panels;

import dmtools.GUI.LayoutConstants;
import dmtools.GUI.entityguicomponents.labels.StatLabel;
import dmtools.game.entities.numericals.StatBlock;
import dmtools.game.entities.numericals.enums.Stat;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author A3
 */
public class StatBlockPanel extends JPanel {

    private StatBlock statBlock;
    private static final int HGAP = 0;
    private static final int VGAP = 0;
    private static final float statFontSize = 25f;
    private static final float valueFontSize = 18f;

    public StatBlockPanel(StatBlock statBlock) {
        this.statBlock = statBlock;
        
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBackground(LayoutConstants.BEIGE);

        //Str
        JLabel str = new StatLabel(Stat.STR, statFontSize);
        JLabel strValue = new JLabel(statBlock.printStat(Stat.STR));
        strValue.setFont(getFont().deriveFont(valueFontSize));

        //Dex
        JLabel dex = new StatLabel(Stat.DEX, statFontSize);
        JLabel dexValue = new JLabel(statBlock.printStat(Stat.DEX));
        dexValue.setFont(getFont().deriveFont(valueFontSize));

        //Con
        JLabel con = new StatLabel(Stat.CON, statFontSize);
        JLabel conValue = new JLabel(statBlock.printStat(Stat.CON));
        conValue.setFont(getFont().deriveFont(valueFontSize));

        //Int
        JLabel intelligence = new StatLabel(Stat.INT, statFontSize);
        JLabel intelligenceValue = new JLabel(statBlock.printStat(Stat.INT));
        intelligenceValue.setFont(getFont().deriveFont(valueFontSize));

        //Wis
        JLabel wis = new StatLabel(Stat.WIS, statFontSize);
        JLabel wisValue = new JLabel(statBlock.printStat(Stat.WIS));
        wisValue.setFont(getFont().deriveFont(valueFontSize));

        //Cha
        JLabel cha = new StatLabel(Stat.CHA, statFontSize);
        JLabel chaValue = new JLabel(statBlock.printStat(Stat.CHA));
        chaValue.setFont(getFont().deriveFont(valueFontSize));

        GridBagConstraints l = new GridBagConstraints();
        l.gridx = 0;
        l.gridy = 0;
        l.insets = new Insets(0, 5, 0, 5);
        l.anchor = GridBagConstraints.LINE_END;
        
        GridBagConstraints r = new GridBagConstraints();
        r.gridx = 1;
        r.gridy = 0;
        r.insets = new Insets(0, 5, 0, 5);
        r.anchor = GridBagConstraints.LINE_START;
        
        add(str, l);
        add(strValue, r);
        l.gridy ++;
        r.gridy ++;
        add(dex, l);
        add(dexValue, r);
        l.gridy ++;
        r.gridy ++;
        add(con, l);
        add(conValue, r);
        l.gridy ++;
        r.gridy ++;
        add(intelligence, l);
        add(intelligenceValue, r);
        l.gridy ++;
        r.gridy ++;
        add(wis, l);
        add(wisValue, r);
        l.gridy ++;
        r.gridy ++;
        add(cha, l);
        add(chaValue, r);
    }
}
