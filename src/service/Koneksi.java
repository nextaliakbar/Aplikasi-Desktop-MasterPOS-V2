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
        // Mengambil path ke folder AppData\Roaming
        String appDataPath = System.getenv("APPDATA");
        
        // Membuat subfolder khusus untuk aplikasi Anda agar lebih rapi
        File appFolder = new File(appDataPath, "MasterPOS");
        if (!appFolder.exists()) {
            appFolder.mkdirs(); // Membuat folder jika belum ada
        }
        
        // Menggabungkan path folder dengan nama file database
        return appFolder.getAbsolutePath() + File.separator + "master_pos.db";
    }

    public static Connection getConnection() {
        // Selalu gunakan path dari method getDatabasePath()
        String dbPath = getDatabasePath();

        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            
            Class.forName("org.sqlite.JDBC");
            // Membuat koneksi ke database di lokasi yang benar (AppData)
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public static void setupDatabase() {
        // Menggunakan path yang sama untuk memeriksa keberadaan file
        String dbPath = getDatabasePath();
        File dbFile = new File(dbPath);

        // File skema tetap dibaca dari folder instalasi aplikasi
        String schemaFileName = "master_pos_schema.sql";

        if (!dbFile.exists()) {
            try (Connection conn = getConnection(); // Ini akan membuat file .db kosong di AppData
                 Statement stmt = conn.createStatement()) {

                // Membaca skema dari folder tempat .exe berada
                String sqlScript = new String(Files.readAllBytes(Paths.get(schemaFileName)));

                stmt.executeUpdate(sqlScript);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
