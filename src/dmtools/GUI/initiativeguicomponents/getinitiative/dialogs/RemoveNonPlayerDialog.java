/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.GUI.initiativeguicomponents.getinitiative.dialogs;

import dmtools.game.entities.DNDEntity;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author A3
 */
public class RemoveNonPlayerDialog extends JDialog 
        implements PropertyChangeListener{
    
    private JOptionPane optionPane;
    private JList list;
    private HashMap<String, DNDEntity> names;
    private List<String> namesToRemove;
    
    public RemoveNonPlayerDialog(Frame frame, ArrayList<DNDEntity> entities) {
        super(frame, true);
        createComponents(entities);

        setTitle("Remove Non-Players from Encounter");
        String createButtonTxt = "Remove";
        Object[] options = {createButtonTxt};

        optionPane = new JOptionPane(list,
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
    
    public ArrayList<DNDEntity> getEntitiesToRemove() {
        if (namesToRemove == null) {
            return null;
        }
        
        ArrayList<DNDEntity> toRemove = new ArrayList();
        for (int i = 0; i < namesToRemove.size(); i ++) {
            toRemove.add(names.get(namesToRemove.get(i)));
        }
        
        return toRemove;
    }

    private void createComponents(ArrayList<DNDEntity> entities) {
        DefaultListModel listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        names = new HashMap();
        int j = 0;
        for (DNDEntity i : entities) {
            names.put(i.getUniqueName(), i);
            listModel.addElement(i.getUniqueName());
        }
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
            * or the monster is created
             */
            
            namesToRemove = list.getSelectedValuesList();
            dispose();
        }
    }
}
