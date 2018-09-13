package com.gmail.vuyotm.swingy.model;

import com.gmail.vuyotm.swingy.model.artifacts.Armor;
import com.gmail.vuyotm.swingy.model.artifacts.Helm;
import com.gmail.vuyotm.swingy.model.artifacts.Weapon;
import com.gmail.vuyotm.swingy.model.characters.Regular;
import com.gmail.vuyotm.swingy.util.RegularFactory;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;

public class RegularContext {

    @NotNull
    private String      connectionString;
    private Connection  conn;

    public RegularContext(String connectionString) {
        this.connectionString = connectionString;
    }

    public boolean openConnection() {
        try {
            conn = DriverManager.getConnection(connectionString);
            return (true);
        }
        catch (SQLException e) {
            System.out.println("Error, failed to connect to database: " + e.getMessage());
            return (false);
        }
    }

    public void close() {
        try {
            if (conn != null)
                conn.close();
        }
        catch (SQLException e) {
            System.out.println("Error, failed to close database connection: " + e.getMessage());
        }
    }

    public ArrayList<Regular> getAllRegulars() {
        ArrayList<Regular>  list = new ArrayList<Regular>();
        String              sql;

        sql = "SELECT * FROM regulars";
        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                Regular regular;

                regular = RegularFactory.newRegular("placeholder", "scout");
                regular.setId(resultSet.getInt("id"));
                regular.setLevel(resultSet.getInt("regular_level"));
                regular.setPhysicalAttack(resultSet.getInt("physical_attack"));
                regular.setPhysicalDefense(resultSet.getInt("physical_defense"));
                regular.setShinsooAttack(resultSet.getInt("shinsoo_attack"));
                regular.setShinsooDefense(resultSet.getInt("shinsoo_defense"));
                regular.setSpeed(resultSet.getInt("speed"));
                regular.setHitPts(resultSet.getInt("hit_pts"));
                regular.setName(resultSet.getString("regular_name"));
                regular.setClassType(resultSet.getString("class_type"));
                regular.setExperience(resultSet.getInt("experience"));
                if (resultSet.getString("helm") != null) {
                    int level;

                    level = resultSet.getInt("helm_level");
                    regular.setHelm(new Helm("helm", level, 2 * level));
                }
                if (resultSet.getString("armor") != null) {
                    int level;

                    level = resultSet.getInt("armor_level");
                    regular.setArmor(new Armor("basic armor", level, 2 * level, 1 * level));
                }
                if (resultSet.getString("weapon") != null) {
                    int level;

                    level = resultSet.getInt("weapon_level");
                    if (resultSet.getString("weapon").equals("needle"))
                        regular.setWeapon(new Weapon("needle", level, 2 * level, 0));
                    else if (resultSet.getString("weapon").equals("ignition weapon"))
                        regular.setWeapon(new Weapon("ignition weapon", level, 5 * level, 10 * level));
                }
                list.add(regular);
            }
        }
        catch (SQLException e) {
            System.out.println("SQL get regulars error: " + e.getMessage());
        }
        return (list);
    }

    public Regular getRegular(int id) {
        String              sql;
        ResultSet           resultSet;
        Regular             regular;

        if (id <= 0)
            throw new IllegalArgumentException("Regular id must be positive.");
        regular = null;
        sql = "SELECT * FROM regulars WHERE id = ?";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
            prepStmt.setInt(1, id);
            resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                regular = RegularFactory.newRegular("placeholder", "scout");
                regular.setId(resultSet.getInt("id"));
                regular.setLevel(resultSet.getInt("regular_level"));
                regular.setPhysicalAttack(resultSet.getInt("physical_attack"));
                regular.setPhysicalDefense(resultSet.getInt("physical_defense"));
                regular.setShinsooAttack(resultSet.getInt("shinsoo_attack"));
                regular.setShinsooDefense(resultSet.getInt("shinsoo_defense"));
                regular.setSpeed(resultSet.getInt("speed"));
                regular.setHitPts(resultSet.getInt("hit_pts"));
                regular.setName(resultSet.getString("regular_name"));
                regular.setClassType(resultSet.getString("class_type"));
                regular.setExperience(resultSet.getInt("experience"));
                if (resultSet.getString("helm") != null) {
                    int level;

                    level = resultSet.getInt("helm_level");
                    regular.setHelm(new Helm("helm", level, 2 * level));
                }
                if (resultSet.getString("armor") != null) {
                    int level;

                    level = resultSet.getInt("armor_level");
                    regular.setArmor(new Armor("basic armor", level, 2 * level, 1 * level));
                }
                if (resultSet.getString("weapon") != null) {
                    int level;

                    level = resultSet.getInt("weapon_level");
                    if (resultSet.getString("weapon").equals("needle"))
                        regular.setWeapon(new Weapon("needle", level, 2 * level, 0));
                    else if (resultSet.getString("weapon").equals("ignition weapon"))
                        regular.setWeapon(new Weapon("ignition weapon", level, 5 * level, 10 * level));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("SQL get regular error: " + e.getMessage());
        }
        return (regular);
    }

    public void postRegular(Regular regular) {
        String  sql;

        if (regular == null)
            throw new IllegalArgumentException("Regular may not be null.");
        sql = "INSERT INTO regulars VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
            prepStmt.setInt(2, regular.getLevel());
            prepStmt.setInt(3, regular.getPhysicalAttack());
            prepStmt.setInt(4, regular.getPhysicalDefense());
            prepStmt.setInt(5, regular.getShinsooAttack());
            prepStmt.setInt(6, regular.getShinsooDefense());
            prepStmt.setInt(7, regular.getSpeed());
            prepStmt.setInt(8, regular.getHitPts());
            prepStmt.setString(9, regular.getName());
            prepStmt.setString(10, regular.getClassType());
            prepStmt.setInt(11, regular.getExperience());
            if (regular.getHelm() != null) {
                prepStmt.setString(12, regular.getHelm().getType());
                prepStmt.setInt(13, regular.getHelm().getLevel());
            }
            if (regular.getArmor() != null) {
                prepStmt.setString(14, regular.getArmor().getType());
                prepStmt.setInt(15, regular.getArmor().getLevel());
            }
            if (regular.getWeapon() != null) {
                prepStmt.setString(16, regular.getWeapon().getType());
                prepStmt.setInt(17, regular.getWeapon().getLevel());
            }
            prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQL post regular error: " + e.getMessage());
        }
    }

    public void putRegular(Regular regular) {
        String  sql;

        if (regular == null)
            throw new IllegalArgumentException("Regular may not be null.");
        sql = "UPDATE regulars SET " +
                "regular_level = ?, " +
                "physical_attack = ?, " +
                "physical_defense = ?, " +
                "shinsoo_attack = ?, " +
                "shinsoo_defense = ?, " +
                "speed = ?, " +
                "hit_pts = ?, " +
                "regular_name = ?, " +
                "class_type = ?, " +
                "experience = ?, " +
                "helm = ?, " +
                "helm_level = ?, " +
                "armor = ?, " +
                "armor_level = ?, " +
                "weapon = ?, " +
                "weapon_level = ? " +
                "WHERE id = ?";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
            prepStmt.setInt(1, regular.getLevel());
            prepStmt.setInt(2, regular.getPhysicalAttack());
            prepStmt.setInt(3, regular.getPhysicalDefense());
            prepStmt.setInt(4, regular.getShinsooAttack());
            prepStmt.setInt(5, regular.getShinsooDefense());
            prepStmt.setInt(6, regular.getSpeed());
            prepStmt.setInt(7, regular.getHitPts());
            prepStmt.setString(8, regular.getName());
            prepStmt.setString(9, regular.getClassType());
            prepStmt.setInt(10, regular.getExperience());
            if (regular.getHelm() != null) {
                prepStmt.setString(11, regular.getHelm().getType());
                prepStmt.setInt(12, regular.getHelm().getLevel());
            }
            if (regular.getArmor() != null) {
                prepStmt.setString(13, regular.getArmor().getType());
                prepStmt.setInt(14, regular.getArmor().getLevel());
            }
            if (regular.getWeapon() != null) {
                prepStmt.setString(15, regular.getWeapon().getType());
                prepStmt.setInt(16, regular.getWeapon().getLevel());
            }
            prepStmt.setInt(17, regular.getId());
            prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQL put regular error: " + e.getMessage());
        }
    }

    public void deleteRegular(int id) {
        String  sql;

        if (id <= 0)
            throw new IllegalArgumentException("Regular id must be positive.");
        sql = "DELETE FROM regulars WHERE id = ?";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("SQL delete regular error: " + e.getMessage());
        }
    }

}
