package com.gmail.vuyotm.swingy.util;

import com.gmail.vuyotm.swingy.model.characters.*;

public abstract class RegularFactory {

    public static Regular newRegular(String name, String classType) {

        if (classType.equals(Regular.SCOUT)) {
            Scout player;

            player = new Scout(name);
            return (player);
        }
        else if (classType.equals(Regular.LIGHT_BEARER)) {
            LightBearer player;

            player = new LightBearer(name);
            return (player);
        }
        else if (classType.equals(Regular.FISHERMAN)) {
            Fisherman player;

            player = new Fisherman(name);
            return (player);
        }
        else if (classType.equals(Regular.SPEAR_BEARER)) {
            SpearBearer player;

            player = new SpearBearer(name);
            return (player);
        }
        else if (classType.equals(Regular.WAVE_CONTROLLER)) {
            WaveController player;

            player = new WaveController(name);
            return (player);
        }
        else {
            throw new IllegalArgumentException(classType + " is an unknown class for regulars.");
        }

    }

}
