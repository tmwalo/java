package com.gmail.vuyotm.swingy.view;

import com.gmail.vuyotm.swingy.model.characters.Regular;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;

@Getter
@Setter

public class CreateNewRegularView extends BaseConsoleView {

    private String              regularName;
    private String              regularPosition;
    public static final String  CREATE_REGULAR_TITLE = "Create a new Regular";
    public static final String  SCOUT_DESCR = "Scouts will usually run ahead and investigate enemy forces for info," +
                                                " then return and deliver the information to the Light Bearers. They also help the Fishermen with offence.";
    public static final String  LIGHT_BEARER_DESCR = "Light Bearers store and deliver knowledge to their team-mates while guiding them with the light of their Lighthouse.";
    public static final String  FISHERMAN_DESCR = "The main offence of the battle, Fishermen are to dash into the heart of the enemy force and decimate their forces," +
                                                    " usually through physical attacks.";
    public static final String  SPEAR_BEARER_DESCR = "Spear Bearers act like snipers for the team, throwing their spears with deadly accuracy," +
                                                        " but they can also serve a purpose similar to the Fisherman's by using their spear like a staff for close-combat." +
                                                        " Technically speaking, they take all long-range matters into their own hands.";
    public static final String  WAVE_CONTROLLER_DESCR = "Wave Controllers are the directors of the battle, aiding their comrades by healing their wounds," +
                                                        " annihilating enemy forces or subtly changing the tide of the battle, all through the use of Shinsoo." +
                                                        " There are many variations of this Position.";
    public static final String  SELECT_REGULAR_MSG = "Press 1 to select a " + Regular.SCOUT + System.lineSeparator()
                                                    + "Press 2 to select a " + Regular.LIGHT_BEARER + System.lineSeparator()
                                                    + "Press 3 to select a " + Regular.FISHERMAN + System.lineSeparator()
                                                    + "Press 4 to select a " + Regular.SPEAR_BEARER + System.lineSeparator()
                                                    + "Press 5 to select a " + Regular.WAVE_CONTROLLER + System.lineSeparator();
    public static final String  CHOOSE_NAME_MSG = "Please specify your name as a Regular:";

    public void writeNewRegularMsg() {
        String newRegularMsg;

        newRegularMsg = "";
        newRegularMsg += System.lineSeparator();
        newRegularMsg += CREATE_REGULAR_TITLE;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += System.lineSeparator();
        newRegularMsg += Regular.SCOUT;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += SCOUT_DESCR;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += System.lineSeparator();
        newRegularMsg += Regular.LIGHT_BEARER;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += LIGHT_BEARER_DESCR;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += System.lineSeparator();
        newRegularMsg += Regular.FISHERMAN;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += FISHERMAN_DESCR;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += System.lineSeparator();
        newRegularMsg += Regular.SPEAR_BEARER;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += SPEAR_BEARER_DESCR;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += System.lineSeparator();
        newRegularMsg += Regular.WAVE_CONTROLLER;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += WAVE_CONTROLLER_DESCR;
        newRegularMsg += System.lineSeparator();
        newRegularMsg += System.lineSeparator();
        newRegularMsg += SELECT_REGULAR_MSG;
        writeToScreen(newRegularMsg);
    }

    public void writeChooseNameMsg() {
        String chooseNameMsg;

        chooseNameMsg = "";
        chooseNameMsg += System.lineSeparator();
        chooseNameMsg += CREATE_REGULAR_TITLE;
        chooseNameMsg += System.lineSeparator();
        chooseNameMsg += System.lineSeparator();
        chooseNameMsg += CHOOSE_NAME_MSG;
        chooseNameMsg += System.lineSeparator();
        writeToScreen(chooseNameMsg);
    }

    public void writeCreationSuccessMsg(String name, String position) {
        String creationSuccessMsg;

        if (name == null)
            throw new IllegalArgumentException("Name can not be null.");
        if (position == null)
            throw new IllegalArgumentException("Position can not be null.");
        if (name.equals(""))
            throw new IllegalArgumentException("Name can not be empty.");
        if (position.equals(""))
            throw new IllegalArgumentException("Position can not be empty.");
        creationSuccessMsg = "";
        creationSuccessMsg += System.lineSeparator();
        creationSuccessMsg += name;
        creationSuccessMsg += ", beginning as a ";
        creationSuccessMsg += position;
        creationSuccessMsg += ", is ready to challenge the Tower.";
        writeToScreen(creationSuccessMsg);
    }

    public void displayRegularCreation(BufferedReader bufferedReader) throws IOException {
        boolean selectRegularPosition;

        selectRegularPosition = false;
        setInputData(null);
        while ((getInputData() == null) || getInputData().equals("")) {
            writeChooseNameMsg();
            getUserInput(bufferedReader);
            if (getInputData().equals("q"))
                System.exit(0);
            if (!getInputData().equals(""))
                setRegularName(getInputData());
        }
        while (!selectRegularPosition) {
            writeNewRegularMsg();
            getUserInput(bufferedReader);
            if (getInputData().equals("1")) {
                setRegularPosition(Regular.SCOUT);
                selectRegularPosition = true;
            }
            else if (getInputData().equals("2")) {
                setRegularPosition(Regular.LIGHT_BEARER);
                selectRegularPosition = true;
            }
            else if (getInputData().equals("3")) {
                setRegularPosition(Regular.FISHERMAN);
                selectRegularPosition = true;
            }
            else if (getInputData().equals("4")) {
                setRegularPosition(Regular.SPEAR_BEARER);
                selectRegularPosition = true;
            }
            else if (getInputData().equals("5")) {
                setRegularPosition(Regular.WAVE_CONTROLLER);
                selectRegularPosition = true;
            }
            else if (getInputData().equals("q"))
                System.exit(0);
            else
                writeToScreen(System.lineSeparator() + "Invalid input." + System.lineSeparator());
        }
        writeCreationSuccessMsg(getRegularName(), getRegularPosition());
    }

}
