package primary;
/* This class is to hold a assignment
 * the sql should work hopefully
 * author Ethan Brinser
 * 22 March 2019
 */
import java.sql.*;
import java.util.ArrayList;
//Class for the storage nad modifications of assignments
public class Assignment extends SqlBase{

	private long internalId;
	private String description;
	private short type;	//0=normal, 1=quiz, 2=test
    private int weight;


	//Main constructor for assignments based of to add assignment data into SQL
	public Assignment(Connection conn, long internalId) throws SQLException {
		super(conn);


		//set internal id
		this.internalId=internalId;
		//run the sql
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Assignments WHERE internalId="+this.internalId+";");
		//Handle the results and fill out the fields
		rs.next();
		this.description=rs.getString("description");
		this.type=rs.getShort("assignmentType");
		this.weight=rs.getInt("weight");
		stmt.close();
	}

	//creating a new assignment
	public Assignment(Connection conn, String description, short type, int weight) throws SQLException {
		super(conn);

		//set the fields of the assignments
		this.description=description;
		this.type=type;
		this.weight=weight;

		//load the data in to the data base using SQL commands
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO Assignments (description,weight,assignmentType) VALUES('"+this.description+"',"+this.weight+","+this.type+");");
		stmt.close();

		//Autogenerating the internal id using SQL Query
		stmt = conn.createStatement();
		ResultSet rs =stmt.executeQuery("SELECT LAST_INSERT_ID();");
		rs.next();
		this.internalId=rs.getLong(1);
		rs.close();
		stmt.close();
	}
	//loading an assigment from file
	public static void loadAssignment(Connection conn, ArrayList<String> data) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO Assignments VALUES("+data.get(0)+"'"+data.get(1)+"',"+data.get(2)+","+data.get(3)+");");
		stmt.close();
	}
	//pushing data to file
	public ArrayList<String> pullAssignment(){
		ArrayList<String> data = new ArrayList<String>();
		data.add(Long.toString(this.internalId));
		data.add(this.description);
		data.add(Integer.toString(this.weight));
		data.add(Short.toString(this.type));
		return data;
	}
	
	//all the getters
	public long getInternalId() {
		return internalId;
	}
	public String getDescription() {
		return description;
	}
	public short getType(){
		return this.type;
	}

	/**
	 * All the setters are down here
	 * The setters are being used to set the data into the database
	 * @param description
	 * @throws SQLException
	 */
	public void setDescription(String description) throws SQLException {
		this.description = description;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Assignments SET description= '"+description+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	public void setType(short type) throws SQLException {
		this.type=type;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Assignments SET assignmentType= "+type+" WHERE internalId="+this.internalId+";");
		stmt.close();
	}


    public void display(){
	    System.out.println("internalId: "+this.internalId);
        System.out.println("description: "+this.description);
        System.out.println("Weight: "+this.weight);
        System.out.println("Type: "+this.type);
    }

	/**
	 * Just deletes stuff
	 * @throws SQLException
	 */

	public void delete() throws SQLException{
		Assignment.delete(conn,internalId);
	}

	public static void delete(Connection conn, long internalId) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("DELETE FROM Assignments WHERE internalId="+internalId);
		stmt.close();
	}
	
}
