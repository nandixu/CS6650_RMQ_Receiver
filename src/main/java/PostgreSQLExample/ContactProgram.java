package PostgreSQLExample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactProgram {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/demodb";
        String username = "postgres";
        String password = "xunandi";

        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to database");
            String sql = "INSERT INTO contacts (\"FirstName\", \"LastName\")"
                    + " VALUES('Erni', 'Dai')";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("A new row is added");
            }
            connection.close();
        }catch (SQLException e) {
            System.out.println("Error in connection to PostgreSQL server.");
            e.printStackTrace();
        }
    }
}
