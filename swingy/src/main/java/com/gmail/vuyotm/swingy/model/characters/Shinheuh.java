package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Artifact;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter

public abstract class Shinheuh extends Character {

    @NotEmpty
    private String              type;
    private int                 expGiven;
    private Artifact            itemDrop;
    public static final String  NET_DOLPHIN = "net dolphin";
    public static final String  STRIPED_GROUND_PIG = "striped ground pig";
    public static final String  WHITE_STEEL_EEL = "white steel eel";
    public static final String  BARRACUDA = "barracuda";
    public static final int     MAP_FILL_RATE = 4;

    public Shinheuh(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String type) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts);
        this.type = type;
    }

}
