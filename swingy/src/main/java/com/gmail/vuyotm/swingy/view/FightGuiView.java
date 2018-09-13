package com.gmail.vuyotm.swingy.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@Getter

public class FightGuiView extends JFrame {

    private JLabel      heading;
    private JTextArea   feedbackTxtArea;
    private JButton     physicalAttckBtn;
    private JButton     shinsooAttckBtn;
    private JButton     runBtn;
    private JButton     pickUpItemBtn;
    private JButton     leaveItemBtn;

    public FightGuiView() throws HeadlessException {
        heading = new JLabel("Fight");
        feedbackTxtArea = new JTextArea();
        physicalAttckBtn = new JButton("Physical Attack");
        shinsooAttckBtn = new JButton("Shinsoo Attack");
        runBtn = new JButton("Run");
        pickUpItemBtn = new JButton("Pick Up Item");
        leaveItemBtn = new JButton("Leave Item");
        heading.setBounds(220, 20, 150, 20);
        feedbackTxtArea.setBounds(50, 50, 400, 65);
        physicalAttckBtn.setBounds(125, 120, 150, 20);
        shinsooAttckBtn.setBounds(300, 120, 150, 20);
        runBtn.setBounds(125, 150, 150, 20);
        pickUpItemBtn.setBounds(125, 120, 150, 20);
        leaveItemBtn.setBounds(300, 120, 150, 20);
        pickUpItemBtn.setVisible(false);
        leaveItemBtn.setVisible(false);
        feedbackTxtArea.setEditable(false);
        add(heading);
        add(feedbackTxtArea);
        add(physicalAttckBtn);
        add(shinsooAttckBtn);
        add(runBtn);
        add(pickUpItemBtn);
        add(leaveItemBtn);
        setSize(500, 600);
        setLayout(null);
        setVisible(true);
    }

    public void hideBattleBtns() {
        physicalAttckBtn.setVisible(false);
        shinsooAttckBtn.setVisible(false);
        runBtn.setVisible(false);
    }

    public void showItemBtns() {
        pickUpItemBtn.setVisible(true);
        leaveItemBtn.setVisible(true);
    }

    public void setFeedbackTxt(String feedback) {
        feedbackTxtArea.setText(feedback);
    }

    public void addListenerAllBtns(ActionListener actionListener) {
        physicalAttckBtn.addActionListener(actionListener);
        shinsooAttckBtn.addActionListener(actionListener);
        runBtn.addActionListener(actionListener);
        pickUpItemBtn.addActionListener(actionListener);
        leaveItemBtn.addActionListener(actionListener);
    }

}
