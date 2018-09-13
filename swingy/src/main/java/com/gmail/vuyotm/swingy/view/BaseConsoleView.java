package com.gmail.vuyotm.swingy.view;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;

@Getter
@Setter

public abstract class BaseConsoleView {

    private String inputData;

    public void getUserInput(BufferedReader bufferedReader) throws IOException {
        inputData = bufferedReader.readLine();
    }

    public void writeToScreen(String str) {
        System.out.println(str);
    }

}
