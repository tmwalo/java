package com.gmail.vuyotm.swingy.controller;

import com.gmail.vuyotm.swingy.model.characters.Regular;
import com.gmail.vuyotm.swingy.model.characters.Shinheuh;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ThreadLocalRandom;

public class ShinheuhManager {

    @NotNull
    private Shinheuh    shinheuh;

    public ShinheuhManager(Shinheuh shinheuh) {
        this.shinheuh = shinheuh;
    }

    public void physicalAttack(Regular enemy) {

        int     totalEnemyDefense;
        int     effectiveAttack;
        int     newEnemyHp;

        if (enemy == null)
            throw new IllegalArgumentException("Enemy being attacked may not be null.");
        if (enemy.getArmor() != null)
            totalEnemyDefense = enemy.getPhysicalDefense() + enemy.getArmor().getPhysicalDefenseBonus();
        else
            totalEnemyDefense = enemy.getPhysicalDefense();
        if (shinheuh.getPhysicalAttack() > totalEnemyDefense)
            effectiveAttack = shinheuh.getPhysicalAttack() - totalEnemyDefense;
        else
            effectiveAttack = 0;
        if (effectiveAttack < enemy.getHitPts())
            newEnemyHp = enemy.getHitPts() - effectiveAttack;
        else
            newEnemyHp = 0;
        enemy.setHitPts(newEnemyHp);

    }

    public void shinsooAttack(Regular enemy) {

        int     totalEnemyDefense;
        int     effectiveAttack;
        int     newEnemyHp;

        if (enemy == null)
            throw new IllegalArgumentException("Enemy being attacked may not be null.");
        if (enemy.getArmor() != null)
            totalEnemyDefense = enemy.getShinsooDefense() + enemy.getArmor().getShinsooDefenseBonus();
        else
            totalEnemyDefense = enemy.getShinsooDefense();
        if (shinheuh.getShinsooAttack() > totalEnemyDefense)
            effectiveAttack = shinheuh.getShinsooAttack() - totalEnemyDefense;
        else
            effectiveAttack = 0;
        if (effectiveAttack < enemy.getHitPts())
            newEnemyHp = enemy.getHitPts() - effectiveAttack;
        else
            newEnemyHp = 0;
        enemy.setHitPts(newEnemyHp);

    }

    public boolean hasDroppedItem() {
        String  shinheuhType;
        int     randNum;
        int     min;
        int     max;

        if (shinheuh.getHitPts() != 0)
            return (false);
        min = 1;
        max = 100;
        randNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        shinheuhType = shinheuh.getType();
        if (shinheuhType.equals(Shinheuh.NET_DOLPHIN) && ((randNum >= 1) && (randNum <= 15))) {
            return (true);
        }
        else if (shinheuhType.equals(Shinheuh.STRIPED_GROUND_PIG) && ((randNum >= 1) && (randNum <= 10))) {
            return (true);
        }
        else if (shinheuhType.equals(Shinheuh.WHITE_STEEL_EEL) && ((randNum >= 1) && (randNum <= 5))) {
            return (true);
        }
        else if (shinheuhType.equals(Shinheuh.BARRACUDA) && (randNum == 1)) {
            return (true);
        }
        return (false);
    }

}
