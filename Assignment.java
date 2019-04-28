package primary;
/* This class is to hold a assignment
 * the sql should work hopefully
 * author Ethan Brinser
 * 22 March 2019
 */
import java.sql.*;

public class Assignment {
	private long internalId;
	private String description;
	private short type;	//0=normal, 1=quiz, 2=test
    private int weight;
	//load assignment from sql database
	public Assignment(Connection conn, long internalId) throws SQLException {
		super();
		//set internal id
		this.internalId=internalId;
		//run the sql
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Assignments WHERE internalId="+this.internalId+";");
		//Handle the results and fill out the fields
		rs.next();
		this.description=rs.getString("description");
		this.type=rs.getShort("type");
		this.weight=rs.getInt("weight");
		stmt.close();
	}
	//creating a new assignment
	public Assignment(Connection conn, String description, short type, int weight) throws SQLException {
		super();
		//set the fields
		this.description=description;
		this.type=type;
		this.weight=weight;
		//get the next internalId
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Assignments");
		rs.next();
		this.internalId=rs.getLong("Count(*)");
		rs.close();
		stmt.close();
		//load the rest of the data in to the data base
		stmt = conn.createStatement();
		stmt.execute("INSERT INTO Assignments VALUES("+this.internalId+",'"+this.description+"',"+this.weight+","+this.type+");");
		stmt.close();
		
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
	
	//all the setters
	//they set the local value and the data base value
	public void setDescription(Connection conn, String description) throws SQLException {
		this.description = description;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Assignments SET description= '"+description+"' WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	public void setType(Connection conn, short type) throws SQLException {
		this.type=type;
		Statement stmt = conn.createStatement();
		stmt.execute("UPDATE Assignments SET type= "+type+" WHERE internalId="+this.internalId+";");
		stmt.close();
	}

	//debug display
    public void display(){
	    System.out.println("intenalId: "+this.internalId);
        System.out.println("description: "+this.description);
        System.out.println("Weight: "+this.weight);
        System.out.println("Type: "+this.type);
    }
	
	//delete function
	public static void delete(Connection conn, long internalId) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute("DELETE FROM Assignments WHERE internalId="+internalId);
		stmt.close();
	}
	
}
