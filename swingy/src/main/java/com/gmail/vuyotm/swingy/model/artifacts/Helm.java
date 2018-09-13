package com.gmail.vuyotm.swingy.model.artifacts;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;

@Getter
@Setter

public class Helm extends Artifact {

    @Min(0)
    private int     hitPtsBonus;

    public Helm(String type, int level, int hitPtsBonus) {
        super(type, level);
        this.hitPtsBonus = hitPtsBonus;
    }

}
