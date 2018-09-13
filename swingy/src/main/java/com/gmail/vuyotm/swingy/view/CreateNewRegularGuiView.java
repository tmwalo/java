package com.gmail.vuyotm.swingy.view;

import com.gmail.vuyotm.swingy.model.characters.Regular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateNewRegularGuiView extends JFrame {

    private JLabel                  headingLbl;
    private JLabel                  regularNameLbl;
    private JTextField              regularNameTxtFld;
    private JLabel                  choosePosLbl;
    private JRadioButton            scoutRbtn;
    private JRadioButton            lightBearerRbtn;
    private JRadioButton            fishermanRbtn;
    private JRadioButton            spearBearerRbtn;
    private JRadioButton            waveControllerRbtn;
    private ButtonGroup             buttonGroup;
    private JTextArea               scoutTxtArea;
    private JTextArea               lightBearerTxtArea;
    private JTextArea               fishermanTxtArea;
    private JTextArea               spearBearerTxtArea;
    private JTextArea               waveControllerTxtArea;
    private JButton                 createRegularBtn;
    private ArrayList<JRadioButton> regularPositionRBtns;
    private static final String     SCOUT_DESCR = "Scouts will usually run ahead and investigate enemy forces for info," +
                                                    " then return and deliver the information to the Light Bearers. They also help the Fishermen with offence.";
    private static final String     LIGHT_BEARER_DESCR = "Light Bearers store and deliver knowledge to their team-mates while guiding them with the light of their Lighthouse.";
    private static final String     FISHERMAN_DESCR = "The main offence of the battle, Fishermen are to dash into the heart of the enemy force and decimate their forces," +
                                                        " usually through physical attacks.";
    private static final String     SPEAR_BEARER_DESCR = "Spear Bearers act like snipers for the team, throwing their spears with deadly accuracy," +
                                                            " but they can also serve a purpose similar to the Fisherman's by using their spear like a staff for close-combat." +
                                                            " Technically speaking, they take all long-range matters into their own hands.";
    private static final String     WAVE_CONTROLLER_DESCR = "Wave Controllers are the directors of the battle, aiding their comrades by healing their wounds," +
                                                            " annihilating enemy forces or subtly changing the tide of the battle, all through the use of Shinsoo." +
                                                            " There are many variations of this Position.";

    public CreateNewRegularGuiView() throws HeadlessException {
        headingLbl = new JLabel("Create Regular");
        regularNameLbl = new JLabel("Regular Name:");
        regularNameTxtFld = new JTextField();
        choosePosLbl = new JLabel("Choose Regular Position:");
        scoutRbtn = new JRadioButton("Scout");
        lightBearerRbtn = new JRadioButton("Light Bearer");
        fishermanRbtn = new JRadioButton("Fisherman");
        spearBearerRbtn = new JRadioButton("Spear Bearer");
        waveControllerRbtn = new JRadioButton("Wave Controller");
        scoutTxtArea = new JTextArea(SCOUT_DESCR);
        lightBearerTxtArea = new JTextArea(LIGHT_BEARER_DESCR);
        fishermanTxtArea = new JTextArea(FISHERMAN_DESCR);
        spearBearerTxtArea = new JTextArea(SPEAR_BEARER_DESCR);
        waveControllerTxtArea = new JTextArea(WAVE_CONTROLLER_DESCR);
        scoutRbtn.setSelected(true);
        scoutTxtArea.setEditable(false);
        lightBearerTxtArea.setEditable(false);
        fishermanTxtArea.setEditable(false);
        spearBearerTxtArea.setEditable(false);
        waveControllerTxtArea.setEditable(false);
        regularPositionRBtns = new ArrayList<>();
        regularPositionRBtns.add(scoutRbtn);
        regularPositionRBtns.add(lightBearerRbtn);
        regularPositionRBtns.add(fishermanRbtn);
        regularPositionRBtns.add(spearBearerRbtn);
        regularPositionRBtns.add(waveControllerRbtn);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(scoutRbtn);
        buttonGroup.add(lightBearerRbtn);
        buttonGroup.add(fishermanRbtn);
        buttonGroup.add(spearBearerRbtn);
        buttonGroup.add(waveControllerRbtn);
        createRegularBtn = new JButton("Create Regular");
        headingLbl.setBounds(30, 5, 200, 20);
        regularNameLbl.setBounds(30, 45, 100, 20);
        regularNameTxtFld.setBounds(160, 45, 150, 20);
        choosePosLbl.setBounds(30, 75, 200, 20);
        scoutRbtn.setBounds(30, 105, 150, 20);
        scoutTxtArea.setBounds(30, 130, 440, 50);
        scoutTxtArea.setLineWrap(true);
        lightBearerRbtn.setBounds(30, 185, 300, 20);
        lightBearerTxtArea.setBounds(30, 210, 440, 40);
        lightBearerTxtArea.setLineWrap(true);
        fishermanRbtn.setBounds(30, 255, 300, 20);
        fishermanTxtArea.setBounds(30, 280, 440, 50);
        fishermanTxtArea.setLineWrap(true);
        spearBearerRbtn.setBounds(30, 335, 300, 20);
        spearBearerTxtArea.setBounds(30, 360, 440, 70);
        spearBearerTxtArea.setLineWrap(true);
        waveControllerRbtn.setBounds(30, 435, 300, 20);
        waveControllerTxtArea.setBounds(30, 460, 440, 70);
        waveControllerTxtArea.setLineWrap(true);
        createRegularBtn.setBounds(30, 550, 200, 20);
        add(headingLbl);
        add(regularNameLbl);
        add(regularNameTxtFld);
        add(choosePosLbl);
        add(scoutRbtn);
        add(lightBearerRbtn);
        add(fishermanRbtn);
        add(spearBearerRbtn);
        add(waveControllerRbtn);
        add(scoutTxtArea);
        add(lightBearerTxtArea);
        add(fishermanTxtArea);
        add(spearBearerTxtArea);
        add(waveControllerTxtArea);
        add(createRegularBtn);
        setSize(500, 600);
        setLayout(null);
        setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addListenerCreateRegularBtn(ActionListener actionListener) {
        createRegularBtn.addActionListener(actionListener);
    }

    public String getRegularName() {
        return (regularNameTxtFld.getText());
    }

    public String getRegularPosition() {
        if (scoutRbtn.isSelected())
            return (Regular.SCOUT);
        else if (lightBearerRbtn.isSelected())
            return (Regular.LIGHT_BEARER);
        else if (fishermanRbtn.isSelected())
            return (Regular.FISHERMAN);
        else if (spearBearerRbtn.isSelected())
            return (Regular.SPEAR_BEARER);
        else if (waveControllerRbtn.isSelected())
            return (Regular.WAVE_CONTROLLER);
        else
            return ("");
    }

    public void displayErrorMsg(String errorMsg) {
        JOptionPane.showMessageDialog(this, errorMsg);
    }

}
