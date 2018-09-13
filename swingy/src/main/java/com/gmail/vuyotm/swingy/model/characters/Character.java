package com.gmail.vuyotm.swingy.model.characters;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter

public abstract class Character {

    @Min(1)
    private int level;
    @Min(0)
    private int physicalAttack;
    @Min(0)
    private int physicalDefense;
    @Min(0)
    private int shinsooAttack;
    @Min(0)
    private int shinsooDefense;
    @Min(0)
    private int speed;
    @Min(0)
    private int hitPts;
    @Min(0)
    private int x;
    @Min(0)
    private int y;

    public Character(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts) {
        this.level = level;
        this.physicalAttack = physicalAttack;
        this.physicalDefense = physicalDefense;
        this.shinsooAttack = shinsooAttack;
        this.shinsooDefense = shinsooDefense;
        this.speed = speed;
        this.hitPts = hitPts;
    }

    public boolean hasDied() {
        if (hitPts == 0)
            return (true);
        else
            return (false);
    }

}
