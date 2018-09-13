package com.gmail.vuyotm.swingy.view;

import com.gmail.vuyotm.swingy.model.characters.Regular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatsAndArtifactsGuiView extends JFrame {

    private JLabel      statsAndEquipmentHeading;
    private JLabel      statsHeading;
    private JTextArea   stats;
    private JLabel      equipmentHeading;
    private JTextArea   equipment;
    private JButton     backBtn;
    private String      statsStr;
    private String      equipmentStr;

    public StatsAndArtifactsGuiView(Regular regular) throws HeadlessException {
        statsAndEquipmentHeading = new JLabel("Stats and Artifacts");
        statsHeading = new JLabel("Stats:");
        stats = new JTextArea();
        equipmentHeading = new JLabel("Artifacts:");
        equipment = new JTextArea();
        backBtn = new JButton("Back");
        stats.setEditable(false);
        equipment.setEditable(false);
        statsAndEquipmentHeading.setBounds(220, 20, 200, 20);
        statsHeading.setBounds(100, 50, 100, 20);
        stats.setBounds(100, 80, 200, 200);
        equipmentHeading.setBounds(100, 300, 100, 20);
        equipment.setBounds(100, 330, 200, 100);
        backBtn.setBounds(100, 450, 100, 20);
        setStatsStr(regular);
        setEquipmentStr(regular);
        stats.setText(statsStr);
        equipment.setText(equipmentStr);
        add(statsAndEquipmentHeading);
        add(statsHeading);
        add(stats);
        add(equipmentHeading);
        add(equipment);
        add(backBtn);
        setSize(500, 600);
        setLayout(null);
        setVisible(true);
    }

    public void setStatsStr(Regular regular) {
        statsStr = "";
        statsStr += "Name: " + regular.getName() + System.lineSeparator();
        statsStr += "Level: " + regular.getLevel() + System.lineSeparator();
        statsStr += "Position: " + regular.getClassType() + System.lineSeparator();
        statsStr += "Hit Points: " + regular.getHitPts() + System.lineSeparator();
        statsStr += "Physical Attack: " + regular.getPhysicalAttack() + System.lineSeparator();
        statsStr += "Shinsoo Attack: " + regular.getShinsooAttack() + System.lineSeparator();
        statsStr += "Physical Defense: " + regular.getPhysicalDefense() + System.lineSeparator();
        statsStr += "Shinsoo Defense: " + regular.getShinsooDefense() + System.lineSeparator();
        statsStr += "Speed: " + regular.getSpeed() + System.lineSeparator();
        statsStr += "Experience: " + regular.getExperience() + System.lineSeparator();
    }

    public void setEquipmentStr(Regular regular) {
        String  helm;
        String  armor;
        String  weapon;

        equipmentStr = "";
        if (regular.getHelm() != null)
            helm = regular.getHelm().getType();
        else
            helm = "None";
        if (regular.getArmor() != null)
            armor = regular.getArmor().getType();
        else
            armor = "None";
        if (regular.getWeapon() != null)
            weapon = regular.getWeapon().getType();
        else
            weapon = "None";
        equipmentStr += "Helm: " + helm + System.lineSeparator();
        equipmentStr += "Armor: " + armor + System.lineSeparator();
        equipmentStr += "Weapon: " + weapon + System.lineSeparator();
    }

    public void addListenerBackBtn(ActionListener actionListener) {
        backBtn.addActionListener(actionListener);
    }

}
