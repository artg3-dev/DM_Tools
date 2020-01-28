/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.initiativeguicomponents.getinitiative.panels;

import dmtools.GUI.entityguicomponents.horde.MonsterComboBox;
import dmtools.playermgmt.PlayerParty;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author A3
 */
public class GetInitiativePanel extends JPanel {

    private PlayerParty party;
    private JLabel header;
    private PlayerGetInitiativePanel pIniPanel;
    private NonPlayerGetInitiativePanel nIniPanel;

    private JButton beginButton, addMonButton, addHordeButton;
    private MonsterComboBox monBox;

    public GetInitiativePanel(PlayerParty party) {
        super();
        this.party = party;
        createComponents();
    }

    private void createComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c;

        //Header
        header = new JLabel("Create a New Encounter", SwingConstants.CENTER);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 35f));
        header.setBackground(Color.YELLOW);
        header.setOpaque(true);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 5, 0, 5);
        add(header, c);

        // Player Initiatives
        pIniPanel = new PlayerGetInitiativePanel(party);
        pIniPanel.setBackground(Color.LIGHT_GRAY);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(5, 5, 0, 5);
        add(pIniPanel, c);
        
        // Non Player Initiatives
        nIniPanel = new NonPlayerGetInitiativePanel();
        nIniPanel.setBackground(Color.pink);
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 0.1;
        c.insets = new Insets(5, 5, 5, 5);
        add(nIniPanel, c);

        // Monster Combobox
        monBox = new MonsterComboBox("-Create Monster-");
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 5, 0, 5);
        add(monBox, c);

        // Add Monster Button
        addHordeButton = new JButton("+ Horde");
        addMonButton = new JButton("+");
        addMonButton.setPreferredSize(addHordeButton.getPreferredSize());
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 4;
        c.insets = new Insets(0, 5, 0, 0);
        add(addMonButton, c);

        // Add Horde Button
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 5);
        add(addHordeButton, c);

        // Begin Button
        beginButton = new JButton("Begin Encounter");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);
        add(beginButton, c);
        
        // Filler
        JPanel filler = new JPanel();
//        filler.setBackground(pIniPanel.getBackground());
        filler.setBackground(Color.red);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 4;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.2;
        add(filler, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;

    }
}