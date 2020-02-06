/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.entityguicomponents.pcdialogs.panels;

import dmtools.game.entities.PC;
import dmtools.game.entities.numericals.enums.Skill;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author A3
 */
public class DisplaySelectedSkills extends JPanel {

    private HashSet<Skill> skillsSelected;
    private Border redline = BorderFactory.createLineBorder(Color.red);
    private PC pc;
    private final HashSet<Skill> skillsSelected;
    private final Border redline = BorderFactory.createLineBorder(Color.red);
    private final PC pc;

    public DisplaySelectedSkills(PC pc) {
        this.pc = pc;
        skillsSelected = new HashSet();
        setBorder(BorderFactory.createTitledBorder(null, "Selected Skills",
                TitledBorder.DEFAULT_POSITION,
                TitledBorder.DEFAULT_JUSTIFICATION, null, null));

        setLayout(new GridLayout(10, 2));

        for (Skill i : Skill.values()) {
            JCheckBox skillCheckBox = new JCheckBox(i.toString());
            skillCheckBox.setFocusable(false);
            skillCheckBox.setEnabled(false);
            if (pc.isSkillProficient(i)) {
                skillCheckBox.setSelected(true);
                skillsSelected.add(i);
            }
            add(skillCheckBox);
        }
    }

    public HashSet<Skill> getSelectedSkills() {
        return skillsSelected;
    }
}
