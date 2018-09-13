package com.gmail.vuyotm.swingy.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

@Getter

public class MoveRegularGuiView extends JFrame {

    private JLabel      heading;
    private JButton     northBtn;
    private JButton     southBtn;
    private JButton     eastBtn;
    private JButton     westBtn;
    private JTextField  feedbackTxtFld;
    private JButton     statsAndArtifactsBtn;

    public MoveRegularGuiView() throws HeadlessException {
        heading = new JLabel("Move Regular");
        northBtn = new JButton("North");
        southBtn = new JButton("South");
        eastBtn = new JButton("East");
        westBtn = new JButton("West");
        feedbackTxtFld = new JTextField();
        statsAndArtifactsBtn = new JButton("View stats and artifacts");
        heading.setBounds(220, 20, 150, 20);
        northBtn.setBounds(220, 50, 100, 20);
        southBtn.setBounds(220, 130, 100, 20);
        eastBtn.setBounds(320, 90, 100, 20);
        westBtn.setBounds(100, 90, 100, 20);
        feedbackTxtFld.setBounds(150, 170, 250, 20);
        statsAndArtifactsBtn.setBounds(150, 200, 200, 20);
        add(heading);
        add(northBtn);
        add(southBtn);
        add(eastBtn);
        add(westBtn);
        add(feedbackTxtFld);
        add(statsAndArtifactsBtn);
        setSize(500, 600);
        setLayout(null);
        setVisible(true);
    }

    public void addListenerAllMoveBtns(ActionListener actionListener) {
        northBtn.addActionListener(actionListener);
        southBtn.addActionListener(actionListener);
        eastBtn.addActionListener(actionListener);
        westBtn.addActionListener(actionListener);
        statsAndArtifactsBtn.addActionListener(actionListener);
    }

    public void addWindowCloseListener(WindowListener windowListener) {
        addWindowListener(windowListener);
    }

    public void setFeedbackTxt(String feedback) {
        feedbackTxtFld.setText(feedback);
    }

}
