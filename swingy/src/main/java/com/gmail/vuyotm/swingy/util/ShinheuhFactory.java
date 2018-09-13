package com.gmail.vuyotm.swingy.util;

import com.gmail.vuyotm.swingy.model.characters.*;

import java.util.concurrent.ThreadLocalRandom;

public abstract class ShinheuhFactory {

    public static Shinheuh newShinheuh(String shinheuhType) {
        return (newShinheuh(shinheuhType, 1));
    }

    public static Shinheuh newShinheuh(String shinheuhType, int level) {

        if (shinheuhType.equals(Shinheuh.NET_DOLPHIN)) {
            NetDolphin shinheuh;

            shinheuh = new NetDolphin(level);
            return (shinheuh);
        }
        else if (shinheuhType.equals(Shinheuh.STRIPED_GROUND_PIG)) {
            StripedGroundPig shinheuh;

            shinheuh = new StripedGroundPig(level);
            return (shinheuh);
        }
        else if (shinheuhType.equals(Shinheuh.WHITE_STEEL_EEL)) {
            WhiteSteelEel shinheuh;

            shinheuh = new WhiteSteelEel(level);
            return (shinheuh);
        }
        else if (shinheuhType.equals(Shinheuh.BARRACUDA)) {
            Barracuda shinheuh;

            shinheuh = new Barracuda(level);
            return (shinheuh);
        }
        else {
            throw new IllegalArgumentException(shinheuhType + " is an unknown type for shinheuh.");
        }

    }

    private static String randShinheuhType() {
        int randNum;
        int min;
        int max;

        min = 1;
        max = 100;
        randNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        if ((randNum >= 1) && (randNum <= 10))
            return (Shinheuh.BARRACUDA);
        else if ((randNum >= 11) && (randNum <= 30))
            return (Shinheuh.WHITE_STEEL_EEL);
        else if ((randNum >= 31) && (randNum <= 60))
            return (Shinheuh.STRIPED_GROUND_PIG);
        else
            return (Shinheuh.NET_DOLPHIN);
    }

    public static Shinheuh randShinheuh() {
        String shinheuhType;

        shinheuhType = randShinheuhType();
        return (newShinheuh(shinheuhType));
    }

    public static Shinheuh randShinheuh(int level) {
        String shinheuhType;

        shinheuhType = randShinheuhType();
        return (newShinheuh(shinheuhType, level));
    }

}
