package primary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	//so the user can do custom sql statments
	public Section(Connection conn, ResultSet rs) throws SQLException {
		super(conn);
		this.name=rs.getString("name");
		this.teacher=rs.getString("teacher");
		//temp arrays to hold internalIds
		long[] stuIds = new long[Section.MAX_NUM_OF_STUDENTS];
		long[] gridTempIds = new long[Section.MAX_NUM_GRIDS_PER_SECTION];
		//number of each to speed up array list adding
		int numStu=Section.MAX_NUM_OF_STUDENTS;
		int numGrids=Section.MAX_NUM_GRIDS_PER_SECTION;
		//get the student ids
		for(int a=0; a<stuIds.length; a++) {
			stuIds[a]=rs.getLong("stu"+a);
			//check if it is the last student
			if(stuIds[a]<0) {
				numStu=a;
				break;
			}
		}
		//get the grid template ids
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
			this.students.add(new Student(conn, stuIds[c]));
		}
		//create the grid templates
		for(int d=0; d<numGrids; d++) {
			this.grids.add(new GridTemplate(conn, gridTempIds[d]));
		}
	}
	public Section(Connection conn, long internalId) throws SQLException {
		super(conn);
		this.internalId=internalId;
		//set up the sql query
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Sections WHERE internalId="+this.internalId+";");
		rs.next();
		this.name=rs.getString("name");
		this.teacher=rs.getString("teacher");
		//temp arrays to hold internalIds
		long[] stuIds = new long[Section.MAX_NUM_OF_STUDENTS];
		long[] gridTempIds = new long[Section.MAX_NUM_GRIDS_PER_SECTION];
		//number of each to speed up array list adding
		int numStu=Section.MAX_NUM_OF_STUDENTS;
		int numGrids=Section.MAX_NUM_GRIDS_PER_SECTION;
		//get the student ids
		for(int a=0; a<stuIds.length; a++) {
			stuIds[a]=rs.getLong("stu"+a);
			//check if it is the last student
			if(stuIds[a]<0) {
				numStu=a;
				break;
			}
		}
		//get the grid template ids
		for(int b=0; b<gridTempIds.length; b++) {
			gridTempIds[b]=rs.getLong("grid"+b);
			//check if it is the last grid template
			if(gridTempIds[b]<0) {
				numGrids=b;
				break;
			}
		}
		//finish the statement so constructors can do sql
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
		return students;
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
	
	//setters
	public void setName(String name) throws SQLException {
		this.name = name;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Section SET name= '"+name+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public void setActive(boolean isActive) throws SQLException {
		this.isActive = isActive;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Section SET isActive= "+isActive+" WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	public void setTeacher(String teacher) throws SQLException {
		this.teacher = teacher;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Section SET teacher= '"+teacher+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}
	public boolean removeStu(Student stu) {
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
	public boolean addStu(Student stu) {
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
	public boolean removeGrid(GridTemplate grid) {
		Statement stmt=null;
		int gridNumber=0;
		for(;gridNumber<this.grids.size();gridNumber++) {
			if(this.grids.get(gridNumber).getInternalId()==grid.getInternalId()) {
				break;
			}
		}
		try {
			//remove the grid
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE Sections SET grid"+gridNumber+"= -1 WHERE internalId="+this.internalId+";");
			this.grids.remove(gridNumber);
			//shift grids over
			while(gridNumber<this.grids.size()-1) {
				stmt = conn.createStatement();
				stmt.addBatch("UPDATE Sections SET grid"+gridNumber+"= "+this.grids.get(gridNumber+1).getInternalId()+
						" WHERE internalId="+this.internalId+";");
				gridNumber++;
			}
			//set the position after the grid to -1
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE Sections SET grid"+this.grids.size()+"= -1 WHERE internalId="+this.internalId+";");
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	public boolean addGrid(GridTemplate grid) {
		Statement stmt=null;
		try {
			stmt = conn.createStatement();
			stmt.execute("UPDATE Sections SET grid"+this.grids.size()+"= "+grid.getInternalId()+" WHERE internalId="+this.internalId+";");
			stmt.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}
