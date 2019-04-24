package primary;

import java.sql.Connection;
import java.util.ArrayList;

public class GradeSet {
	private long gridId;
	private long studentId;
	private ArrayList<Integer> grades;
	
	public GradeSet(Connection conn, long gridId, long studentId) {
		this.gridId=gridId;
		this.studentId=studentId;
		//INSERT SQL
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
	public void changeGrade(Connection conn, int position, int value) {
		this.grades.set(position, value);
		//INSERT SQL
	}
	
	
}
