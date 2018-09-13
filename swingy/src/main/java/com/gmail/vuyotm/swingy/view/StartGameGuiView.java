package com.gmail.vuyotm.swingy.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter

public class StartGameGuiView extends JFrame {

    private JLabel                  heading;
    private JTextArea               introTxtArea;
    private JButton                 createRegularBtn;
    private JButton                 loadRegularBtn;
    private static final String     GAME_TITLE = "Tower of God: The original Regulars.";
    private static final String     START_GAME_MSG = "What do you desire?" + System.lineSeparator()
                                                    + "Money and wealth?" + System.lineSeparator()
                                                    + "Honor and pride?" + System.lineSeparator()
                                                    + "Authority and power?" + System.lineSeparator()
                                                    + "Revenge?" + System.lineSeparator()
                                                    + "Or something that transcends them all?" + System.lineSeparator()
                                                    + "Whatever you desire — it's here.";

    public StartGameGuiView() throws HeadlessException {
        heading = new JLabel(GAME_TITLE);
        introTxtArea = new JTextArea(START_GAME_MSG);
        createRegularBtn = new JButton("Create Regular");
        loadRegularBtn = new JButton("Load Regular");
        heading.setBounds(100, 30, 300, 25);
        introTxtArea.setBounds(100, 80, 250, 120);
        createRegularBtn.setBounds(160, 230, 120, 25);
        loadRegularBtn.setBounds(160, 265, 120, 25);
        introTxtArea.setEditable(false);
        add(heading);
        add(introTxtArea);
        add(createRegularBtn);
        add(loadRegularBtn);
        setSize(500, 600);
        setLayout(null);
        setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addListenerCreateRegularBtn(ActionListener actionListener) {
        createRegularBtn.addActionListener(actionListener);
    }

    public void addListenerLoadRegularBtn(ActionListener actionListener) {
        loadRegularBtn.addActionListener(actionListener);
    }

}
