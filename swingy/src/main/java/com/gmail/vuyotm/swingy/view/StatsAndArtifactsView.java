package com.gmail.vuyotm.swingy.view;

import com.gmail.vuyotm.swingy.model.characters.Regular;

public class StatsAndArtifactsView extends BaseConsoleView {

    public void displayStatsAndEquipment(Regular regular) {
        String  statsAndEquipment;
        String  helm;
        String  armor;
        String  weapon;

        if (regular == null)
            throw new IllegalArgumentException("regular may not be null");
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
        statsAndEquipment = "";
        statsAndEquipment += System.lineSeparator();
        statsAndEquipment += "STATS" + System.lineSeparator();
        statsAndEquipment += System.lineSeparator();
        statsAndEquipment += "Name: " + regular.getName() + System.lineSeparator();
        statsAndEquipment += "Level: " + regular.getLevel() + System.lineSeparator();
        statsAndEquipment += "Position: " + regular.getClassType() + System.lineSeparator();
        statsAndEquipment += "Hit Points: " + regular.getHitPts() + System.lineSeparator();
        statsAndEquipment += "Physical Attack: " + regular.getPhysicalAttack() + System.lineSeparator();
        statsAndEquipment += "Shinsoo Attack: " + regular.getShinsooAttack() + System.lineSeparator();
        statsAndEquipment += "Physical Defense: " + regular.getPhysicalDefense() + System.lineSeparator();
        statsAndEquipment += "Shinsoo Defense: " + regular.getShinsooDefense() + System.lineSeparator();
        statsAndEquipment += "Speed: " + regular.getSpeed() + System.lineSeparator();
        statsAndEquipment += "Experience: " + regular.getExperience() + System.lineSeparator();
        statsAndEquipment += System.lineSeparator();
        statsAndEquipment += "ARTIFACTS" + System.lineSeparator();
        statsAndEquipment += System.lineSeparator();
        statsAndEquipment += "Helm: " + helm + System.lineSeparator();
        statsAndEquipment += "Armor: " + armor + System.lineSeparator();
        statsAndEquipment += "Weapon: " + weapon + System.lineSeparator();
        writeToScreen(statsAndEquipment);
    }

}
