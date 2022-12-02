package Recv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    //{"skierID":"39939", "resortID":"8", "liftID":"32", "seasonID":"2022", "dayID":"1", "time":"260"}

    String jdbcURL = "jdbc:postgresql://localhost:5432/SkierDB";
    String username = "postgres";
    String password = "xunandi";
    Connection connection;

    public ConnectionManager() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(jdbcURL, username, password);
            //System.out.println("Connnected to Database!");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
