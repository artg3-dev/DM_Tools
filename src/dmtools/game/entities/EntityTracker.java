/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.game.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author A3
 */
public class EntityTracker {

    private final ArrayList<DNDEntity> entities;

    public EntityTracker(ArrayList<DNDEntity> entities) {
        this.entities = entities;
    }

    public EntityTracker() {
        this(new ArrayList<DNDEntity>());
    }
    
    public ArrayList<DNDEntity> getEntities() {
        return entities;
    }

    public void add(DNDEntity e) throws IllegalArgumentException{
        // Only accepts duplicates if the user has not set a unique id
        if (entities.contains(e) && e.hasUniqueID()) {
            throw new IllegalArgumentException(e.getUniqueName() + 
                    " is already included in this tracker.");
        }
        if (e.hasUniqueID()) {
            entities.add(e);
        } else {
            generateID(e);
            entities.add(e);
        }
    }

    public void remove(DNDEntity e) {
        entities.remove(e);
    }
    
    public void sort() {
        Collections.sort(entities);
    }
    
    public boolean contains(DNDEntity e) {
        return entities.contains(e);
    }

    public int getCount(DNDEntity e) {
        int count = 0;
        for (DNDEntity i : entities) {
            if (i.getName().equals(e.getName())) {
                count++;
            }
        }

        return count;
    }

    private void generateID(DNDEntity e){
        int count = getCount(e);
        if (count == 0) {
            return;
        }
        for (int i = 1; i <= count + 1; i ++) {
            e.setID("" + i);
            if (!contains(e)) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator i = entities.iterator();
        while (i.hasNext()) {
            DNDEntity next = (DNDEntity) i.next();
            sb.append(next.getUniqueName());
            if (i.hasNext()) {
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
}
