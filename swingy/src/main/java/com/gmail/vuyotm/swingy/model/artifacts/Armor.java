package com.gmail.vuyotm.swingy.model.artifacts;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;

@Getter
@Setter

public class Armor extends Artifact {

    @Min(0)
    private int     physicalDefenseBonus;
    @Min(0)
    private int     shinsooDefenseBonus;

    public Armor(String type, int level, int physicalDefenseBonus, int shinsooDefenseBonus) {
        super(type, level);
        this.physicalDefenseBonus = physicalDefenseBonus;
        this.shinsooDefenseBonus = shinsooDefenseBonus;
    }

}
