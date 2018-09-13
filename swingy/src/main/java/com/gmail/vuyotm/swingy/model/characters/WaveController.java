package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;

public class WaveController extends Regular {

    public WaveController(String name) {
        this(1, 2, 5, 10, 10, 4, 15, name, Regular.WAVE_CONTROLLER, 0, null, null, null);
    }

    public WaveController(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String name, String classType, int experience, Helm helm, Armor armor, Weapon weapon) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts, name, classType, experience, helm, armor, weapon);
    }

}
