/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.game.entities;

import dmtools.filehandling.ReadWritable;
import dmtools.game.entities.numericals.StatBlock;
import dmtools.game.entities.numericals.enums.Skill;
import dmtools.game.entities.numericals.enums.Stat;

/**
 *
 * @author A3
 */
public class Monster extends DNDEntity implements ReadWritable {

    private final int CR;
    private final String monsterType;

    // Statblock is a placeholder until monsters are actually developed
    public Monster(String name, String monsterType, int AC, int HP, int CR,
            String id) {
        super(name, new StatBlock(), AC, HP);
        this.CR = CR;
        this.monsterType = monsterType;
        this.id = id;
    }

    public Monster(String name, String monsterType, int AC,
            int HP, int CR) {
        super(name, new StatBlock(), AC, HP);
        this.CR = CR;
        this.monsterType = monsterType;
        this.id = "";
    }

    public int getCR() {
        return CR;
    }

    public String getType() {
        return monsterType;
    }

    @Override
    public int getProficiencyBonus() {
        return 0;
    }

    @Override
    public int getSavingThrow(Stat stat) {
        return statBlock.getModifier(stat) + getProficiencyBonus();
    }

    @Override
    public int getSkillCheck(Skill skill) {
        return statBlock.getModifier(skill.getRelevantStat());
    }

    @Override
    public String getFilePath() {
        return "Campaigns/default/Monsters/" + this.name + ".mon";
    }

    public static String getFilePath(String name) {
        return "Campaigns/default/Monsters/" + name + ".mon";
    }
}
