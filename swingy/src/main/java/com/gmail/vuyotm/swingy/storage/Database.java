package com.gmail.vuyotm.swingy.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    public static final String  DB_NAME = "swingy.db";
    public static final String  CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static void createDB() {

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS regulars " +
                                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, regular_level INTEGER NOT NULL, physical_attack INTEGER NOT NULL, physical_defense INTEGER NOT NULL," +
                                "shinsoo_attack INTEGER NOT NULL, shinsoo_defense INTEGER NOT NULL, speed INTEGER NOT NULL, hit_pts INTEGER NOT NULL," +
                                "regular_name TEXT NOT NULL, class_type TEXT NOT NULL, experience INTEGER NOT NULL," +
                                "helm TEXT DEFAULT(NULL), helm_level INTEGER DEFAULT(0), armor TEXT DEFAULT(NULL), armor_level INTEGER DEFAULT(0)," +
                                "weapon TEXT DEFAULT(NULL), weapon_level INTEGER DEFAULT(0))");
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void dropTable() {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE regulars");
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
