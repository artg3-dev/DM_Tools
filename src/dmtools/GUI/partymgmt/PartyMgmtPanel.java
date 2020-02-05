/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.partymgmt;

import dmtools.GUI.entityguicomponents.pcdialogs.CreatePcDialog;
import dmtools.GUI.entityguicomponents.pcdialogs.EditPcDialog;
import dmtools.GUI.DMToolsGui;
import dmtools.game.entities.PC;
import dmtools.playermgmt.PlayerParty;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author A3
 */
public class PartyMgmtPanel extends JPanel implements ActionListener {

    private PlayerParty party;
    private PartyDoubleList partyDoubleList;
    private JButton editPC, addNew, delete, editPartyName, submitPartyName;
    private JTextField partyName;
    private JPanel editNamePanel;

    public PartyMgmtPanel(PlayerParty party) {
        this.party = party;
        setLayout(new GridBagLayout());
        createComponents();
    }
    
    public PlayerParty getParty() {
        this.party = partyDoubleList.getUpdatedParty();
        return this.party;
    }

    private void createComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, 0, 5);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;
        JPanel filler = new JPanel();
        add(filler, c);

        addNew = new JButton("Add New PC");
        addNew.addActionListener(this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        add(addNew, c);

        editPC = new JButton("Edit PC");
        editPC.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        add(editPC, c);

        delete = new JButton("Delete PC");
        delete.addActionListener(this);
        c.gridx = 1;
        c.gridy = 3;
        add(delete, c);

        editPartyName = new JButton("Edit Party Name");
        editPartyName.addActionListener(this);
        c.gridx = 1;
        c.gridy = 4;
        add(editPartyName, c);

        //edit partyname panel
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(5, 0, 0, 5);
        add(getEditPartyNamePanel(), c);

        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;
        filler = new JPanel();
        add(filler, c);

        partyDoubleList = new PartyDoubleList(party);
        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 7;
        c.weightx = 1;
        c.weighty = 1;
        add(partyDoubleList, c);
    }

    private JPanel getEditPartyNamePanel() {
        editNamePanel = new JPanel();
        editNamePanel.setLayout(new GridBagLayout());
        editNamePanel.setBorder(BorderFactory.createDashedBorder(null));
        editNamePanel.setVisible(false);

        GridBagConstraints c = new GridBagConstraints();

        partyName = new JTextField(party.getName());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(3, 3, 0, 3);
        editNamePanel.add(partyName, c);

        submitPartyName = new JButton("Submit");
        submitPartyName.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(3, 3, 3, 3);
        editNamePanel.add(submitPartyName, c);

        return editNamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add new player button
        if (e.getSource() == addNew) {
            CreatePcDialog createDialog = new CreatePcDialog(DMToolsGui.frame,
                    true);
            try {
                PC addedPC = createDialog.getCreatedPC();
                String key = addedPC.getName() + " ("
                        + addedPC.getPlayerName() + ")";
                partyDoubleList.addObject(addedPC, key, true);
                partyDoubleList.setSelectedKey(key);
            } catch (Exception ex) {
                return;
            }
        }

        // Edit PC button
        if (e.getSource() == editPC) {
            PC pcToEdit = (PC) partyDoubleList.getSelectedObject();
            EditPcDialog editDialog = new EditPcDialog(DMToolsGui.frame,
                    pcToEdit);
            PC updatedPC = editDialog.getUpdatedPC();

            if (updatedPC != null) {
                String key = updatedPC.getName() + " ("
                        + updatedPC.getPlayerName() + ")";
                partyDoubleList.replaceObject(updatedPC, key);
            }
        }

        // Remove player button
        if (e.getSource() == delete) {
            //if there is no selection
            if (partyDoubleList.getSelectedObject() == null) {
                return;
            }

            Object[] options = {"Delete", "Cancel"};
            PC toDelete = (PC) partyDoubleList.getSelectedObject();
            String pcName = toDelete.getName();

            int n = JOptionPane.showOptionDialog(DMToolsGui.frame,
                    "Are you sure you want to permanently delete "
                    + pcName
                    + "?",
                    "Delete Playable Character",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == JOptionPane.OK_OPTION) {
                partyDoubleList.removeSelected();

                //REMOVE THE FILE HERE!
                File pcFile = new File(toDelete.getFilePath());
                if (pcFile.delete()) {
                    System.out.println("true");
                }
            }
        }

        // Edit Party Name
        if (e.getSource() == editPartyName) {
            if (editNamePanel.isVisible()) {
                editNamePanel.setVisible(false);
            } else {
                editNamePanel.setVisible(true);
                partyName.requestFocus();
            }
        }

        // Submit Party Name
        if (e.getSource() == submitPartyName) {
            if (!partyName.getText().isEmpty()) {
                partyDoubleList.changePartyName(partyName.getText());
                editNamePanel.setVisible(false);
            }
        }
    }
}
