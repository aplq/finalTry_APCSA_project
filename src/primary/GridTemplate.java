package primary;
/* This class is to hold a grid template
 * the sql still needs to be added
 * author Ethan Brinser
 * 22 March 2019
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Develops the grid Tempelate
public class GridTemplate extends SqlBase{

	//Fields
	private long internalId;
	private String name;
	private ArrayList<Assignment> assignments;	//max 50
	public static final int MAX_NUM_ASSIGNMENTS=50;


	/**
	 * Making the Gris Tempelate
	 * @param conn
	 * @param intenalId
	 * @throws SQLException
	 */
	public GridTemplate(Connection conn, long intenalId) throws SQLException {
		super(conn);
		//Getting Internal ID
		this.internalId=intenalId;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Gridtemplates WHERE internalId="+this.internalId+";");
		rs.next();
		this.name=rs.getString("name");

		//Array to hold assignment ids
		long[] assignmentIds= new long[GridTemplate.MAX_NUM_ASSIGNMENTS];
		int numAssignments=0;

		//Get the student ids from the database
		for(int a=0; a<assignmentIds.length; a++) {
			assignmentIds[a]=rs.getLong("assignment"+a);
			//check if it is the last assignment
			if(assignmentIds[a]<0) {
				numAssignments=a;
				break;
			}
		}

		//Finish the statement so constructors can do sql- seen multiple times through otu the code
		stmt.close();

		//Initialize the array lists
		this.assignments=new ArrayList<Assignment>();

		//Create the students
		for(int c=0; c<numAssignments; c++) {
			this.assignments.add(new Assignment(conn, assignmentIds[c]));
		}
	}

	//getters
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}
	public long getInternalId() {
		return internalId;
	}
	public int numOfAssignments() {
		return assignments.size();
	}
	public String getName() {
		return name;
	}
	
	//setters
	public boolean addAssignment(Assignment a) {
		this.assignments.add(a);
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.execute("UPDATE GridTemplates SET assignment"+(this.assignments.size()-1)+"WHERE internalId="+this.internalId);
			stmt.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Removing the assignment from the Grid after the database
	 * @param a
	 * @return
	 */
	public boolean removeAssignment(Assignment a) {
		Statement stmt=null;
		int assignmentNumber=0;
		for(;assignmentNumber<this.assignments.size();assignmentNumber++) {
			if(this.assignments.get(assignmentNumber).getInternalId()==a.getInternalId()) {
				break;
			}
		}
		try {

			//Remove the student
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE GridTemplates SET assignment"+assignmentNumber+"= -1 WHERE internalId="+this.internalId+";");
			this.assignments.remove(assignmentNumber);

			//Shift students over
			while(assignmentNumber<this.assignments.size()-1) {
				stmt = conn.createStatement();
				stmt.addBatch("UPDATE GridTemplates SET assignment"+assignmentNumber+"= "+this.assignments.get(assignmentNumber+1).getInternalId()+
						" WHERE internalId="+this.internalId+";");
				assignmentNumber++;
			}

			//Set the position after the grid to -1
			stmt = conn.createStatement();
			stmt.addBatch("UPDATE GridTemplates SET assignment"+this.assignments.size()+"= -1 WHERE internalId="+this.internalId+";");
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Shuffling the assignments in the Grid
	 * @param assignments
	 * @return
	 */
	public boolean shuffleAssignments(ArrayList<Assignment> assignments) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			for(int n=0;n<assignments.size();n++) {
				stmt.addBatch("UPDATE GridTemplate SET assignment"+n+" = "+assignments.get(n)+" WHERE internalId = "+this.internalId+";");
			}
			stmt.executeBatch();
			stmt.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	public void delete() throws SQLException{
		System.out.println("Deleate is not set up for GridTemplate");
	}

	public static void addAssignmentCsv(Connection conn, ArrayList<String> data) throws SQLException{
		Statement stmt;
		stmt = conn.createStatement();
		String str="INSERT INTO GridTemplates VALUES("+data.get(0)+",'"+data.get(1)+"'";
		for(int s=2; s<data.size(); s++){
			str=str+","+data.get(s);
		}
		str=str+");";
		stmt.execute(str);
		stmt.close();
	}
}
