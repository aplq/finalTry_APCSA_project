package primary;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.String;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Purpose - To set up the SQL database part of Grades
 * Instance vairables- 	gridId - Unique id for each grid
 * 						studentId- The student ID or the lunch number for the each student
 */

public class GradeSet {
	private long gridId;
	private long studentId;
	private ArrayList<Integer> grades;

	//Constructor to initialize the variables and to add them to the database
	public GradeSet(Connection conn, long gridId, long studentId) throws SQLException{
		this.gridId=gridId;
		this.studentId=studentId;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT (*) FROM Grades WHERE gridId="+this.gridId+" AND studemtId="+this.studentId+";");
        rs.next();
        this.grades = new ArrayList<Integer>();
        for(int i =0; i<50; i++)
		{
			grades.add(rs.getInt("grade"+i));
			if(grades.get(i)<0) {
				grades.remove(i);
				break;
			}
			
		}
        stmt.close();
	}



	
	//Getters- to find the information from different locations
	public long getGridId() {
		return gridId;
	}
	public long getStudentId() {
		return studentId;
	}
	public ArrayList<Integer> getGrades() {
		return grades;
	}
	
	//Set individual grades- uses SQL commands to change the grades
	public void changeGrade(Connection conn, int position, int value)throws SQLException {
		this.grades.set(position, value);
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Grades SET grades= '"+grades+"' WHERE internalId="+this.grades+";");
		stmt.close();
		//INSERT SQL
	}

	//Used to display the grades
	public void display(){

			System.out.println("Grades: "+this.grades);
			System.out.println("GridID: "+this.gridId);
			//System.out.println("Type: "+this.);
	}

	//Used to shove data read from the CSV into mySQL database
	public static void loadAssignment(Connection conn, ArrayList<String> data) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO GradeSet VALUES("+data.get(0)+"'"+data.get(1)+"','"+data.get(2)+"',"+data.get(3)+","+data.get(4)+","+data.get(5)+");");
		stmt.close();
	}
	
	
}
