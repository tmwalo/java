package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;

public class SpearBearer extends Regular {

    public SpearBearer(String name) {
        this(1, 8, 8, 2, 6, 6, 15, name, Regular.SPEAR_BEARER, 0, null, null, null);
    }

    public SpearBearer(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String name, String classType, int experience, Helm helm, Armor armor, Weapon weapon) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, name, classType, experience, helm, armor, weapon);
    }

}
