package primary;
/**
 * Author - Every group member
 * Main
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main_Test {
	//Using TutorialsPoint JDBC example for help
	//from their example
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/projectgrids";

	//  Database credentials
	private static final String USER = "root";
	private static final String PASS = "";
	
	/*private static void numRowsInAssignments(Connection conn) throws SQLException {
		 //print table size for assignments
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Assignments;");
		 rs.next();
		 System.out.println("Number of rows in Assignments table: "+rs.getLong("Count(*)"));
		 rs.close();
		 stmt.close();
	}*/

	/**
	 * Starts pulling things to start developing the database
	 * Develops the database
	 * @param args
	 */
	public static void main(String[] args) {

		// Testing data base connection and methods in assignment class
		System.out.println("running");

		
		Connection conn = null;
		try{
		   //STEP 2: Register JDBC driver
		   Class.forName("com.mysql.jdbc.Driver");

		   //STEP 3: Open a connection
		   System.out.println("Connecting to database...");
		   conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   //CsvUtil.dataLoad(conn, "dump/");
		  DisplayGrid dg = new DisplayGrid(conn, new Section(conn,1), 0);
		  dg.displayOnConsole();
		   conn.close();
		   
		} catch(SQLException sqle) {
			sqle.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Testing assignments
	 * @param a
	 * @param b
	 */
	private static void test(Assignment a, Assignment b) {
		System.out.println("A:");
		a.display();
		System.out.println("B:");
		b.display();
		if(a.equals(b)) {
			System.out.println("This program is working!");
		} else {
			System.out.println("Something went wrong ... this is awkard.");
		}
	}

}
