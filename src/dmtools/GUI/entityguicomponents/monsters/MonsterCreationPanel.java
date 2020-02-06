/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.entityguicomponents.monsters;

import dmtools.game.entities.Monster;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author A3
 */
public class MonsterCreationPanel extends JPanel implements FocusListener{

    private JLabel nameLabel, acLabel, hpLabel, idLabel;
    private JTextField nameField, acField, hpField, idField;
    private JComboBox crCombo;
    private ArrayList<String> errors;

    public MonsterCreationPanel() {
        super();
        createComponents();
    }

    public ArrayList<String> hasValidInfo() {
        errors = new ArrayList();

        // Name
        highlight(nameLabel, false);
        if (nameField.getText().isEmpty()) {
            errors.add("-Name cannot be blank");
            highlight(nameLabel, true);
        }

        // AC 
        highlight(acLabel, false);
        if (acField.getText().isEmpty()) {
            errors.add("-AC cannot be blank");
            highlight(acLabel, true);
        } else {
            try {
                Integer.parseInt(acField.getText());
            } catch (NumberFormatException e) {
                errors.add("-AC must be an Integer");
                highlight(acLabel, true);
            }
        }

        // HP 
        highlight(hpLabel, false);
        if (hpField.getText().isEmpty()) {
            errors.add("-HP cannot be blank");
            highlight(hpLabel, true);
        } else {
            try {
                Integer.parseInt(hpField.getText());
            } catch (NumberFormatException e) {
                errors.add("-HP must be an Integer");
                highlight(hpLabel, true);
            }
        }

        // CR
        highlight(crCombo, false);
        if (crCombo.getSelectedIndex() == 0) {
            errors.add("-Select a CR");
            highlight(crCombo, true);
        }

        // Checks for any errors, then returns appropriate value
        return errors;
    }

    public Monster getCreatedMonster() throws IllegalArgumentException {
        if (hasValidInfo().isEmpty()) {
            String name = nameField.getText();
            int acValue = Integer.parseInt(acField.getText());
            int hpValue = Integer.parseInt(hpField.getText());
            int crValue = Integer.parseInt(
                    crCombo.getSelectedItem().toString().split(" ")[1]);
            Monster monster = new Monster(
                    name, name, acValue, hpValue, crValue);
            if (idField.getText().equals("") || 
                    idField.getText().equals("(optional)")) {
                return monster;
            } else {
                monster.setID(idField.getText());
                return monster;
            }
        } else {
            throw new IllegalArgumentException(
                    "There are unaddressed errors in one or more fields");
        }
    }

    private void highlight(JComponent component, boolean shouldColor) {
        if (component.getClass() == JComboBox.class) {
            if (shouldColor) {
                component.setBorder(BorderFactory.createBevelBorder(
                        EtchedBorder.RAISED, Color.red, Color.RED));
            } else {
                component.setBorder(null);
            }

        } else {
            if (shouldColor) {
                component.setForeground(Color.red);
            } else {
                component.setForeground(null);
            }
        }
    }

    private void createComponents() {
        setLayout(new GridBagLayout());

        // CR comboBox
        String[] crPossibilities = new String[31];
        for (int i = 0; i < 31; i++) {
            if (i == 0) {
                crPossibilities[i] = "-Choose a CR-";
            } else {
                crPossibilities[i] = "CR " + i;
            }
        }
        crCombo = new JComboBox(crPossibilities);
        crCombo.setEditable(false);
        crCombo.setFocusable(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5, 0, 0, 0);
        add(crCombo, c);

        // All Labels and TextFields
        nameLabel = new JLabel("Name");
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 0, 5);
        add(nameLabel, c);

        nameField = new JTextField();
        nameField.setColumns(12);
        nameField.requestFocus();
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 0, 0, 5);
        add(nameField, c);

        acLabel = new JLabel("Armor Class (AC)");
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 5, 0, 5);
        add(acLabel, c);

        acField = new JTextField();
        acField.setColumns(3);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5, 0, 0, 5);
        add(acField, c);

        hpLabel = new JLabel("Hit Points (HP)");
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5, 5, 0, 5);
        add(hpLabel, c);

        hpField = new JTextField();
        hpField.setColumns(3);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5, 0, 0, 5);
        add(hpField, c);

        idLabel = new JLabel("Temporary ID");
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5, 5, 5, 5);
        add(idLabel, c);

        idField = new JTextField("(optional)");
        idField.addFocusListener(this);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(5, 0, 5, 5);
        add(idField, c);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (idField.getText().equals("(optional)")) {
            idField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
