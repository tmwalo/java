package com.gmail.vuyotm.swingy.controller;

import com.gmail.vuyotm.swingy.model.characters.Regular;
import com.gmail.vuyotm.swingy.model.characters.Shinheuh;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter

public class FightManager {

    @NotNull
    private Regular         regular;
    @NotNull
    private RegularManager  regularManager;
    @NotNull
    private Shinheuh        shinheuh;
    @NotNull
    private ShinheuhManager shinheuhManager;
    private String          attackRecord = "";

    public FightManager(Regular regular, RegularManager regularManager, Shinheuh shinheuh, ShinheuhManager shinheuhManager) {
        this.regular = regular;
        this.regularManager = regularManager;
        this.shinheuh = shinheuh;
        this.shinheuhManager = shinheuhManager;
    }

    public void launchPhysicalAttack() {
        int     regularOriginalHp;
        int     shinheuhOriginalHp;
        int     fallInShinheuhHp;
        int     fallInRegularHp;

        attackRecord = "";
        regularOriginalHp = regular.getHitPts();
        shinheuhOriginalHp = shinheuh.getHitPts();
        if (regular.getSpeed() >= shinheuh.getSpeed()) {
            regularManager.physicalAttack(shinheuh);
            fallInShinheuhHp = shinheuhOriginalHp - shinheuh.getHitPts();
            attackRecord += regular.getName() + " launched a physical attack that reduced " + System.lineSeparator() + shinheuh.getType() + " health by " + fallInShinheuhHp + " points." + System.lineSeparator();
            if (shinheuh.hasDied())
                return ;
            shinheuhManager.physicalAttack(regular);
            fallInRegularHp = regularOriginalHp - regular.getHitPts();
            attackRecord += shinheuh.getType() + " launched a physical attack that reduced " + System.lineSeparator() + regular.getName() + " health by " + fallInRegularHp + " points. " + System.lineSeparator();
        }
        else {
            shinheuhManager.physicalAttack(regular);
            fallInRegularHp = regularOriginalHp - regular.getHitPts();
            attackRecord += shinheuh.getType() + " launched a physical attack that reduced " + System.lineSeparator() + regular.getName() + " health by " + fallInRegularHp + " points. " + System.lineSeparator();
            if (regular.hasDied())
                return ;
            regularManager.physicalAttack(shinheuh);
            fallInShinheuhHp = shinheuhOriginalHp - shinheuh.getHitPts();
            attackRecord += regular.getName() + " launched a physical attack that reduced " + System.lineSeparator() + shinheuh.getType() + " health by " + fallInShinheuhHp + " points." + System.lineSeparator();
        }
    }

    public void launchShinsooAttack() {
        int     regularOriginalHp;
        int     shinheuhOriginalHp;
        int     fallInShinheuhHp;
        int     fallInRegularHp;

        attackRecord = "";
        regularOriginalHp = regular.getHitPts();
        shinheuhOriginalHp = shinheuh.getHitPts();
        if (regular.getSpeed() >= shinheuh.getSpeed()) {
            regularManager.shinsooAttack(shinheuh);
            fallInShinheuhHp = shinheuhOriginalHp - shinheuh.getHitPts();
            attackRecord += regular.getName() + " launched a shinsoo attack that reduced " + System.lineSeparator() + shinheuh.getType() + " health by " + fallInShinheuhHp + " points." + System.lineSeparator();
            if (shinheuh.hasDied())
                return ;
            shinheuhManager.physicalAttack(regular);
            fallInRegularHp = regularOriginalHp - regular.getHitPts();
            attackRecord += shinheuh.getType() + " launched a physical attack that reduced " + System.lineSeparator() + regular.getName() + " health by " + fallInRegularHp + " points. " + System.lineSeparator();
        }
        else {
            shinheuhManager.physicalAttack(regular);
            fallInRegularHp = regularOriginalHp - regular.getHitPts();
            attackRecord += shinheuh.getType() + " launched a physical attack that reduced " + System.lineSeparator() + regular.getName() + " health by " + fallInRegularHp + " points. " + System.lineSeparator();
            if (regular.hasDied())
                return ;
            regularManager.shinsooAttack(shinheuh);
            fallInShinheuhHp = shinheuhOriginalHp - shinheuh.getHitPts();
            attackRecord += regular.getName() + " launched a shinsoo attack that reduced " + System.lineSeparator() + shinheuh.getType() + " health by " + fallInShinheuhHp + " points." + System.lineSeparator();
        }
    }

    public void endureWhenFailedToRun() {
        int     regularOriginalHp;
        int     fallInRegularHp;

        attackRecord = "";
        regularOriginalHp = regular.getHitPts();
        shinheuhManager.physicalAttack(regular);
        fallInRegularHp = regularOriginalHp - regular.getHitPts();
        attackRecord += shinheuh.getType() + " launched a physical attack that reduced " + System.lineSeparator() + regular.getName() + " health by " + fallInRegularHp + " points. " + System.lineSeparator();
    }

}
