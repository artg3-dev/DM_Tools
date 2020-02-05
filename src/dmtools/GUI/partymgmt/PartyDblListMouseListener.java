/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.partymgmt;

import dmtools.GUI.entityguicomponents.pcdialogs.EditPcDialog;
import dmtools.GUI.DMToolsGui;
import dmtools.game.entities.PC;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author A3
 */
public class PartyDblListMouseListener implements MouseListener{
    private PartyDoubleList partyDoubleList;

    public PartyDblListMouseListener(PartyDoubleList partyDoubleList) {
        this.partyDoubleList = partyDoubleList;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
