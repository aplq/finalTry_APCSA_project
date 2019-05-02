package primary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io;

public class Main_Console {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/projectgrids";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";
    public static final UserInput in = new UserInput();
    public static void Main(String[] args){
        Connection conn = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e){
            System.out.println("Unable to reach database");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException a){
                System.out.println("my sleep has been interupted");
            }
        } catch (ClassNotFoundException a){
            System.out.println("Unable to reach database");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException z){
                System.out.println("my sleep has been interupted");
            }
        }
        System.out.print("Welcome To Project Grids Console edition version 1.0");
        Main_Console.mainMenue(conn);
    }
    public static void mainMenue(Connection conn){
        boolean keepRunnring = true;
        while(keepRunnring){
            System.out.println("Please select an action to continue:");
            System.out.println("1: Select a Section and Grid to display");
            System.out.println("2: Create Section");
            System.out.println("3: Manage Sections");
            System.out.println("4: Create New Grid Template");
            System.out.println("5: Quit");
            String usrIn="";
            try {
                switch (in.getInt("Your Selection: ", "Please enter the number next to the action you would like to takae: ")) {
                    case 1:
                        ArrayList<Section> activeSections = SqlUtil.getActiveSections(conn);
                        Section choice = activeSections.get(Main_Console.selectSection(activeSections));
                        DisplayGrid grid = new DisplayGrid(conn, choice, Main_Console.selectGtidTemplate(choice.getGrids()));
                        grid.displayOnConsole();
                        while(!usrIn.equals("return")){
                            usrIn=in.getString("Your options: \"return\", \"edit\": ");
                            if(usrIn.equals("edit")){
                                grid.setGrade(Main_Console.selectStudent(grid.getSection().getStudents()),Main_Console.selectAssignment(grid.getTemplate().getAssignments()),(short)in.getInt("What is the new Grade: "));
                            }
                        }
                        break;
                    case 2:
                        new Section(conn,
                                in.getString("Name of Section: "),
                                in.getString("Name of Teacher: "));
                        break;
                    //case 3:

                }
            } catch(SQLException e){
                System.out.println("There was an ishue processing your request.\n Please try again.");
            }
        }
    }
    public static int selectSection(ArrayList<Section> sections){
        System.out.println("Please Select a Section:");
        for(int s=0; s<sections.size(); s++){
            System.out.println(s+":\t"+sections.get(s).getName()+" - "+sections.get(s).getTeacher());
        }
        return in.getInt("Your Selection: ");
    }
    public static int selectGtidTemplate(ArrayList<GridTemplate> gts){
        System.out.println("Please Select a Grid Template:");
        for(int gt=0; gt<gts.size(); gt++){
            System.out.println(gt+":\t"+gts.get(gt).getName());
        }
        return in.getInt("Your Selection: ");
    }
    public static int selectStudent(ArrayList<Student> students){
        System.out.println("Please Select a Student:");
        for(int stu=0; stu<students.size(); stu++){
            System.out.println(stu+":\t"+students.get(stu).getFirstName()+" "+students.get(stu).getLastName());
        }
        return in.getInt("Your Selection: ");
    }
    public static int selectAssignment(ArrayList<Assignment> assignments){
        System.out.println("Please Select a Asignment:");
        for(int assign=0; assign<assignments.size(); assign++){
            System.out.println(assign+":\t"+assignments.get(assign).getDescription());
        }
        return in.getInt("Your Selection: ");
    }

    public static int addStudent(ArrayList<Student> students) throws IOException {

        BufferedReader input = new BufferedReader ( new InputStreamReader ( System.in ) );
        System.out.println("Enter the name of the student you want to remove-");
        String inputString = input.readLine();




    }

}
