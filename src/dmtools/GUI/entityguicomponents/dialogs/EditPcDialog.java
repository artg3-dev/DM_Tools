/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.entityguicomponents.dialogs;

import dmtools.GUI.entityguicomponents.dialogs.panels.CreateACandHP;
import dmtools.GUI.entityguicomponents.dialogs.panels.CreateSelectedSkills;
import dmtools.GUI.entityguicomponents.dialogs.panels.CreateStatBlockPanel;
import dmtools.GUI.entityguicomponents.comboboxes.DNDComboBox;
import dmtools.GUI.entityguicomponents.dialogs.panels.DisplayACandHP;
import dmtools.GUI.entityguicomponents.dialogs.panels.DisplaySelectedSkills;
import dmtools.GUI.entityguicomponents.dialogs.panels.DisplayStatBlock;
import dmtools.filehandling.FileHandler;
import dmtools.game.entities.PC;
import dmtools.game.entities.characteristics.Alignment;
import dmtools.game.entities.characteristics.Race;
import dmtools.game.entities.numericals.StatBlock;
import dmtools.game.entities.numericals.enums.Skill;
import dmtools.game.entities.numericals.enums.Stat;
import dmtools.game.playableclasses.PlayableClass;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author A3
 */
public class EditPcDialog extends JDialog implements PropertyChangeListener {

    private static JLabel cNameLabel;
    private static JLabel pNameLabel;
    private static JLabel raceLabel;
    private static JLabel alignmentLabel;
    private static JLabel classLabel;
    private static JLabel cLevelLabel;
    
    
    private static JTextField cNameTF;
    private static JTextField pNameTF;
    private static JComboBox raceCB;
    private static JComboBox alignmentCB;
    private static JComboBox classCB;
    private static DNDComboBox levelBox;
    private static DisplayStatBlock statBlockPanel;
    private static DisplaySelectedSkills selectedSkillList;
    private static DisplayACandHP acAndHP;

    private JOptionPane optionPane;

    private PC updatedPC;
    private PC toEditPC;
    private boolean shouldSave;

    public EditPcDialog(Frame frame, PC pc) {
        super(frame, true);
        this.toEditPC = pc;
        this.shouldSave = true;

        setTitle("Edit a Player Character");
        String createButtonTxt = "Save";
        Object[] options = {createButtonTxt};

        optionPane = new JOptionPane(getInputFields(),
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_OPTION,
                null, //icon
                options,
                options[0]);

        setContentPane(optionPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        optionPane.addPropertyChangeListener(this);
        pack();
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop))
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop)) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            /*
            * This is where the error list is displayed
            * or the pc is created
             */
            List<String> errors = checkErrors();
            if (errors == null) {
                //create the pc here
                this.updatedPC = getPcWithChanges();
                if (shouldSave) {
                    try {
                        FileHandler.saveFromInstance(updatedPC, 
                                FileHandler.PC_FILE);
                    } catch (IOException pcFileHandlerException) {
                    }
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        errors.toArray(),
                        "Character incomplete or incorrect",
                        JOptionPane.ERROR_MESSAGE,
                        null);
            }
        }
    }

    private PC getPcWithChanges() {
        String characterName = cNameTF.getText();
        String playerName = pNameTF.getText();
        Race race = Race.stringValueOf(raceCB.getSelectedItem().toString());
        Alignment alignment = Alignment.stringValueOf(
                alignmentCB.getSelectedItem().toString());
        PlayableClass playableClass = PlayableClass.valueOf(
                classCB.getSelectedItem().toString().toUpperCase());
        int characterLevel = Integer.parseInt(
                levelBox.getSelectedItem().toString());
        StatBlock statBlock = statBlockPanel.getStatBlock();
        HashSet<Skill> selectedSkills
                = selectedSkillList.getSelectedSkills();
        int AC = acAndHP.getAC();
        int maxHP = acAndHP.getMaxHP();
        int currentHP = maxHP;

        return new PC(characterName, playerName, race, alignment,
                playableClass, characterLevel, statBlock, selectedSkills,
                AC, maxHP, currentHP);

    }
    
    public PC getUpdatedPC() {
        return updatedPC;
    }

    private JComponent[] getInputFields() {
        //Character name
        cNameLabel = new JLabel("Character Name");
        cNameTF = new JTextField(toEditPC.getName());
        cNameTF.setEnabled(false);
        

        //Player name
        pNameLabel = new JLabel("Player Name");
        pNameTF = new JTextField(toEditPC.getPlayerName());
        pNameTF.setEnabled(false);

        //Race
        raceLabel = new JLabel("Race");
        raceCB = new DNDComboBox(DNDComboBox.RACE_BOX);
        raceCB.setSelectedItem(toEditPC.getRace().toString());
        raceCB.setEnabled(false);

        //Alignment
        alignmentLabel = new JLabel("Alignment");
        alignmentCB = new DNDComboBox(DNDComboBox.ALIGNMENT_BOX);
        alignmentCB.setSelectedItem(toEditPC.getAlignment().toString());

        //Playable Class
        classLabel = new JLabel("Class");
        classCB = new DNDComboBox(DNDComboBox.PLAYABLE_CLASS_BOX);
        classCB.setSelectedItem(toEditPC.getPlayableClass().toString());
        classCB.setEnabled(false);

        //Character Level
        cLevelLabel = new JLabel("Character Level");
        levelBox = new DNDComboBox(DNDComboBox.CHARACTER_LEVEL_BOX);
        levelBox.setSelectedIndex(toEditPC.getCharacterLevel() - 1);

        //StatBlock
        statBlockPanel = new DisplayStatBlock(toEditPC);
        statBlockPanel.setFocusable(false);

        //Selected Skills
        selectedSkillList = new DisplaySelectedSkills(toEditPC);
        selectedSkillList.setFocusTraversalKeysEnabled(false);

        //AC, Max HP, Current HP
        acAndHP = new DisplayACandHP(toEditPC);

        //AC&HP, Statblock and Selected Skills in a panel
        JPanel AcHpStatAndSkillPanel = new JPanel();
        AcHpStatAndSkillPanel.setLayout(new GridBagLayout());
        GridBagConstraints acStatsAndSkillConstrain = new GridBagConstraints();
        acStatsAndSkillConstrain.insets = new Insets(0, 0, 0, 5);

        //for adding components
        acStatsAndSkillConstrain.gridx = 0;
        acStatsAndSkillConstrain.gridy = 0;
        acStatsAndSkillConstrain.anchor = GridBagConstraints.LAST_LINE_END;
        AcHpStatAndSkillPanel.add(acAndHP, acStatsAndSkillConstrain);

        acStatsAndSkillConstrain.gridy = 1;
        acStatsAndSkillConstrain.anchor = GridBagConstraints.LAST_LINE_END;
        AcHpStatAndSkillPanel.add(statBlockPanel, acStatsAndSkillConstrain);

        acStatsAndSkillConstrain.gridx = 1;
        acStatsAndSkillConstrain.gridy = 0;
        acStatsAndSkillConstrain.gridheight = 2;
        acStatsAndSkillConstrain.anchor = GridBagConstraints.FIRST_LINE_END;
        AcHpStatAndSkillPanel.add(selectedSkillList, acStatsAndSkillConstrain);

        acStatsAndSkillConstrain.gridx = 2;
        acStatsAndSkillConstrain.weightx = 1.0;
        AcHpStatAndSkillPanel.add(new JPanel(), acStatsAndSkillConstrain);

        /*
        * Other stuff can go here to be added
         */
        
        
        JComponent[] components = new JComponent[]{
            cNameLabel,
            cNameTF,
            pNameLabel,
            pNameTF,
            raceLabel,
            raceCB,
            alignmentLabel,
            alignmentCB,
            classLabel,
            classCB,
            cLevelLabel,
            levelBox,
            AcHpStatAndSkillPanel};
        return components;
    }

    private List<String> checkErrors() {
        //put the checks here
        List<String> errorList = new ArrayList();

        //Checks Character name 
        if (cNameTF.getText().equals("")) {
            errorList.add("-Character name cannot be blank");
            cNameLabel.setForeground(Color.red);
        } else {
            cNameLabel.setForeground(null);
        }
        
        //Checks player name
        if (pNameTF.getText().equals("")) {
            errorList.add("-Player name cannot be blank");
            pNameLabel.setForeground(Color.red);
        } else {
            pNameLabel.setForeground(null);
        }
        
        //Checks race
        if (raceCB.getSelectedIndex() == 0) {
            errorList.add("-No Race selected");
            raceLabel.setForeground(Color.red);
        } else {
            raceLabel.setForeground(null);
        }
        
        //Checks alignment
        if (alignmentCB.getSelectedIndex() == 0) {
            errorList.add("-No alignment selected");
            alignmentLabel.setForeground(Color.red);
        } else {
            alignmentLabel.setForeground(null);
        }
        
        //Checks class
        if (classCB.getSelectedIndex() == 0) {
            errorList.add("-No Class selected");
            classLabel.setForeground(Color.red);
        } else {
            classLabel.setForeground(null);
        }

        //Checks AC
        try {
            acAndHP.getAC();
            acAndHP.highlight(false, "AC");
        } catch (NumberFormatException nx) {
            errorList.add("-AC must be an integer");
            acAndHP.highlight(true, "AC");
        } catch (IllegalArgumentException iax) {
            errorList.add("-AC cannot be blank");
            acAndHP.highlight(true, "AC");
        }

        //Checks HP
        try {
            acAndHP.getMaxHP();
            acAndHP.highlight(false, "HP");
        } catch (NumberFormatException nx) {
            errorList.add("-HP must be an integer greater than 0");
            acAndHP.highlight(true, "HP");
        } catch (IllegalArgumentException iax) {
            errorList.add("-HP cannot be blank");
            acAndHP.highlight(true, "HP");
        }
        
        //selected skills disabled

//        //Checks Selected Skills > 0
//        if (selectedSkillList.getSelectedSkills().size() < 1) {
//            errorList.add("-At least one skill must be selected");
//            selectedSkillList.highlight(true);
//            /*
//            *THIS IS WHERE YOU WILL PUT THE INFO FOR SKILLS SELECTED != NUMBER
//            *OF POSSIBLE CLASS SKILLS
//             */
//        } else {
//            selectedSkillList.highlight(false);
//        }

        //Stats
        statBlockPanel.resetHighlight();
        if (statBlockPanel.checkValidity() != null) {
            for (Stat i : statBlockPanel.checkValidity()) {
                errorList.add("-Value of " + i.name()
                        + " must be an integer greater than 0");
                statBlockPanel.highlight(true, i);
            }
        }
        if (errorList.isEmpty()) {
            return null;
        } else {
            return errorList;
        }
    }
}
