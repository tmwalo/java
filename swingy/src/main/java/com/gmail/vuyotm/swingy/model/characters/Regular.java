package com.gmail.vuyotm.swingy.model.characters;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

@Getter
@Setter

public abstract class Regular extends Character {

    @Min(0)
    private int                 id;
    @NotEmpty
    private String              name;
    @NotEmpty
    private String              classType;
    @Min(0)
    private int                 experience;
    private Helm                helm;
    private Armor               armor;
    private Weapon              weapon;
    public static final String  SCOUT = "scout";
    public static final String  LIGHT_BEARER = "light bearer";
    public static final String  FISHERMAN = "fisherman";
    public static final String  SPEAR_BEARER = "spear bearer";
    public static final String  WAVE_CONTROLLER = "wave controller";

    public Regular(int level, int physicalAttack, int physicalDefense, int shinsooAttack, int shinsooDefense, int speed, int hitPts, String name, String classType, int experience, Helm helm, Armor armor, Weapon weapon) {
        super(level, physicalAttack, physicalDefense, shinsooAttack, shinsooDefense, speed, hitPts);
        this.name = name;
        this.classType = classType;
        this.experience = experience;
        this.helm = helm;
        this.armor = armor;
        this.weapon = weapon;
    }

    public void setHelm(Helm helm) {
        this.helm = helm;
        if (this.helm != null)
            this.setHitPts(this.getHitPts() + this.getHelm().getHitPtsBonus());
    }

}
