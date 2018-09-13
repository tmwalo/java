package com.gmail.vuyotm.swingy.model.artifacts;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

@Getter
@Setter

public abstract class Artifact {

    @NotEmpty
    private String  type;
    @Min(1)
    private int     level;

    public Artifact(String type, int level) {
        this.type = type;
        this.level = level;
    }

}
