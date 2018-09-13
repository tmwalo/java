package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;

public class WhiteSteelEel extends Shinheuh {

    private static final int    baseExp = 250;

    public WhiteSteelEel() {
        this(1, 4, 4, 0, 6, 4, 15, "white steel eel");
    }

    public WhiteSteelEel(int level) {
        this(level, 4 * level, 4 * level, 0 * level, 6 * level, 4 * level, 15, "white steel eel");
    }

    public WhiteSteelEel(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String type) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, type);
        this.setExpGiven(baseExp * level);
        setItemDrop(new Armor("basic armor", level, 2 * level, 1 * level));
    }

}
