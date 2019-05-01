package primary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* This class is to hold a grid to be displaded
 * the fx still needs to be added in this class
 * or another class
 * author Ethan Brinser
 * 22 March 2019
 */
public class DisplayGrid{
	private Connection conn;
	private Section section;
	private short[][] grades;
	private GridTemplate template;
	public static int maxNameLen = 25;

	/**
	 *
	 * @param conn
	 * @param section
	 * @param sectionGridNumber
	 * @throws SQLException
	 */

	public DisplayGrid(Connection conn, Section section, int sectionGridNumber) throws SQLException {
		this.conn=conn;
		this.section=section;
		this.template=new GridTemplate(conn, section.getGrids().get(sectionGridNumber).getInternalId());
		//get the grades in order
		System.out.println(section.getStudents().size()+", "+this.template.getAssignments().size());
		this.grades=new short[section.getStudents().size()][this.template.getAssignments().size()];
		for(int row=0; row<section.getStudents().size(); row++) {
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery("SELECT * FROM Grades WHERE "+
						"gridTemplateId = "+this.template.getInternalId()+" AND "+
						"stuId = "+section.getStudents().get(row).getInternalId()+";");
			//go through the results
			rs.next();
			for(int col=0; col<this.template.getAssignments().size();col++) {
				this.grades[row][col]=rs.getShort("grade"+col);
			}
		}
	}
	//getters
	public Section getSection() {
		return section;
	}
	public short[][] getGrades() {
		return grades;
	}
	
	//Used to set and change grades for a student
	public boolean setGrade(int rowIndex, int colIndex, short grade) {
		try {
			Statement sm = conn.createStatement();
			sm.executeUpdate("UPDATE Grades SET g"+colIndex+"="+grade+"WHERE gridTemplate="+this.template.getInternalId()+
					" AND student="+this.section.getStudents().get(rowIndex)+";");
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	//Main fucntion for diaplay grid
	public void printline(){
		System.out.print("\u251c");
		for(int a=0; a<DisplayGrid.maxNameLen; a++){
			System.out.print("\u2500");
		}
		for(int b=0; b<this.grades[0].length; b++){
			System.out.print("\u253c\u2500\u2500");
		}
		System.out.println("\u2524");
	}

	//Main function for printing spaces
	public void printSpaces(int num){
		for(int a=0; a<num; a++){
			System.out.print(" ");
		}
	}

	//Displays the main console
	public void displayOnConsole(){
		this.printline();
		//print the circles
		for(int a=2; a>=1; a--) {
			System.out.print("\u2502");
			this.printSpaces(DisplayGrid.maxNameLen);
			for(int b=0; b<this.template.numOfAssignments();b++){
				System.out.print("\u2502");
				if(this.template.getAssignments().get(b).getType()>=a){
					System.out.print("\u25cf ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println("\u2502");
			//this.printline();
		}

		// Printing assignement numbers.

		String temp;
		System.out.print("\u2502Name /  Assignment Number");
		for(int c=0; c<this.template.numOfAssignments(); c++){
			System.out.print("\u2502");
			temp=Integer.toString(c);
			if(temp.length()==1){
				temp=" "+temp;
			}
			System.out.print(temp);
		}
		System.out.println("\u2502");
		this.printline();

		//Printing names and grades
		for(int d=0; d<this.grades.length; d++) {
			System.out.print("\u2502");
			temp = this.section.getStudents().get(d).firstAndLastInitial();
			if(temp.length()<DisplayGrid.maxNameLen){
				//pad with zeros
				System.out.print(temp);
				this.printSpaces(DisplayGrid.maxNameLen-temp.length());
			} else if (temp.length()>DisplayGrid.maxNameLen){
				//truncate
				System.out.print(temp.substring(0,DisplayGrid.maxNameLen));
			} else {
				System.out.print(temp);
			}
			/**
			 * Printing the grades using different colors shwoing the Standard MAstery framework colors assigned
			 * 			at differnt stages
			 */

			for(int e=0; e<this.grades[d].length; e++){
				System.out.print("\u2502");
				switch(this.grades[d][e]){
					case 0:
						System.out.print(" ");
						break;
					case 1: 											//blue[34m red[31m silver[37m gold[33m
						System.out.print("\u001B[34m\u2605\u001B[0m");
						break;
					case 2: 											//blue[34m red[31m silver[37m gold[33m
						System.out.print("\u001B[31m\u2605\u001B[0m");
						break;
					case 3:												//blue[34m red[31m silver[37m gold[33m
						System.out.print("\u001B[37m\u2605\u001B[0m");
						break;
					case 4: 											//blue[34m red[31m silver[37m gold[33m
						System.out.print("\u001B[33m\u2605\u001B[0m");
						break;
				}
				System.out.print(" ");
			}
			System.out.println("\u2502");
			this.printline();
		}
	}
	
}
