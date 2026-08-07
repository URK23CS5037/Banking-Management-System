

package database;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/banking_management_system";

    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_PASSWORD";

    public static Connection getConnection() {

        try {
            Connection con =
                DriverManager.getConnection(URL, USER, PASSWORD);

            //System.out.println("DB Connected");
            return con;

        } catch (Exception e) {
            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }

        return null;
    }
}
