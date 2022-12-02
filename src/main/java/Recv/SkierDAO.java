package Recv;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SkierDAO {

    public static void addNewSkierByMessage(String msg, ConnectionManager CM) throws SQLException {
        Skier skier = parseMessage(msg);
        String sql = "INSERT INTO skier_table (\"skier_id\", \"resort_id\", \"lift_id\", \"season_id\", \"day_id\", \"time\")"
                + " VALUES("
                + "'" + skier.getSkierID() + "',"
                + "'" + skier.getResortID() + "',"
                + "'" + skier.getLiftID() + "',"
                + "'" + skier.getSeasonID() + "',"
                + "'" + skier.getDayID() + "',"
                + "'" + skier.getTime() + "'"
                + ")";
        try(Statement statement = CM.getConnection().createStatement()){
            int rows = statement.executeUpdate(sql);
//            if (rows > 0){
//                System.out.println("A new row is added");
//            }
            CM.getConnection().close();
        }
    }

    public static Skier parseMessage(String msg) {
        String skierID = "";
        int resortID = 0;
        int liftID = 0;
        int seasonID = 0;
        int dayID = 0;
        int time = 0;

        String[] msg_splited = msg.split(",");

        for (String s : msg_splited) {
            if (s.contains("skierID")) {
                skierID = extractInt(s);
            }else if(s.contains("resortID")) {
                resortID = Integer.valueOf(extractInt(s));
            }else if(s.contains("liftID")) {
                liftID = Integer.valueOf(extractInt(s));
            }else if(s.contains("seasonID")) {
                seasonID = Integer.valueOf(extractInt(s));
            }else if(s.contains("dayID")) {
                dayID = Integer.valueOf(extractInt(s));
            }else if(s.contains("time")) {
                time = Integer.valueOf(extractInt(s));
            }
        }
        return new Skier(skierID, resortID, liftID, seasonID, dayID, time);
    }

    // Function to return the modified string
    static String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^0-9]", ""); // regular expression

        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", "");

        if (str.equals(""))
            return "-1";

        return str;
    }

}
