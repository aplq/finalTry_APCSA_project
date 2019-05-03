package primary;

import java.sql.*;
import java.util.ArrayList;

/* This class is to hold a section
 * the sql is added for read and update
 * author Ethan Brinser
 * 22 March 2019
 */


public class Section extends SqlBase{
	private long internalId;
	private String name;
	private String teacher;
	private boolean isActive;
	private ArrayList<Student> students;	//max 50
	private ArrayList<GridTemplate> grids;	//max 50
	public static final int MAX_NUM_OF_STUDENTS=50;
	public static final int MAX_NUM_GRIDS_PER_SECTION=50;

	//Custom SQL statements can be made by the user
	//Constructor 1 uses SQL commands

	/**
	 * @param conn
	 * @param rs
	 * @throws SQLException
	 */
	public Section(Connection conn, ResultSet rs) throws SQLException {
		super(conn);

		this.name=rs.getString("name");
		this.teacher=rs.getString("teacher");
		//temp long type arrays to hold internalIds
		long[] stuIds = new long[Section.MAX_NUM_OF_STUDENTS];
		long[] gridTempIds = new long[Section.MAX_NUM_GRIDS_PER_SECTION];

		//Number of each to speed up array list adding
		int numStu=Section.MAX_NUM_OF_STUDENTS;
		int numGrids=Section.MAX_NUM_GRIDS_PER_SECTION;

		//Retrieving StudentsIds from SQL
		for(int a=0; a<stuIds.length; a++) {
			stuIds[a]=rs.getLong("student"+a);
			//check if it is the last student
			if(stuIds[a]<0) {
				numStu=a;
				break;
			}
		}

		//Getting grid tempelates from the SQL
		for(int b=0; b<gridTempIds.length; b++) {
			gridTempIds[b]=rs.getLong("grid"+b);
			//check if it is the last grid template
			if(gridTempIds[b]<0) {
				numGrids=b;
				break;
			}
		}

		//Initialize the array lists
		this.students=new ArrayList<Student>();
		this.grids=new ArrayList<GridTemplate>();

		//Create the students
		for(int c=0; c<numStu; c++) {

			//Command to add new students into the database
			this.students.add(new Student(conn, stuIds[c]));
		}

		//Creating the grid templates
		for(int d=0; d<numGrids; d++) {
			//
			this.grids.add(new GridTemplate(conn, gridTempIds[d]));
		}
	}

	/**
	 * Constructor
	 * @param conn
	 * @param data
	 * @throws SQLException
	 */
	public Section(Connection conn, ArrayList<String> data) throws SQLException{
		super(conn);
		this.internalId=Integer.parseInt(data.get(0));
		this.name=data.get(1);
		this.teacher=data.get(2);
		this.isActive=Boolean.parseBoolean(data.get(3));

		//Query to get things based on sections
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO section (internalID, name, teachet, isActive) VALUES ("+this.internalId+", '"+this.name+"', '"+this.teacher+"', "+this.isActive+");");
		stmt.close();
		for(int a=0; a<50; a++){
			if(data.get(a+4).equals("-1")){
				break;
			} else {
				this.addStu(new Student(conn, Integer.parseInt(data.get(a+4))));
			}
		}
		for(int a=0; a<50; a++){
			if(data.get(a+54).equals("-1")){
				break;
			} else {
				this.addStu(new Student(conn, Integer.parseInt(data.get(a+54))));
			}
		}
	}

	/**
	 * Another constuctor
	 * @param conn
	 * @param internalId
	 * @throws SQLException
	 */
	public Section(Connection conn, long internalId) throws SQLException {
		super(conn);
		this.internalId=internalId;
		//set up the sql query
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Section WHERE internalId="+this.internalId+";");
		rs.next();
		this.name=rs.getString("name");
		this.teacher=rs.getString("teacher");

		//Temporary arrays to hold internalIds
		long[] stuIds = new long[Section.MAX_NUM_OF_STUDENTS];
		long[] gridTempIds = new long[Section.MAX_NUM_GRIDS_PER_SECTION];
		//number of each to speed up array list adding
		int numStu=Section.MAX_NUM_OF_STUDENTS;
		int numGrids=Section.MAX_NUM_GRIDS_PER_SECTION;

		//Get the student ids
		for(int a=0; a<stuIds.length; a++) {
			stuIds[a]=rs.getLong("student"+a);
			//check if it is the last student
			if(stuIds[a]<0) {
				numStu=a;
				break;
			}
		}

		//Get the grid template ids
		for(int b=0; b<gridTempIds.length; b++) {
			gridTempIds[b]=rs.getLong("grid"+b);
			//check if it is the last grid template
			if(gridTempIds[b]<0) {
				numGrids=b;
				break;
			}
		}

		//Finish the statement so constructors can do sql
		stmt.close();

		//Initialize the array lists
		this.students=new ArrayList<Student>();
		this.grids=new ArrayList<GridTemplate>();

		//Create the students
		for(int c=0; c<numStu; c++) {
			this.students.add(new Student(conn, stuIds[c]));
		}

		//create the grid templates
		for(int d=0; d<numGrids; d++) {
			this.grids.add(new GridTemplate(conn, gridTempIds[d]));
		}
	}

	/**
	 * Another constructor
	 * @param conn
	 * @param name
	 * @param teacher
	 * @throws SQLException
	 */
	public Section(Connection conn, String name, String teacher) throws SQLException{
		super(conn);
		this.name=name;
		this.teacher=teacher;
		this.isActive=true;
		this.students= new ArrayList<Student>();
		this.grids= new ArrayList<GridTemplate>();
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO Section (name, teacher, isActive) VALUES ("+name+","+teacher+",true );");
		stmt.close();
		//get the internalID that sql autogenerated
		stmt = conn.createStatement();
		ResultSet rs =stmt.executeQuery("SELECT LAST_INSERT_ID();");
		rs.next();
		this.internalId=rs.getLong(1);
		rs.close();
		stmt.close();
	}

	//getters
	public long getInternalId() {
		return internalId;
	}
	public String getName() {
		return name;
	}
	public String getTeacher() {
		return teacher;
	}
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	public ArrayList<GridTemplate> getGrids() {
		return grids;
	}
	public int numStudents() {
		return this.students.size();
	}
	public boolean isActive() {
		return this.isActive;
	}
	
	//Setters

	/**
	 * Sets name using SQL query
	 * @param name
	 * @throws SQLException
	 */
	public void setName(String name) throws SQLException {
		this.name = name;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Section SET name= '"+name+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	/**
	 * Makes the section active
	 * @param isActive
	 * @throws SQLException
	 */
	public void setActive(boolean isActive) throws SQLException {
		this.isActive = isActive;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Section SET isActive= "+isActive+" WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	/**
	 * Sets a teacher
	 * @param teacher
	 * @throws SQLException
	 */
	public void setTeacher(String teacher) throws SQLException {
		this.teacher = teacher;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Section SET teacher= '"+teacher+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	/**
	 * Removes students from a section
	 * @param stu
	 * @throws SQLException
	 */
	public void removeStu(Student stu) throws SQLException{
		Statement stmt=null;
		int stuNumber=0;
		for(;stuNumber<this.students.size();stuNumber++) {
			if(this.students.get(stuNumber).getInternalId()==stu.getInternalId()) {
				break;
			}
		}

		//Remove the student
		stmt = conn.createStatement();
		stmt.addBatch("UPDATE Section SET student"+stuNumber+"= -1 WHERE internalId="+this.internalId+";");
		this.grids.remove(stuNumber);

		//Shift students over
		while(stuNumber<this.students.size()-1) {
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE Section SET student"+stuNumber+"= "+this.students.get(stuNumber+1).getInternalId()+
					" WHERE internalId="+this.internalId+";");
			stuNumber++;
		}
		//set the position after the grid to -1
		stmt = conn.createStatement();
		stmt.addBatch("UPDATE Section SET student"+this.students.size()+"= -1 WHERE internalId="+this.internalId+";");
		stmt.executeBatch();
		stmt.close();
	}

	/**
	 * Adds student
	 * @param stu
	 * @throws SQLException
	 */
	public void addStu(Student stu) throws SQLException{
		Statement stmt=null;
		stmt = conn.createStatement();
		stmt.execute("UPDATE Section SET student"+this.numStudents()+"= "+stu.getInternalId()+" WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	/**
	 * Removing students from the section
	 * @param grid
	 * @throws SQLException
	 */
	public void removeGrid(GridTemplate grid) throws SQLException{
		Statement stmt=null;
		int gridNumber=0;
		for(;gridNumber<this.grids.size();gridNumber++) {
			if(this.grids.get(gridNumber).getInternalId()==grid.getInternalId()) {
				break;
			}
		}

		//Remove the grid
		stmt = conn.createStatement();
		stmt.addBatch("UPDATE Section SET grid"+gridNumber+"= -1 WHERE internalId="+this.internalId+";");
		this.grids.remove(gridNumber);

		//Shift grids over
		while(gridNumber<this.grids.size()-1) {
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE Section SET grid"+gridNumber+"= "+this.grids.get(gridNumber+1).getInternalId()+
					" WHERE internalId="+this.internalId+";");
			gridNumber++;
		}

		//Set the position after the grid to -1
		stmt = conn.createStatement();
		stmt.addBatch("UPDATE Section SET grid"+this.grids.size()+"= -1 WHERE internalId="+this.internalId+";");
		stmt.executeBatch();
		stmt.close();
	}

	/**
	 * Adds grid to sections
	 * @param grid
	 * @throws SQLException
	 */
	public void addGrid(GridTemplate grid) throws SQLException{
		Statement stmt=null;
			stmt = conn.createStatement();
			stmt.execute("UPDATE Section SET grid"+this.grids.size()+"= "+grid.getInternalId()+" WHERE internalId="+this.internalId+";");
			stmt.close();
	}

	//Delete function
	public void delete() throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("DELETE FROM Section WHERE internalID="+this.internalId+",");
		stmt.close();
	}
}
