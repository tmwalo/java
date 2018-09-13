package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Helm;

public class StripedGroundPig extends Shinheuh {

    private static final int    baseExp = 150;

    public StripedGroundPig() {
        this(1, 2, 3, 1, 3, 2, 12, "striped ground pig");
    }

    public StripedGroundPig(int level) {
        this(level, 2 * level, 3 * level, 1 * level, 3 * level, 2 * level, 12, "striped ground pig");
    }

    public StripedGroundPig(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String type) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, type);
        this.setExpGiven(baseExp * level);
        setItemDrop(new Helm("helm", level, 2 * level));
    }

}
