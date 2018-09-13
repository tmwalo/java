package com.gmail.vuyotm.swingy;

import com.gmail.vuyotm.swingy.controller.GameController;
import com.gmail.vuyotm.swingy.controller.GameGuiController;
import com.gmail.vuyotm.swingy.storage.Database;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("One command line argument expected.");
            System.exit(0);
        }

        if (!(args[0]).equals("console") && !(args[0]).equals("gui")) {
            System.out.println("Command line argument console or gui expected.");
            System.exit(0);
        }

        if ((args[0]).equals("console")) {

            GameController  gameController;

            Database.createDB();
            gameController = new GameController();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                gameController.startGame(bufferedReader);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }
        else if ((args[0]).equals("gui")) {

            GameGuiController   gameGuiController;

            Database.createDB();
            gameGuiController = new GameGuiController();
            try {
                gameGuiController.startGame();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (HeadlessException e) {
                e.printStackTrace();
            }

        }

    }

}
