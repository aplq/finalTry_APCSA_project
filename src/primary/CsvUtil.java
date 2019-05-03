package primary;
/**
 * Purpose- to read CSV files and transport them into the database
 * Author - Shreyans Saraogi
 * AP CSA A final project
 */

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.*;


public class CsvUtil {

    /**
     * Loading the data
     * @param conn
     * @param dir
     * @throws SQLException
     */
    public static void dataLoad(Connection conn, String dir) throws SQLException {

        /**
         * Different ArrayList like assignmentData, gradeData, gridTempelateData, sectionData, and
         * studentData read from the CSV to copy into SQL database
         */

        ArrayList<ArrayList<String>> assignementData;
        ArrayList<ArrayList<String>> gradeData;
        ArrayList<ArrayList<String>> gridTemplateData;
        ArrayList<ArrayList<String>> sectionData;
        ArrayList<ArrayList<String>> studentData;



        /**
         * Calling SQL commands across different classes to load data into database
         */


        for(ArrayList<String> row: CsvUtil.readFile(dir+"assignments.csv")){


            Assignment.loadAssignment(conn, row);
        }
        for(ArrayList<String> row: CsvUtil.readFile(dir+"gridtemplate.csv")){

            GridTemplate.addAssignmentCsv(conn, row );

        }
        for(ArrayList<String> row: CsvUtil.readFile(dir+"students.csv")){

            Student.loadAssignment(conn, row);
        }
        for(ArrayList<String> row: CsvUtil.readFile(dir+"grades.csv")) {
            GradeSet.loadAssignment(conn, row);
        }
        for(ArrayList<String> row: CsvUtil.readFile(dir+"section.csv")){
            new Section(conn, row);
        }
    }

    /**
     * Used to read filePath
     * @param filePath
     * @return
     */
    public static ArrayList<ArrayList<String>> readFile(String filePath) {
        ArrayList<ArrayList<String>> data;
        ArrayList<String> temp2;
        String[] temp;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Integer lineNum=0;
            data = new ArrayList<ArrayList<String>>();
            while((line=br.readLine())!=null){
                //parse the line
                temp2= new ArrayList<String>();
                temp=line.split(",");
                for(String str : temp){
                    temp2.add(str);
                }
                data.add(temp2);
            }
            return data;
        } catch (IOException a){
            return new ArrayList<ArrayList<String>>();
        }
    }

    /**
     * Used to write through filePath
     * @param filePath
     * @param contents
     * @return
     */
    public static boolean writeFile(String filePath, ArrayList<ArrayList<String>> contents){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(ArrayList<String> row: contents){
                for(String str: row){
                    bw.write(str);
                    bw.write(',');
                }
                bw.write('\n');
            }
            return true;
        } catch(IOException a){
            return false;
        }
    }

    public static void addTOSQL()throws SQLException {


    }

}
