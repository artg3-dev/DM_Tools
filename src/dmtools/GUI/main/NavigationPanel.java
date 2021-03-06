/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.main;

import dmtools.GUI.LayoutConstants;
import dmtools.GUI.main.DisplayPanel;
import dmtools.GUI.buttons.custombuttons.EncounterButton;
import dmtools.GUI.buttons.custombuttons.PartyManagementButton;
import dmtools.GUI.buttons.custombuttons.DMToolsButton;
import dmtools.GUI.buttons.custombuttons.SettingsButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author A3
 */
public class NavigationPanel extends JPanel implements ActionListener {

    private EncounterButton encounter;
    private PartyManagementButton party;
    private DMToolsButton dm;
    private SettingsButton settings;
    
    private final DisplayPanel display;
    
    public NavigationPanel(DisplayPanel display) {
        super();
        this.display = display;
        createComponents();
    }
    
    private void createComponents () {
        setBackground(LayoutConstants.NAV_PANEL_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.weighty = 1.0;

        //New Encounter Button
        encounter = new EncounterButton();
        encounter.addActionListener(this);
        c.gridy = 0;
        add(encounter, c);

        //Party Mgmt Button
        party = new PartyManagementButton();
        party.addActionListener(this);
        c.gridy = 1;
        add(party, c);
        
        //DM Tools Button
        dm = new DMToolsButton();
        dm.addActionListener(this);
        c.gridy = 2;
        add(dm, c);
        
        //Settings Button
        settings = new SettingsButton();
        settings.addActionListener(this);
        c.gridy = 3;
        add(settings, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Encounter Button
        if (e.getSource() == encounter) {
            display.show(DisplayPanel.ENCOUNTER);
        }
        
        // Party Mgmt Button
        if (e.getSource() == party) {
            display.show(DisplayPanel.PARTYMGMT);
        }
        
        // DM Tools
        if (e.getSource() == dm) {
            display.show(DisplayPanel.COMING_SOON);
        }
        
        // Settings
        if (e.getSource() == settings) {
            display.show(DisplayPanel.COMING_SOON);
        }
    }
}
