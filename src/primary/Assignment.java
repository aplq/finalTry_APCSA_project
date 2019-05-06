package primary;
/* This class is to hold a assignment
 * the sql should work hopefully
 * author Ethan Brinser
 * 22 March 2019
 */
import java.sql.*;
import java.util.ArrayList;

//Class for the storage and modifications of assignments
public class Assignment extends SqlBase{

	private long internalId;
	private String description;
	private short type;	//0=normal, 1=quiz, 2=test
    private int weight;


    /**
     * Main constructor for assignments based of to add assignment data into SQL
     * @param conn
     * @param internalId
     * @throws SQLException
     */
	public Assignment(Connection conn, long internalId) throws SQLException {
		super(conn);


		//set internal id
		this.internalId=internalId;

		//Run the sql

		//This statement is seen multiple times becuase its the basis of SQL
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Assignments WHERE internalId="+this.internalId+";");

		//Handle the results and fill out the fields
		rs.next();
		this.description=rs.getString("description");
		this.type=rs.getShort("assignmentType");
		this.weight=rs.getInt("weight");
		stmt.close();
	}
	/**
     * Creating a new assignment
     * @param conn
     * @param description
     * @param type
     * @param weight
     * @throws SQLException
     */
	public Assignment(Connection conn, String description, short type, int weight) throws SQLException {
		super(conn);

		//Set the fields of the assignments
		this.description=description;
		this.type=type;
		this.weight=weight;

		//Load the data in to the data base using SQL commands
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
	//create student base on user input
	public  Assignment(Connection conn, UserInput in) throws SQLException{
		super(conn);
		this.userSetDescription(in);
		this.userSetType(in);
		this.userSetWeigth(in);
		//Load the data in to the data base using SQL commands
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
	public void userSetDescription(UserInput in)throws SQLException{
		this.setDescription(in.getString("Description: "));
	}
	public void userSetType(UserInput in) throws SQLException{
		int type=0;
		do {
			type = in.getInt("Type:\n0: regular\n1: quiz\n:2: test");
		} while (this.type>=0 && this.type<=2);
		this.setType((short) type);
	}
	public void userSetWeigth(UserInput in) throws SQLException{
		this.setWeight(in.getInt("Weight: "));
	}
	public void edit(UserInput in){
		System.out.println();
	}

    /**
     * Loading an assigment from file
     * @param conn
     * @param data
     * @throws SQLException
     */
	public static void loadAssignment(Connection conn, ArrayList<String> data) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.execute("INSERT INTO Assignments VALUES("+data.get(0)+",'"+data.get(1)+"',"+data.get(2)+","+data.get(3)+");");
		stmt.close();
	}
	//Pushing data to file
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
	public void setWeight(int weight) throws SQLException {
		this.weight=weight;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Assignments SET weight= "+weight+" WHERE internalId="+this.internalId+";");
		stmt.close();
	}

    /**
     *Sends control to display stuff
     */
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

	/**
	 * @param conn
	 * @param internalId
	 * @throws SQLException
	 */
	public static void delete(Connection conn, long internalId) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("DELETE FROM Assignments WHERE internalId="+internalId);
		stmt.close();
	}
	
}
