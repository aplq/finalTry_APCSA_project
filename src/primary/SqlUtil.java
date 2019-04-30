package primary;


import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SqlUtil {
    public static ArrayList<Section> getActiveSections(Connection conn) throws SQLException {
        return SqlUtil.getSection(conn, "SELECT (*) FROM Section WHERE isActive=true;");
    }
    public static ArrayList<Section> getAllSections(Connection conn) throws SQLException {
        return SqlUtil.getSection(conn, "SELECT (*) FROM Section;");
    }
    private static ArrayList<Section> getSection(Connection conn, String sql) throws SQLException{
        ArrayList<Section> activeSections = new ArrayList<Section>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            activeSections.add(new Section(conn, rs));
        }
        rs.close();
        stmt.close();
        return activeSections;
    }
    public static void deleteStu(Connection conn, Student stu) throws SQLException{
        ArrayList<Section> sections = SqlUtil.getAllSections(conn);
        for(Section sec: sections){
            sec.removeStu(stu);
        }
        stu.delete();
    }
    private static ArrayList<GridTemplate> getGridTemplates(Connection conn) throws SQLException{
        ArrayList<GridTemplate> gridTemplates = new ArrayList<GridTemplate>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT (*) FROM gridtemplates");
        while(rs.next()){
            gridTemplates.add(new GridTemplate(conn, rs));
        }
        rs.close();
        stmt.close();
        return gridTemplates;
    }
    public static void deleteGridTemaplate(Connection conn, GridTemplate gt) throws SQLException{
        ArrayList<Section> sections = SqlUtil.getAllSections(conn);
        for(Section sec: sections){
            sec.removeGrid(gt);
        }
        gt.delete();
    }
}