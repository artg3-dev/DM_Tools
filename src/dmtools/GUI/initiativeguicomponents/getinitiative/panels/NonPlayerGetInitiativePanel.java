/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.initiativeguicomponents.getinitiative.panels;

import dmtools.GUI.LayoutConstants;
import dmtools.game.entities.DNDEntity;
import dmtools.game.entities.EntityTracker;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author A3
 */
public class NonPlayerGetInitiativePanel extends JPanel {

    private Map<DNDEntity, JTextField> inputs;
    private Map<DNDEntity, JLabel> labels;
    private EntityTracker entities;

    public NonPlayerGetInitiativePanel() {
        super();
        inputs = new HashMap();
        labels = new HashMap();
        entities = new EntityTracker();
        createComponents();
    }

    public Map<DNDEntity, Integer> getInitiatives()
            throws IllegalArgumentException {
        Map<DNDEntity, Integer> initiatives = new HashMap();
        for (DNDEntity i : inputs.keySet()) {
            try {
                int ini = Integer.parseInt(inputs.get(i).getText());
                initiatives.put(i, ini);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid initiative found");
            }
        }

        return initiatives;
    }

    public ArrayList<DNDEntity> getEntities() {
        return entities.getEntities();
    }

    public void addEntity(DNDEntity e) {
        entities.add(e);
        entities.sort();

        // Label
        JLabel name = new JLabel(e.getUniqueName());
        labels.put(e, name);

        // TextField
        JTextField tf = new JTextField(2);
        inputs.put(e, tf);

        updateEntityList();
    }

    public void removeEntity(DNDEntity e) {
        entities.remove(e);
        remove(labels.get(e));
        remove(inputs.get(e));
        labels.remove(e);
        inputs.remove(e);

        revalidate();
        repaint();
    }

    public boolean hasValidInfo() {
        boolean isValid = true;
        for (DNDEntity i : inputs.keySet()) {
            if (inputs.get(i).getText().equals("")) {
                highlight(i, true);
                isValid = false;
            } else {
                try {
                    Integer.parseInt(inputs.get(i).getText());
                    highlight(i, false);
                } catch (NumberFormatException e) {
                    highlight(i, true);
                    isValid = false;
                }
            }
        }

        return isValid;
    }

    private void highlight(DNDEntity e, boolean shouldColor) {
        if (shouldColor) {
            labels.get(e).setForeground(Color.red);
        } else {
            labels.get(e).setForeground(null);
        }
    }

    private void updateEntityList() {
        removeAll();
        createHeader();
        int fillerY = 1;
        GridBagConstraints c = new GridBagConstraints();
        ArrayList<DNDEntity> entityList = entities.getEntities();
        if (!labels.isEmpty() && !inputs.isEmpty()) {
            for (DNDEntity i : entityList) {
                // Label
                c.gridx = 0;
                c.gridy = entityList.indexOf(i) + 1;
                c.weightx = 1;
                c.anchor = GridBagConstraints.FIRST_LINE_END;
                c.insets = new Insets(5, 5, 0, 5);
                add(labels.get(i), c);

                // TextField
                c.gridx = 1;
                c.anchor = GridBagConstraints.LINE_START;
                c.insets = new Insets(5, 0, 0, 5);
                add(inputs.get(i), c);
                fillerY = c.gridy + 1;
            }
        }

        addFiller(fillerY);
        revalidate();
        repaint();
    }

    private void createComponents() {
        setLayout(new GridBagLayout());
        setBackground(LayoutConstants.BEIGE);
        createHeader();
        addFiller(1);
    }

    private void createHeader() {
        JLabel header = new JLabel("Non Players");
        header.setFont(header.getFont().deriveFont(Font.BOLD, 20f));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 0, 5);
        add(header, c);
    }

    private void addFiller(int index) {
        GridBagConstraints c = new GridBagConstraints();
        JPanel filler = new JPanel();
        filler.setBackground(getBackground());
//        filler.setBackground(Color.red);
        c.gridx = 0;
        c.gridy = index;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        add(filler, c);
    }
}
