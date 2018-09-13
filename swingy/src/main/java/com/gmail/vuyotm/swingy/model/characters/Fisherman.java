package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;

public class Fisherman extends Regular {

    public Fisherman(String name) {
        this(1, 10, 10, 1, 5, 2, 20, name, Regular.FISHERMAN, 0, null, null, null);
    }

    public Fisherman(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String name, String classType, int experience, Helm helm, Armor armor, Weapon weapon) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, name, classType, experience, helm, armor, weapon);
    }

}
