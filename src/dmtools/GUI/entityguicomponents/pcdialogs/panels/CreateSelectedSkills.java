/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.entityguicomponents.pcdialogs.panels;

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
public class CreateSelectedSkills extends JPanel implements ItemListener {

    private HashSet<Skill> skillsSelected;
    private Border redline = BorderFactory.createLineBorder(Color.red);

    public CreateSelectedSkills() {
        skillsSelected = new HashSet();
        setBorder(BorderFactory.createTitledBorder(null, "Selected Skills",
                TitledBorder.DEFAULT_POSITION, 
                TitledBorder.DEFAULT_JUSTIFICATION, null, null));

        setLayout(new GridLayout(10, 2));

        for (Skill i : Skill.values()) {
            JCheckBox skillCheckBox = new JCheckBox(i.toString());
            skillCheckBox.setFocusable(false);
            skillCheckBox.setEnabled(true);
            skillCheckBox.addItemListener(this);
            add(skillCheckBox);
        }
    }

    public HashSet<Skill> getSelectedSkills() {
        return skillsSelected;
    }

    public void highlight(boolean b) {
        if (b) {
            setBorder(BorderFactory.createTitledBorder(redline, 
                    "Selected Skills",
                    TitledBorder.DEFAULT_POSITION,
                    TitledBorder.DEFAULT_JUSTIFICATION, null, Color.red));
        } else {
            setBorder(BorderFactory.createTitledBorder(null, "Selected Skills",
                    TitledBorder.DEFAULT_POSITION,
                    TitledBorder.DEFAULT_JUSTIFICATION, null, null));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JCheckBox choice = (JCheckBox) e.getItemSelectable();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            skillsSelected.add(Skill.getSkillFromString(choice.getText()));
        } else {
            skillsSelected.remove(Skill.getSkillFromString(choice.getText()));
        }
    }
}
