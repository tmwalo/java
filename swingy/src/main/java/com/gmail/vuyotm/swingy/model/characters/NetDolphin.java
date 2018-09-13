package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Weapon;

public class NetDolphin extends Shinheuh {

    private static final int    baseExp = 100;

    public NetDolphin() {
        this(1, 1, 2, 1, 2, 1, 10, "net dolphin");
    }

    public NetDolphin(int level) {
        this(level, 1 * level, 2 * level, 1 * level, 2 * level, 1 * level, 10, "net dolphin");
    }

    public NetDolphin(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String type) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, type);
        this.setExpGiven(baseExp * level);
        setItemDrop(new Weapon("needle", level, 2 * level, 0));
    }

}
