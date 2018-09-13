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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.awt.event.*;
import java.util.ArrayList;

public class GameGuiController {

    private boolean isNewRegular;

    class StartGameEventHandler implements ActionListener {

        @NotNull
        private StartGameGuiView startGameGuiView;

        public StartGameEventHandler(StartGameGuiView startGameGuiView) {
            this.startGameGuiView = startGameGuiView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startGameGuiView.getCreateRegularBtn()) {
                startGameGuiView.dispose();
                createRegular();
            }
            else if (e.getSource() == startGameGuiView.getLoadRegularBtn()) {
                startGameGuiView.dispose();
                loadRegular();
            }
        }

    }

    class CreateRegularEventHandler implements ActionListener {

        @NotNull
        private CreateNewRegularGuiView createNewRegularGuiView;

        public CreateRegularEventHandler(CreateNewRegularGuiView createNewRegularGuiView) {
            this.createNewRegularGuiView = createNewRegularGuiView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String          regularName;
            String          regularPosition;
            Regular         regular;
            Map             map;

            regularName = createNewRegularGuiView.getRegularName();
            regularPosition = createNewRegularGuiView.getRegularPosition();
            if (regularName.equals("")) {
                createNewRegularGuiView.displayErrorMsg("Please enter your name.");
                return ;
            }
            if (regularPosition.equals("")) {
                createNewRegularGuiView.displayErrorMsg("Please select a regular position.");
                return ;
            }
            regular = RegularFactory.newRegular(regularName, regularPosition);
            map = new Map(regular);
            isNewRegular = true;
            createNewRegularGuiView.dispose();
            moveRegular(regular, map);
        }

    }

    class LoadRegularEventHandler implements ActionListener {

        @NotNull
        private LoadRegularGuiView  loadRegularGuiView;

        public LoadRegularEventHandler(LoadRegularGuiView loadRegularGuiView) {
            this.loadRegularGuiView = loadRegularGuiView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int             regularId;
            RegularContext  regularContext;
            Regular         regular;
            Map             map;

            regularId = Integer.parseInt(e.getActionCommand());
            regularContext = new RegularContext(Database.CONNECTION_STRING);
            regularContext.openConnection();
            regular = regularContext.getRegular(regularId);
            regularContext.close();
            map = new Map(regular);
            isNewRegular = false;
            loadRegularGuiView.dispose();
            moveRegular(regular, map);
        }

    }

    class MoveRegularEventHandler implements ActionListener, WindowListener {

        @NotNull
        private MoveRegularGuiView  moveRegularGuiView;
        @NotNull
        private Regular             regular;
        @NotNull
        private Map                 map;
        private RegularManager      regularManager;
        private MapManager          mapManager;

        public MoveRegularEventHandler(MoveRegularGuiView moveRegularGuiView, Regular regular, Map map) {
            this.moveRegularGuiView = moveRegularGuiView;
            this.regular = regular;
            this.map = map;
            regularManager = new RegularManager(regular);
            mapManager = new MapManager(map);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int                 originalX;
            int                 originalY;
            Shinheuh            encounteredShinheuh;

            originalX = regular.getX();
            originalY = regular.getY();
            if (e.getSource() == moveRegularGuiView.getNorthBtn()) {
                moveRegularGuiView.setFeedbackTxt("You moved north.");
                regularManager.moveNorth();
            }
            else if (e.getSource() == moveRegularGuiView.getSouthBtn()) {
                moveRegularGuiView.setFeedbackTxt("You moved south.");
                regularManager.moveSouth();
            }
            else if (e.getSource() == moveRegularGuiView.getEastBtn()) {
                moveRegularGuiView.setFeedbackTxt("You moved east.");
                regularManager.moveEast();
            }
            else if (e.getSource() == moveRegularGuiView.getWestBtn()) {
                moveRegularGuiView.setFeedbackTxt("You moved west.");
                regularManager.moveWest();
            }
            else if (e.getSource() == moveRegularGuiView.getStatsAndArtifactsBtn()) {
                moveRegularGuiView.dispose();
                statsAndArtifacts(regular, map);
                return ;
            }
            if (mapManager.hasCrossedMapBorder(regular)) {
                map = new Map(regular);
                mapManager = new MapManager(map);
                moveRegularGuiView.setFeedbackTxt("You have entered a new area.");
                return ;
            }
            else if (mapManager.hasEncounteredShinheuh(regular)) {
                moveRegularGuiView.dispose();
                encounteredShinheuh = (Shinheuh) (map.getGrid())[regular.getY()][regular.getX()];
                fight(regular, encounteredShinheuh, map, originalX, originalY);
                return ;
            }
            (map.getGrid())[originalY][originalX] = null;
            (map.getGrid())[regular.getY()][regular.getX()] = regular;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            RegularContext  regularContext;

            regularContext = new RegularContext(Database.CONNECTION_STRING);
            regularContext.openConnection();
            if (isNewRegular)
                regularContext.postRegular(regular);
            else
                regularContext.putRegular(regular);
            regularContext.close();
            System.exit(0);
        }

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}

    }

    class FightEventHandler implements ActionListener {

        @NotNull
        private FightGuiView        fightGuiView;
        @NotNull
        private Regular             regular;
        @NotNull
        private Shinheuh            encounteredShinheueh;
        @NotNull
        private Map                 map;
        @Min(0)
        private int                 originalX;
        @Min(0)
        private int                 originalY;
        private RegularManager      regularManager;
        private ShinheuhManager     shinheuhManager;
        private FightManager        fightManager;

        public FightEventHandler(FightGuiView fightGuiView, Regular regular, Shinheuh encounteredShinheueh, Map map, int originalX, int originalY) {
            this.fightGuiView = fightGuiView;
            this.regular = regular;
            this.encounteredShinheueh = encounteredShinheueh;
            this.map = map;
            this.originalX = originalX;
            this.originalY = originalY;
            regularManager = new RegularManager(regular);
            shinheuhManager = new ShinheuhManager(encounteredShinheueh);
            fightManager = new FightManager(regular, regularManager, encounteredShinheueh, shinheuhManager);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == fightGuiView.getPhysicalAttckBtn()) {
                fightManager.launchPhysicalAttack();
                fightGuiView.setFeedbackTxt(fightManager.getAttackRecord());
            }
            else if (e.getSource() == fightGuiView.getShinsooAttckBtn()) {
                fightManager.launchShinsooAttack();
                fightGuiView.setFeedbackTxt(fightManager.getAttackRecord());
            }
            else if (e.getSource() == fightGuiView.getRunBtn()) {
                if (regularManager.run()) {
                    regular.setX(originalX);
                    regular.setY(originalY);
                    fightGuiView.dispose();
                    moveRegular(regular, map);
                    return ;
                }
                else {
                    fightManager.endureWhenFailedToRun();
                    fightGuiView.setFeedbackTxt("You failed to run away. " + System.lineSeparator() + fightManager.getAttackRecord());
                }
            }
            else if (e.getSource() == fightGuiView.getPickUpItemBtn()) {
                if (encounteredShinheueh.getItemDrop().getType().equals("needle"))
                    regular.setWeapon((Weapon) encounteredShinheueh.getItemDrop());
                else if (encounteredShinheueh.getItemDrop().getType().equals("helm"))
                    regular.setHelm((Helm) encounteredShinheueh.getItemDrop());
                else if (encounteredShinheueh.getItemDrop().getType().equals("basic armor"))
                    regular.setArmor((Armor) encounteredShinheueh.getItemDrop());
                else if (encounteredShinheueh.getItemDrop().getType().equals("ignition weapon"))
                    regular.setWeapon((Weapon) encounteredShinheueh.getItemDrop());
                fightGuiView.dispose();
                moveRegular(regular, map);
            }
            else if (e.getSource() == fightGuiView.getLeaveItemBtn()) {
                fightGuiView.dispose();
                moveRegular(regular, map);
            }
            if (regular.hasDied()) {
                fightGuiView.setFeedbackTxt("You died. Game over.");
                System.exit(0);
            }
            if (encounteredShinheueh.hasDied()) {
                fightGuiView.setFeedbackTxt("You were victorious.");
                (map.getGrid())[originalY][originalX] = null;
                (map.getGrid())[regular.getY()][regular.getX()] = regular;
                regular.setExperience(regular.getExperience() + encounteredShinheueh.getExpGiven());
                if (regularManager.hasLeveledUp()) {
                    regularManager.levelUp();
                    fightGuiView.setFeedbackTxt("You have leveled up.");
                }
                if (shinheuhManager.hasDroppedItem()) {
                    fightGuiView.hideBattleBtns();
                    fightGuiView.showItemBtns();
                    fightGuiView.setFeedbackTxt("An item was dropped.");
                }
                else {
                    fightGuiView.dispose();
                    moveRegular(regular, map);
                }
            }
        }

    }

    class StatsAndArtifactsEventHandler implements ActionListener {

        @NotNull
        private StatsAndArtifactsGuiView    statsAndArtifactsGuiView;
        @NotNull
        private Regular                     regular;
        @NotNull
        private Map                         map;

        public StatsAndArtifactsEventHandler(StatsAndArtifactsGuiView statsAndArtifactsGuiView, Regular regular, Map map) {
            this.statsAndArtifactsGuiView = statsAndArtifactsGuiView;
            this.regular = regular;
            this.map = map;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            statsAndArtifactsGuiView.dispose();
            moveRegular(regular, map);
        }

    }

    public void startGame() {
        StartGameGuiView        startGameGuiView;
        StartGameEventHandler   startGameEventHandler;

        startGameGuiView = new StartGameGuiView();
        startGameEventHandler = new StartGameEventHandler(startGameGuiView);
        startGameGuiView.addListenerCreateRegularBtn(startGameEventHandler);
        startGameGuiView.addListenerLoadRegularBtn(startGameEventHandler);
    }

    public void createRegular() {
        CreateNewRegularGuiView     createNewRegularGuiView;
        CreateRegularEventHandler   createRegularEventHandler;

        createNewRegularGuiView = new CreateNewRegularGuiView();
        createRegularEventHandler = new CreateRegularEventHandler(createNewRegularGuiView);
        createNewRegularGuiView.addListenerCreateRegularBtn(createRegularEventHandler);
    }

    public void loadRegular() {
        ArrayList<Regular>          regulars;
        RegularContext              regularContext;
        LoadRegularGuiView          loadRegularGuiView;
        LoadRegularEventHandler     loadRegularEventHandler;

        regularContext = new RegularContext(Database.CONNECTION_STRING);
        regularContext.openConnection();
        regulars = regularContext.getAllRegulars();
        regularContext.close();
        loadRegularGuiView = new LoadRegularGuiView(regulars);
        loadRegularEventHandler = new LoadRegularEventHandler(loadRegularGuiView);
        loadRegularGuiView.addListenerAllBtns(loadRegularEventHandler);
    }

    public void moveRegular(Regular regular, Map map) {
        MoveRegularGuiView          moveRegularGuiView;
        MoveRegularEventHandler     moveRegularEventHandler;

        moveRegularGuiView = new MoveRegularGuiView();
        moveRegularEventHandler = new MoveRegularEventHandler(moveRegularGuiView, regular, map);
        moveRegularGuiView.addListenerAllMoveBtns(moveRegularEventHandler);
        moveRegularGuiView.addWindowCloseListener(moveRegularEventHandler);
    }

    public void fight(Regular regular, Shinheuh encounteredShinheuh, Map map, int originalX, int originalY) {
        FightGuiView        fightGuiView;
        FightEventHandler   fightEventHandler;

        fightGuiView = new FightGuiView();
        fightEventHandler = new FightEventHandler(fightGuiView, regular, encounteredShinheuh, map, originalX, originalY);
        fightGuiView.addListenerAllBtns(fightEventHandler);
    }

    public void statsAndArtifacts(Regular regular, Map map) {
        StatsAndArtifactsGuiView        statsAndArtifactsGuiView;
        StatsAndArtifactsEventHandler   statsAndArtifactsEventHandler;

        statsAndArtifactsGuiView = new StatsAndArtifactsGuiView(regular);
        statsAndArtifactsEventHandler = new StatsAndArtifactsEventHandler(statsAndArtifactsGuiView, regular, map);
        statsAndArtifactsGuiView.addListenerBackBtn(statsAndArtifactsEventHandler);
    }

}
