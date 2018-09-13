package com.gmail.vuyotm.swingy.model;

import com.gmail.vuyotm.swingy.model.characters.Regular;
import com.gmail.vuyotm.swingy.model.characters.Shinheuh;
import com.gmail.vuyotm.swingy.util.ShinheuhFactory;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter

public class Map {

    @Min(0)
    private int             width;
    @Min(0)
    private int             height;
    @Min(0)
    private int             centerXY;
    @Min(0)
    private int             totalGridCells;
    private Object[][]      grid;
    @NotNull
    private Regular         player;
    private List<Shinheuh>  shinheuhList = new ArrayList<Shinheuh>();

    public Map(Regular player) {
        int mapSize;
        int playerLevel;

        if (player == null)
            throw new IllegalArgumentException("Player may not be null.");
        playerLevel = player.getLevel();
        if (playerLevel <= 0)
            throw new IllegalArgumentException("Player level must be a positive number.");
        mapSize = ((playerLevel - 1) * 5) + 10 - (playerLevel % 2);
        width = mapSize;
        height = mapSize;
        totalGridCells = width * height;
        centerXY = mapSize / 2;
        grid = new Object[mapSize][mapSize];
        for (int i = 0; i < mapSize; ++i) {
            for (int j = 0; j < mapSize; ++j) {
                (grid)[j][i] = null;
            }
        }
        player.setX(centerXY);
        player.setY(centerXY);
        grid[centerXY][centerXY] = player;
        this.player = player;
        populateMapWithShinheuh();
    }

    private void populateMapWithShinheuh() {
        for (int i = 0; i < (totalGridCells / Shinheuh.MAP_FILL_RATE); ++i) {
            int         x;
            int         y;
            Shinheuh    randShinheuh;

            x = ThreadLocalRandom.current().nextInt(width);
            y = ThreadLocalRandom.current().nextInt(height);
            while (grid[y][x] != null) {
                x = ThreadLocalRandom.current().nextInt(width);
                y = ThreadLocalRandom.current().nextInt(height);
            }
            randShinheuh = ShinheuhFactory.randShinheuh(player.getLevel());
            randShinheuh.setX(x);
            randShinheuh.setY(y);
            grid[y][x] = randShinheuh;
            shinheuhList.add(randShinheuh);
        }
    }

    @Override
    public String toString() {
        String  map;

        map = "";
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (grid[j][i] == null)
                    map += "0";
                else if (grid[j][i] instanceof Shinheuh)
                    map += "S";
                else if (grid[j][i] instanceof Regular)
                    map += "X";
            }
            map += System.lineSeparator();
        }
        return (map);
    }

}
