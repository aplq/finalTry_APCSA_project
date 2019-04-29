package primary;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.String;
import java.sql.Statement;
import java.sql.ResultSet;



public class GradeSet {
	private long gridId;
	private long studentId;
	private ArrayList<Integer> grades;
	
	public GradeSet(Connection conn, long gridId, long studentId) throws SQLException{
		this.gridId=gridId;
		this.studentId=studentId;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT (*) FROM Grades WHERE gridId="+this.gridId+" AND studemtId="+this.studentId+";");
        rs.next();
        this.grades = new ArrayList<Integer>();
        for(int i =0; i<50; i++)
		{
			grades.add(rs.getShort("grade"+i));
			if(grades.get(i)<0) {
				grades.remove(i);
				break;
			}
			
		}
        stmt.close();
	}



	
	//getters
	public long getGridId() {
		return gridId;
	}
	public long getStudentId() {
		return studentId;
	}
	public ArrayList<Integer> getGrades() {
		return grades;
	}
	
	//set individual grades
	public void changeGrade(Connection conn, int position, int value)throws SQLException {
		this.grades.set(position, value);
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Grades SET grades= '"+grades+"' WHERE internalId="+this.grades+";");
		stmt.close();
		//INSERT SQL
	}

	public void display(){

			System.out.println("Grades: "+this.grades);
			System.out.println("GridID: "+this.gridId);
			System.out.println("Type: "+this.type);
	}

	
	
}
