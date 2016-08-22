package de.hhnracing.server;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to the database so that data can be written and read
 */
public class DatabaseManager {

    private static final String FILE_NAME = "members.sqlite3";
    private final Connection connection;

    DatabaseManager() {
        String home = System.getProperty("user.home");
        Path dbPath = Paths.get(home, FILE_NAME);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath.toAbsolutePath());
        } catch (SQLException e) {
            System.err.println("Failed to connect to SQLite database!");
            throw new RuntimeException(e);
        }
    }

}
