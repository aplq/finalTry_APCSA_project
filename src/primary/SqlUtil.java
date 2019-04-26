package primary;


import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SqlUtil {
    public static ArrayList<Section> getActiveSections(Connection conn) throws SQLException {
        ArrayList<Section> activeSections = new ArrayList<Section>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Section where isActive=true;");
        while(rs.next()){
            activeSections.add(new Section(conn, rs));
        }
        rs.close();
        stmt.close();
        return activeSections;
    }
}
