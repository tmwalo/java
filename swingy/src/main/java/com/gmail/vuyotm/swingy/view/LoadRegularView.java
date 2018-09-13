package com.gmail.vuyotm.swingy.view;

import com.gmail.vuyotm.swingy.model.characters.Regular;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@Getter

public class LoadRegularView extends BaseConsoleView {

    private int selectedRegularID;

    public void displayRegulars(ArrayList<Regular> regulars, BufferedReader bufferedReader) throws IOException {
        int     index;
        int     regularNum;
        boolean selectRegular;
        int     selectedRegular;
        int     selectedRegularIndex;

        selectRegular = false;
        index = 0;
        regularNum = index + 1;
        selectedRegular = -1;
        writeToScreen("Select a Regular:" + System.lineSeparator() + System.lineSeparator());
        if ((regulars == null) || regulars.isEmpty()) {
            writeToScreen("There are no stored regulars." + System.lineSeparator());
            return ;
        }
        while (index < regulars.size()) {
            writeToScreen(regularNum + ". " + (regulars.get(index)).getName() + " - " + (regulars.get(index)).getClassType() + " level " + (regulars.get(index)).getLevel() + System.lineSeparator());
            ++index;
            ++regularNum;
        }
        while (!selectRegular) {
            writeToScreen("Select a regular by entering their number:" + System.lineSeparator() + System.lineSeparator());
            getUserInput(bufferedReader);
            try {
                selectedRegular = Integer.parseInt(getInputData());
                selectedRegularIndex = selectedRegular - 1;
            }
            catch (NumberFormatException e) {
                writeToScreen("Please enter a number." + System.lineSeparator());
                continue ;
            }
            if ((selectedRegular > 0) && (selectedRegular <= regulars.size())) {
                selectedRegularID = regulars.get(selectedRegularIndex).getId();
                selectRegular = true;
            }
            else
                writeToScreen("Invalid input." + System.lineSeparator());
        }

    }

}
