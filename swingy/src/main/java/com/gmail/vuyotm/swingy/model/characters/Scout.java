package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;

public class Scout extends Regular {

    public Scout(String name) {
        this(1, 3, 4, 3, 3, 10, 12, name, Regular.SCOUT, 0, null, null, null);
    }

    public Scout(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String name, String classType, int experience, Helm helm, Armor armor, Weapon weapon) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, name, classType, experience, helm, armor, weapon);
    }

}
