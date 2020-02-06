/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.initiativeguicomponents.getinitiative.panels;

import dmtools.GUI.entityguicomponents.horde.CreateHordeDialog;
import dmtools.GUI.entityguicomponents.horde.MonsterComboBox;
import dmtools.GUI.entityguicomponents.monsters.CreateMonsterDialog;
import dmtools.GUI.initiativeguicomponents.RunEncounterPanel;
import dmtools.GUI.initiativeguicomponents.getinitiative.dialogs.RemoveNonPlayerDialog;
import dmtools.GUI.main.DisplayPanel;
import dmtools.filehandling.FileHandler;
import dmtools.game.entities.DNDEntity;
import dmtools.game.entities.Horde;
import dmtools.game.entities.Monster;
import dmtools.game.initiative.InitiativeTracker;
import dmtools.playermgmt.PlayerParty;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author A3
 */
public class CreateEncounterPanel extends JPanel implements ActionListener {

    private final PlayerParty party;
    private final DisplayPanel display;

    private JLabel header;
    private PlayerGetInitiativePanel pIniPanel;
    private NonPlayerGetInitiativePanel nIniPanel;

    private JButton beginButton, addMonButton, addHordeButton, removeButton;
    private MonsterComboBox monBox;

    public CreateEncounterPanel(PlayerParty party, DisplayPanel display) {
        super();
        this.party = party;
        this.display = display;
        createComponents();
    }

    public void updateParty(PlayerParty party) {
        pIniPanel.updateParty(party);
    }

    private void createComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c;

        //Header
        header = new JLabel("Create a New Encounter", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 35f));
//        header.setBackground(Color.YELLOW);
//        header.setOpaque(true);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 9;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 0, 5);
        add(header, c);

        // Player Initiatives
        pIniPanel = new PlayerGetInitiativePanel(party);
//        pIniPanel.setBackground(Color.LIGHT_GRAY);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 4;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.15;
        c.insets = new Insets(5, 5, 0, 5);
        add(pIniPanel, c);

        // Non Player Initiatives
        nIniPanel = new NonPlayerGetInitiativePanel();
//        nIniPanel.setBackground(Color.pink);
        c = new GridBagConstraints();
        c.gridx = 5;
        c.gridy = 1;
        c.gridheight = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.15;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 5, 5);
        add(nIniPanel, c);

        // Monster Combobox
        monBox = new MonsterComboBox("-Create Monster-");
        c = new GridBagConstraints();
        c.gridx = 5;
        c.gridy = 3;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 5, 0, 5);
        c.weightx = 0.15;
        add(monBox, c);

        // Initiatlize all buttons
        addHordeButton = new JButton("Horde");
        addHordeButton.addActionListener(this);

        addMonButton = new JButton("+");
        addMonButton.addActionListener(this);

        removeButton = new JButton("-");
        removeButton.addActionListener(this);

        beginButton = new JButton("Begin Encounter");
        beginButton.addActionListener(this);

        // Add Monster Button
        c = new GridBagConstraints();
        c.gridx = 5;
        c.gridy = 4;
        c.insets = new Insets(0, 5, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(addMonButton, c);

        //Add Horde Button
        c = new GridBagConstraints();
        c.gridx = 6;
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.05;
        add(addHordeButton, c);

        // Remove Button
        c = new GridBagConstraints();
        c.gridx = 7;
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(removeButton, c);

        // Begin Button
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        add(beginButton, c);

        // Left Filler
        JPanel filler = new JPanel();
        c = new GridBagConstraints();
        filler = new JPanel();
        filler.setBackground(getBackground());
//        filler.setBackground(Color.red);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 5;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.2;
        add(filler, c);

        // Middle Filler
        filler = new JPanel();
        filler.setBackground(getBackground());
//        filler.setBackground(Color.red);
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 1;
        c.gridheight = 4;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.6;
        add(filler, c);

        // Right Filler
        c = new GridBagConstraints();
        filler = new JPanel();
        filler.setBackground(getBackground());
//        filler.setBackground(Color.red);
        c.gridx = 8;
        c.gridy = 1;
        c.gridheight = 5;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.2;
        add(filler, c);

    }

    private Map<DNDEntity, Integer> getInitiatives() {
        Map<DNDEntity, Integer> initiatives = new HashMap();
        initiatives.putAll(pIniPanel.getInitiatives());
        initiatives.putAll(nIniPanel.getInitiatives());
        return initiatives;
    }

    private void showIdDialog(DNDEntity e) {
        String s = (String) JOptionPane.showInputDialog(
                null,
                "(optional)",
                "Add Unique ID to " + e.getName(),
                JOptionPane.QUESTION_MESSAGE);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            e.setID(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add Monster
        if (e.getSource().equals(addMonButton)) {
            if (monBox.getSelectedIndex() == 0) {
                CreateMonsterDialog monDialog = new CreateMonsterDialog(null);
                Monster m = monDialog.getMonster();
                if (m != null) {
                    nIniPanel.addEntity(m);
                }
                monBox.refresh();
            } else {
                try {
                    Monster m = (Monster) FileHandler.loadFromName(
                            (String) monBox.getSelectedItem(),
                            FileHandler.MONSTER_FILE);
                    showIdDialog(m);
                    nIniPanel.addEntity(m);
                } catch (IOException ex) {
                }
            }
        }

        // Add horde
        if (e.getSource().equals(addHordeButton)) {
            CreateHordeDialog hordeDialog = new CreateHordeDialog(null);
            Horde h = hordeDialog.getHorde();
            if (h != null) {
                nIniPanel.addEntity(h);
            }
            monBox.refresh();
        }

        // Remove Monster
        if (e.getSource().equals(removeButton)) {
            ArrayList<DNDEntity> currentEntities = nIniPanel.getEntities();

            if (!currentEntities.isEmpty()) {
                RemoveNonPlayerDialog rnpDialog = new RemoveNonPlayerDialog(
                        null, currentEntities);
                if (rnpDialog.getEntitiesToRemove() != null) {
                    for (DNDEntity i : rnpDialog.getEntitiesToRemove()) {
                        nIniPanel.removeEntity(i);
                    }
                }
            }
        }

        // Begin
        if (e.getSource().equals(beginButton)) {
            if (nIniPanel.hasValidInfo() && pIniPanel.hasValidInfo()) {

                /*
                * BEGIN ENCOUNTER HERE
                 */
                InitiativeTracker iniTrack
                        = new InitiativeTracker(getInitiatives());
                RunEncounterPanel runEncounter
                        = new RunEncounterPanel(iniTrack, display);
                display.beginEncounter(runEncounter);

            } else {
                JOptionPane.showMessageDialog(null,
                        "Some initiatives are invalid or incomplete",
                        "Initiative Errors",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
