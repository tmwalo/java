package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;

public class LightBearer extends Regular {

    public LightBearer(String name) {
        this(1, 2, 8, 3, 8, 3, 15, name, Regular.LIGHT_BEARER, 0, null, null, null);
    }

    public LightBearer(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String name, String classType, int experience, Helm helm, Armor armor, Weapon weapon) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, name, classType, experience, helm, armor, weapon);
    }

}
