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
import java.util.ArrayList;

public class Student extends SqlBase{
	private long internalId;
	private String firstName;
	private String lastName;
	private short gender;
	private short grade;
	private long studentId;
	public static final String[] genders = {"Male","Female","Other"};

	//Loading a student into the database with a constructor

    /**
     *
     * @param conn
     * @param internalId
     * @throws SQLException
     */
    public Student(Connection conn, long internalId) throws SQLException {
		super(conn);

		this.internalId=internalId;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Student WHERE internalId="+this.internalId+";");
		rs.next();
		this.firstName=rs.getString("firstName");
		this.lastName=rs.getString("lastName");
		this.gender=rs.getShort("gender");
		this.grade=rs.getShort("grade");
		this.studentId=rs.getLong("studentId");
		stmt.close();
	}

    /**
     *
     * @param conn
     * @param firstName
     * @param lastName
     * @param gender
     * @param grade
     * @param studentId
     * @throws SQLException
     */
	//Creating and loading students onto the database with basic student information
	public Student(Connection conn, String firstName, String lastName, short gender, short grade, long studentId) throws SQLException{
		super(conn);
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.grade = grade;
		this.studentId = studentId;
		//load the data in to the data base
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO Students (firstName, lastName, gender, grade, studentId) " +
				"VALUES('"+this.firstName+"','"+this.lastName+"',"+this.gender+","+this.grade+","+this.studentId+");");
		stmt.close();
		//get the internalID that sql autogenerated
		stmt = conn.createStatement();
		ResultSet rs =stmt.executeQuery("SELECT LAST_INSERT_ID();");
		rs.next();
		this.internalId=rs.getLong(1);
		rs.close();
		stmt.close();
	}

	//Getters- used to calling info
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
	
	//For shortened display
	public String firstAndLastInitial() {
		return (this.firstName+" "+this.lastName.charAt(0));
	}
	
	//Setter- Setting variables using SQL commands
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
		Statement stmt = conn.createStatement();
		stmt.execute("DELETE FROM Students WHERE internalId="+this.internalId);
		stmt.close();
    }
	//loading an student from file
	public static void loadAssignment(Connection conn, ArrayList<String> data) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO Student VALUES("+data.get(0)+",'"+data.get(1)+"','"+data.get(2)+"',"+data.get(3)+","+data.get(4)+","+data.get(5)+");");
		stmt.close();
	}
	//Pushing data to file
	public ArrayList<String> pullAssignment(){
		ArrayList<String> data = new ArrayList<String>();
		data.add(Long.toString(this.internalId));
		data.add(this.firstName);
		data.add(this.lastName);
		data.add(Short.toString(this.gender));
		data.add(Short.toString(this.grade));
		data.add(Long.toString(this.studentId));
		return data;
	}
}
