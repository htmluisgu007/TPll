import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection connect() throws SQLException {
        var url = "jdbc:postgresql://localhost:5432/db_fatec";
        var user = "fatec";
        var pass = "fatec777";

        return DriverManager.getConnection(url, user, pass);
    }
}
