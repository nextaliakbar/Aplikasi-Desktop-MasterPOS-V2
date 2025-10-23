/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author usER
 */
public class Koneksi {
    
    private static Connection connection;

    /**
     * Method ini secara cerdas menentukan path yang benar untuk database.
     * Path ini berada di folder AppData pengguna, yang selalu bisa ditulisi.
     * Contoh: C:\Users\NamaAnda\AppData\Roaming\MasterPOS\master_pos.db
     */
    private static String getDatabasePath() {
        String appDataPath = System.getenv("APPDATA");
        
        File appFolder = new File(appDataPath, "MasterPOS");
        if (!appFolder.exists()) {
            appFolder.mkdirs();
        }
        
        return appFolder.getAbsolutePath() + File.separator + "master_pos.db";
    }

    public static Connection getConnection() {
        String dbPath = getDatabasePath();

        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public static void setupDatabase() {
        String dbPath = getDatabasePath();
        File dbFile = new File(dbPath);

        String schemaFileName = "master_pos_schema.sql";

        if (!dbFile.exists()) {
            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {

                String sqlScript = new String(Files.readAllBytes(Paths.get(schemaFileName)));

                stmt.executeUpdate(sqlScript);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
