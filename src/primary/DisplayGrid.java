package primary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* This class is to hold a grid to be displaded
 * the fx still needs to be added in this class
 * or another class
 * author Ethan Brinser
 * 22 March 2019
 */
public class DisplayGrid extends SqlBase{
	private Section section;
	private short[][] grades;
	private long gridTemplateId;
	
	public DisplayGrid(Connection conn, Section section, int sectionGridNumber) throws SQLException {
		super(conn);
		this.section=section;
		this.gridTemplateId=section.getGrids().get(sectionGridNumber).getInternalId();
		//get the grades in order
		this.grades=new short[section.getStudents().size()][section.getGrids().size()];
		for(int row=0; row<section.getStudents().size(); row++) {
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery("SELECT * FROM Grades WHERE "+
						"gridTemplateId = "+this.gridTemplateId+" AND "+
						"studentId = "+section.getStudents().get(row)+";");
			//go through the results
			rs.next();
			for(int col=0; col<section.getGrids().size();col++) {
				this.grades[row][col]=rs.getShort("g"+col);
			}
		}
	}
	//getters
	public Section getSection() {
		return section;
	}
	public short[][] getGrades() {
		return grades;
	}
	
	//the grades setter
	public boolean setGrade(int rowIndex, int colIndex, short grade) {
		try {
			Statement sm = conn.createStatement();
			sm.executeUpdate("UPDATE Grades SET g"+colIndex+"="+grade+"WHERE gridTemplate="+this.gridTemplateId+
					" AND student="+this.section.getStudents().get(rowIndex)+";");
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
}
