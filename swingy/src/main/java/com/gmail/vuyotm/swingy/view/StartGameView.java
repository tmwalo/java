package com.gmail.vuyotm.swingy.view;

import java.io.BufferedReader;
import java.io.IOException;

public class StartGameView extends BaseConsoleView {

    public static final String GAME_TITLE = "Tower of God: The original Regulars.";
    public static final String START_GAME_MSG = "What do you desire?" + System.lineSeparator()
                                                + "Money and wealth?" + System.lineSeparator()
                                                + "Honor and pride?" + System.lineSeparator()
                                                + "Authority and power?" + System.lineSeparator()
                                                + "Revenge?" + System.lineSeparator()
                                                + "Or something that transcends them all?" + System.lineSeparator()
                                                + "Whatever you desire — it's here.";
    public static final String LOAD_REGULAR = "1";
    public static final String CREATE_NEW_REGULAR = "2";
    public static final String CREATE_OR_LOAD_REGULAR_MSG = "Press 1 to load a Regular." + System.lineSeparator()
                                                            + "Press 2 to create a new Regular." + System.lineSeparator()
                                                            + "Press q to quit.";

    public void writeStartScreenMsg() {
        String startScreenMsg;

        startScreenMsg = "";
        startScreenMsg += System.lineSeparator();
        startScreenMsg += GAME_TITLE;
        startScreenMsg += System.lineSeparator();
        startScreenMsg += System.lineSeparator();
        startScreenMsg += START_GAME_MSG;
        startScreenMsg += System.lineSeparator();
        startScreenMsg += System.lineSeparator();
        startScreenMsg += CREATE_OR_LOAD_REGULAR_MSG;
        startScreenMsg += System.lineSeparator();
        writeToScreen(startScreenMsg);
    }

    public void displayStartScreen(BufferedReader bufferedReader) throws IOException {
        boolean startGame;

        startGame = false;
        while (!startGame) {
            writeStartScreenMsg();
            getUserInput(bufferedReader);
            if (getInputData().equals("1"))
                startGame = true;
            else if (getInputData().equals("2"))
                startGame = true;
            else if (getInputData().equals("q"))
                System.exit(0);
            else
                writeToScreen(System.lineSeparator() + "Invalid input." + System.lineSeparator());
        }
    }

}