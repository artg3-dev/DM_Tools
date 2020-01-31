/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.initiativeguicomponents.getinitiative.panels;

import dmtools.GUI.LayoutConstants;
import dmtools.game.entities.DNDEntity;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<DNDEntity> entities;

    public NonPlayerGetInitiativePanel() {
        super();
        inputs = new HashMap();
        labels = new HashMap();
        entities = new ArrayList();
        createComponents();
    }

    public ArrayList<DNDEntity> getEntities() {
        return entities;
    }

    public void addEntity(DNDEntity e) {
        entities.add(e);
        Collections.sort(entities);

        // Label
        JLabel name = new JLabel(e.getName());
        labels.put(e, name);

        // TextField
        JTextField tf = new JTextField(2);
        inputs.put(e, tf);

        updateEntityList();
        revalidate();
        repaint();
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

    private void updateEntityList() {
        int fillerY = 1;
        GridBagConstraints c = new GridBagConstraints();
        for (DNDEntity i : entities) {
            // Label
            c.gridx = 0;
            c.gridy = entities.indexOf(i) + 1;
            c.weightx = 1;
            c.anchor = GridBagConstraints.FIRST_LINE_END;
            c.insets = new Insets(5, 5, 0, 5);
            add(labels.get(i), c);

            // TextField
            c.gridx = 1;
            c.anchor = GridBagConstraints.LINE_START;
            c.insets = new Insets(5, 0, 0, 5);
            add(inputs.get(i), c);
            fillerY = c.gridy;
        }
        
        addFiller(fillerY);
    }

    private void createComponents() {
        setLayout(new GridBagLayout());
        setBackground(LayoutConstants.BEIGE);
        JLabel header = new JLabel("Non Players");
        header.setFont(header.getFont().deriveFont(Font.BOLD, 20f));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 0, 5);
        add(header, c);
        
        addFiller(1);
    }
    
    private void addFiller(int index) {
        GridBagConstraints c = new GridBagConstraints();
        JPanel filler = new JPanel();
//        filler.setBackground(getBackground());
        filler.setBackground(Color.red);
        c.gridx = 0;
        c.gridy = index;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0.1;
        add(filler, c);
        
    }
}