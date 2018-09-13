package com.gmail.vuyotm.swingy.model.artifacts;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;

@Getter
@Setter

public class Weapon extends Artifact {

    @Min(0)
    private int     physicalAttackBonus;
    @Min(0)
    private int     shinsooAttackBonus;

    public Weapon(String type, int level, int physicalAttackBonus, int shinsooAttackBonus) {
        super(type, level);
        this.physicalAttackBonus = physicalAttackBonus;
        this.shinsooAttackBonus = shinsooAttackBonus;
    }

}
