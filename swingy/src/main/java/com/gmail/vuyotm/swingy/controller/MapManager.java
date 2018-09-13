package com.gmail.vuyotm.swingy.controller;

import com.gmail.vuyotm.swingy.model.Map;
import com.gmail.vuyotm.swingy.model.characters.Regular;

import javax.validation.constraints.NotNull;

public class MapManager {

    @NotNull
    private Map map;

    public MapManager(Map map) {
        this.map = map;
    }

    public boolean hasCrossedMapBorder(Regular regular) {
        int x;
        int y;
        int mapSize;

        if ((regular == null) || (map == null))
            return (false);
        x = regular.getX();
        y = regular.getY();
        mapSize = map.getWidth();
        if ((x < 0) || (x >= mapSize) || (y < 0) || (y >= mapSize))
            return (true);
        else
            return (false);
    }

    public boolean hasEncounteredShinheuh(Regular regular) {
        if ((regular == null) || (map == null))
            return (false);
        if ((map.getGrid())[regular.getY()][regular.getX()] != null)
            return (true);
        else
            return (false);
    }

}
