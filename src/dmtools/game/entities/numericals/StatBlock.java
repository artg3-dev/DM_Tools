/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.game.entities.numericals;

import dmtools.game.entities.numericals.enums.Stat;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author A3
 */
public class StatBlock {

    private EnumMap<Stat, Integer> statBlock;

    public StatBlock(EnumMap<Stat, Integer> statBlock) {
        this.statBlock = statBlock;
    }

    public StatBlock(int[] stats) {
        this.statBlock = new EnumMap<>(Stat.class);
        setStats(stats);
    }

    public StatBlock() {
        EnumMap<Stat, Integer> baseStats = new EnumMap<>(Stat.class);
        baseStats.put(Stat.STR, 10);
        baseStats.put(Stat.DEX, 10);
        baseStats.put(Stat.CON, 10);
        baseStats.put(Stat.INT, 10);
        baseStats.put(Stat.WIS, 10);
        baseStats.put(Stat.CHA, 10);
        this.statBlock = baseStats;
    }

    public int getStat(Stat s) {
        return statBlock.get(s);
    }

    public void setStat(Stat s, int value) {
        statBlock.put(s, value);
    }

    public boolean setStats(int[] stats) {
        if (stats.length != 6) {
            return false;
        }
        statBlock.put(Stat.STR, stats[0]);
        statBlock.put(Stat.DEX, stats[1]);
        statBlock.put(Stat.CON, stats[2]);
        statBlock.put(Stat.INT, stats[3]);
        statBlock.put(Stat.WIS, stats[4]);
        statBlock.put(Stat.CHA, stats[5]);
        return true;
    }

    public int getModifier(Stat s) {
        double modifier = statBlock.get(s) - 10;
        return (int) Math.floor(modifier / 2);
    }

    public String write() {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Stat, Integer>> iterator
                = statBlock.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Stat, Integer> entry = iterator.next();
            sb.append(entry.getValue());
            if (iterator.hasNext()) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public String printStat(Stat stat) {
        StringBuilder sb = new StringBuilder();
        int statValue = statBlock.get(stat);
        int modifier = getModifier(stat);
        sb.append(statValue);
        sb.append(modifier > 0 ? " (+" : " (");
        sb.append(modifier);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toString() {
//        StringBuilder returnString = new StringBuilder();
//        for (Stat i : statBlock.keySet()) {
//            returnString.append(i);
//            returnString.append(": ");
//            returnString.append(statBlock.get(i));
//            returnString.append("\n");
//        }
//        return returnString.toString();
        StringBuilder rS = new StringBuilder();
        Iterator<Map.Entry<Stat, Integer>> iterator = statBlock.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Stat, Integer> entry = iterator.next();
            rS.append(entry.getKey());
            rS.append(": ");
            rS.append(entry.getValue());
            if (iterator.hasNext()) {
                rS.append("\n");
            }
        }

        return rS.toString();
    }
}
