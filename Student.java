package primary;
/* This class is to hold a student
 * the sql still needs to be added
 * author Ethan Brinser
 * 22 March 2019
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
	private long internalId;
	private String firstName;
	private String lastName;
	private short gender;
	private short grade;
	private long studentId;
	public static final String[] genders = {"Male","Female","Other"};
	public Student(Connection conn, long internalId) throws SQLException {
		super();
		this.internalId=internalId;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Assignments WHERE internalId="+this.internalId+";");
		rs.next();
		this.firstName=rs.getString("firstName");
		this.lastName=rs.getString("lastName");
		this.gender=rs.getShort("gender");
		this.grade=rs.getShort("grade");
		this.studentId=rs.getLong("studentId");
		stmt.close();
	}
	
	//getters
	public long getInternalId() {
		return internalId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getGender() {
		return gender;
	}
	public int getGrade() {
		return grade;
	}
	public long getStudentId() {
		return studentId;
	}
	
	//for shortened display
	public String firstAndLastInitial() {
		return (this.firstName+" "+this.lastName.charAt(0));
	}
	
	//setters
	public void setFirstName(Connection conn, String firstName) throws SQLException {
		this.firstName = firstName;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET firstName= '"+firstName+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setLastName(Connection conn, String lastName) throws SQLException {
		this.lastName = lastName;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET lastName= '"+lastName+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setGender(Connection conn, short gender) throws SQLException {
		this.gender = gender;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET gender= '"+gender+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setGrade(Connection conn, short grade) throws SQLException {
		this.grade = grade;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET grade= '"+grade+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setStudentId(Connection conn, long studentId) throws SQLException {
		this.studentId = studentId;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET studentId= '"+studentId+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	public static boolean addStudent(Connection conn, Student student) throws SQLException{

		Statement stmt=null;
		try {
			stmt = conn.createStatement();
			stmt.execute("UPDATE Sections SET stu"+this.numStudents()+"= "+stu.getInternalId()+" WHERE internalId="+this.internalId+";");
			stmt.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	}

	public static boolean removeStudent(Connection conn, Student student) throws SQLException {

		Statement stmt=null;
		int stuNumber=0;
		for(;stuNumber<this.students.size();stuNumber++) {
			if(this.students.get(stuNumber).getInternalId()==stu.getInternalId()) {
				break;
			}
		}
		try {
			//remove the student
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE Sections SET stu"+stuNumber+"= -1 WHERE internalId="+this.internalId+";");
			this.grids.remove(stuNumber);
			//shift students over
			while(stuNumber<this.students.size()-1) {
				stmt = conn.createStatement();
				stmt.addBatch("UPDATE Sections SET stu"+stuNumber+"= "+this.students.get(stuNumber+1).getInternalId()+
						" WHERE internalId="+this.internalId+";");
				stuNumber++;
			}
			//set the position after the grid to -1
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE Sections SET stu"+this.students.size()+"= -1 WHERE internalId="+this.internalId+";");
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

}
