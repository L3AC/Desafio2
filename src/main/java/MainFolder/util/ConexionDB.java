
package MainFolder.util;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {
    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PASS;

    static {
        Properties props = new Properties();
        try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(input);
            DB_URL = props.getProperty("db.url");
            DB_USER = props.getProperty("db.username");
            DB_PASS = props.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar database.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
