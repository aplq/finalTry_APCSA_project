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

public class Student extends SqlBase{
	private long internalId;
	private String firstName;
	private String lastName;
	private short gender;
	private short grade;
	private long studentId;
	public static final String[] genders = {"Male","Female","Other"};

    public Student(Connection conn, long internalId) throws SQLException {
		super(conn);

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
	public void setFirstName(String firstName) throws SQLException {
		this.firstName = firstName;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET firstName= '"+firstName+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setLastName(String lastName) throws SQLException {
		this.lastName = lastName;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET lastName= '"+lastName+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setGender(short gender) throws SQLException {
		this.gender = gender;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET gender= '"+gender+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setGrade(short grade) throws SQLException {
		this.grade = grade;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET grade= '"+grade+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setStudentId(long studentId) throws SQLException {
		this.studentId = studentId;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Students SET studentId= '"+studentId+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
    public void delete() throws SQLException{
        System.out.println("Deleate is not set up for Student");
    }
}
