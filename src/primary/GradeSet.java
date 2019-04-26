package primary;

import java.sql.Connection;
import java.util.ArrayList;

public class GradeSet extends SqlBase{
	private long gridId;
	private long studentId;
	private ArrayList<Integer> grades;
	
	public GradeSet(Connection conn, long gridId, long studentId) {
		super(conn);
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
	public void changeGrade(int position, int value) {
		this.grades.set(position, value);
		//INSERT SQL
	}
	
	
}
