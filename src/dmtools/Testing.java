/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools;

import dmtools.filehandling.FileHandler;
import dmtools.game.entities.Horde;
import dmtools.game.entities.Monster;

/**
 *
 * @author A3
 */
public class Testing {
    public static void main(String[] args) {
        Monster monster;
        try {
        monster = (Monster) FileHandler.loadFromName("Testing", 
                FileHandler.MONSTER_FILE);
        Monster uMonster = new Monster("Unique Monster", "", 35, 35, 35, "I am Unique");
        Horde horde = new Horde(monster, 5, "TEST");
            System.out.println(horde.getUniqueName());
            System.out.println(uMonster.getUniqueName());
            System.out.println(monster.getUniqueName());
            System.out.println(monster.getUniqueName().length());
        } catch (Exception e) {
        }
    }
}
