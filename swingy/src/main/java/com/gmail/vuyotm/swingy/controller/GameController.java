package com.gmail.vuyotm.swingy.controller;

import com.gmail.vuyotm.swingy.model.Map;
import com.gmail.vuyotm.swingy.model.RegularContext;
import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;
import com.gmail.vuyotm.swingy.model.characters.Regular;
import com.gmail.vuyotm.swingy.model.characters.Shinheuh;
import com.gmail.vuyotm.swingy.storage.Database;
import com.gmail.vuyotm.swingy.util.RegularFactory;
import com.gmail.vuyotm.swingy.view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    private boolean isNewRegular;

    public void startGame(BufferedReader bufferedReader) throws IOException {
        StartGameView   startGameView;

        startGameView = new StartGameView();
        startGameView.displayStartScreen(bufferedReader);
        if (startGameView.getInputData().equals(StartGameView.LOAD_REGULAR))
            loadRegular(bufferedReader);
        else if (startGameView.getInputData().equals(StartGameView.CREATE_NEW_REGULAR))
            createRegular(bufferedReader);
    }

    public void loadRegular(BufferedReader bufferedReader) throws IOException {
        ArrayList<Regular>  regulars;
        RegularContext      regularContext;
        LoadRegularView     loadRegularView;
        Regular             regular;
        Map                 map;

        regularContext = new RegularContext(Database.CONNECTION_STRING);
        regularContext.openConnection();
        regulars = regularContext.getAllRegulars();
        regularContext.close();
        loadRegularView = new LoadRegularView();
        loadRegularView.displayRegulars(regulars, bufferedReader);
        if (regulars.isEmpty())
            startGame(bufferedReader);
        else {
            regularContext.openConnection();
            regular = regularContext.getRegular(loadRegularView.getSelectedRegularID());
            regularContext.close();
            map = new Map(regular);
            isNewRegular = false;
            moveRegular(regular, map, bufferedReader);
        }
    }

    public void createRegular(BufferedReader bufferedReader) throws IOException {
        CreateNewRegularView    createNewRegularView;
        Regular                 regular;
        Map                     map;

        createNewRegularView = new CreateNewRegularView();
        createNewRegularView.displayRegularCreation(bufferedReader);
        regular = RegularFactory.newRegular(createNewRegularView.getRegularName(), createNewRegularView.getRegularPosition());
        map = new Map(regular);
        isNewRegular = true;
        moveRegular(regular, map, bufferedReader);
    }

    public void moveRegular(Regular regular, Map map, BufferedReader bufferedReader) throws IOException {
        MoveRegularView         moveRegularView;
        RegularManager          regularManager;
        MapManager              mapManager;
        int                     originalX;
        int                     originalY;
        Shinheuh                encounteredShinheuh;
        ShinheuhManager         shinheuhManager;
        StatsAndArtifactsView   statsAndArtifactsView;
        RegularContext          regularContext;

        moveRegularView = new MoveRegularView();
        regularManager = new RegularManager(regular);
        mapManager = new MapManager(map);
        statsAndArtifactsView = new StatsAndArtifactsView();
        regularContext = new RegularContext(Database.CONNECTION_STRING);
        while (true) {
            originalX = regular.getX();
            originalY = regular.getY();
            moveRegularView.displayMoveRegular(bufferedReader);
            if (moveRegularView.isHasExited()) {
                regularContext.openConnection();
                if (isNewRegular)
                    regularContext.postRegular(regular);
                else
                    regularContext.putRegular(regular);
                regularContext.close();
                System.exit(0);
            }
            if (moveRegularView.getMoveOption().equals("1"))
                regularManager.moveNorth();
            else if (moveRegularView.getMoveOption().equals("2"))
                regularManager.moveSouth();
            else if (moveRegularView.getMoveOption().equals("3"))
                regularManager.moveEast();
            else if (moveRegularView.getMoveOption().equals("4"))
                regularManager.moveWest();
            else if (moveRegularView.getMoveOption().equals("s")) {
                statsAndArtifactsView.displayStatsAndEquipment(regular);
                continue ;
            }
            if (mapManager.hasCrossedMapBorder(regular)) {
                map = new Map(regular);
                mapManager = new MapManager(map);
                moveRegularView.writeNewAreaMsg();
                continue ;
            }
            else if (mapManager.hasEncounteredShinheuh(regular)) {
                moveRegularView.writeEncounterMsg();
                encounteredShinheuh = (Shinheuh) (map.getGrid())[regular.getY()][regular.getX()];
                shinheuhManager = new ShinheuhManager(encounteredShinheuh);
                fight(regular, regularManager, encounteredShinheuh, shinheuhManager, map, originalX, originalY, bufferedReader);
                continue ;
            }
            (map.getGrid())[originalY][originalX] = null;
            (map.getGrid())[regular.getY()][regular.getX()] = regular;
        }
    }

    public void fight(Regular regular, RegularManager regularManager, Shinheuh shinheuh, ShinheuhManager shinheuhManager, Map map, int originalX, int originalY, BufferedReader bufferedReader) throws IOException {
        FightView       fightView;
        FightManager    fightManager;
        boolean         fightEnded;

        fightEnded = false;
        fightView = new FightView();
        fightManager = new FightManager(regular, regularManager, shinheuh, shinheuhManager);
        while (!fightEnded) {
            fightView.displayFightOptions(bufferedReader);
            if (fightView.getInputData().equals("1")) {
                fightManager.launchPhysicalAttack();
                fightView.writeToScreen(fightManager.getAttackRecord());
            }
            else if (fightView.getInputData().equals("2")) {
                fightManager.launchShinsooAttack();
                fightView.writeToScreen(fightManager.getAttackRecord());
            }
            else if (fightView.getInputData().equals("3")) {
                if (regularManager.run()) {
                    fightView.writeToScreen("You managed to run away." + System.lineSeparator());
                    regular.setX(originalX);
                    regular.setY(originalY);
                    fightEnded = true;
                    moveRegular(regular, map, bufferedReader);
                }
                else {
                    fightManager.endureWhenFailedToRun();
                    fightView.writeToScreen("You failed to run away. " + System.lineSeparator() + fightManager.getAttackRecord());
                }
            }
            if (regular.hasDied()) {
                fightView.writeRegularDeath();
                System.exit(0);
            }
            if (shinheuh.hasDied()) {
                fightView.writeShinheuhDeath();
                regular.setExperience(regular.getExperience() + shinheuh.getExpGiven());
                if (regularManager.hasLeveledUp()) {
                    regularManager.levelUp();
                    fightView.writeLeveledUp();
                }
                if (shinheuhManager.hasDroppedItem()) {
                    fightView.displayPickUpItemOptions(shinheuh, bufferedReader);
                    if (fightView.getInputData().equals("1")) {
                        if (shinheuh.getItemDrop().getType().equals("needle"))
                            regular.setWeapon((Weapon) shinheuh.getItemDrop());
                        else if (shinheuh.getItemDrop().getType().equals("helm"))
                            regular.setHelm((Helm) shinheuh.getItemDrop());
                        else if (shinheuh.getItemDrop().getType().equals("basic armor"))
                            regular.setArmor((Armor) shinheuh.getItemDrop());
                        else if (shinheuh.getItemDrop().getType().equals("ignition weapon"))
                            regular.setWeapon((Weapon) shinheuh.getItemDrop());
                    }
                }
                (map.getGrid())[originalY][originalX] = null;
                (map.getGrid())[regular.getY()][regular.getX()] = regular;
                fightEnded = true;
            }
        }
    }

}
