package com.gmail.vuyotm.swingy.view;

import com.gmail.vuyotm.swingy.model.characters.Shinheuh;

import java.io.BufferedReader;
import java.io.IOException;

public class FightView extends BaseConsoleView {

    public static final String FIGHT_OPTIONS = "Press 1 to launch a physical attack." + System.lineSeparator()
                                                + "Press 2 to launch a shinsoo attack." + System.lineSeparator()
                                                + "Press 3 to run away." + System.lineSeparator();

    public static final String PICK_UP_ITEM_OPTIONS = "Press 1 to pick up artifact." + System.lineSeparator()
                                                        + "Press 2 to leave artifact.";

    public void writeFightOptions() {
        writeToScreen(System.lineSeparator() + FIGHT_OPTIONS);
    }

    public void writePickUpItemOptions() {
        writeToScreen(System.lineSeparator() + PICK_UP_ITEM_OPTIONS);
    }

    public void writeLeveledUp() {
        writeToScreen(System.lineSeparator() + "You have leveled up." + System.lineSeparator());
    }

    public void writeRegularDeath() {
        writeToScreen(System.lineSeparator() + "You died. Game over." + System.lineSeparator());
    }

    public void writeShinheuhDeath() {
        writeToScreen(System.lineSeparator() + "You were victorious." + System.lineSeparator());
    }

    public void displayFightOptions (BufferedReader bufferedReader) throws IOException {
        boolean selectFightOption;

        selectFightOption = false;
        while (!selectFightOption) {
            writeFightOptions();
            getUserInput(bufferedReader);
            if (getInputData().equals("1"))
                selectFightOption = true;
            else if (getInputData().equals("2"))
                selectFightOption = true;
            else if (getInputData().equals("3"))
                selectFightOption = true;
            else
                writeToScreen(System.lineSeparator() + "Invalid input." + System.lineSeparator());
        }
    }

    public void displayPickUpItemOptions(Shinheuh shinheuh, BufferedReader bufferedReader) throws IOException {
        boolean decideOnItemPickUp;

        decideOnItemPickUp = false;
        writeToScreen(System.lineSeparator() + shinheuh.getType() + " dropped a " + shinheuh.getItemDrop().getType() + System.lineSeparator());
        while (!decideOnItemPickUp) {
            writePickUpItemOptions();
            getUserInput(bufferedReader);
            if (getInputData().equals("1"))
                decideOnItemPickUp = true;
            else if (getInputData().equals("2"))
                decideOnItemPickUp = true;
            else
                writeToScreen(System.lineSeparator() + "Invalid input." + System.lineSeparator());
        }
    }

}
