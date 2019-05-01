package primary;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CsvUtil {
    public static void dataLoad(Connection conn, String dir) throws SQLException {

        /**
         * Different ArrayList like assignmentData, gradeData, gridTempelateData, sectionData, and
         * studentData read from the CSV to copy into SQL database
         */

        ArrayList<ArrayList<String>> assignementData = CsvUtil.readFile(dir+"assignments.csv");
        ArrayList<ArrayList<String>> gradeData = CsvUtil.readFile(dir+"grades.csv");
        ArrayList<ArrayList<String>> gridTemplateData = CsvUtil.readFile(dir+"gridTemplates.csv");
        ArrayList<ArrayList<String>> sectionData = CsvUtil.readFile(dir+"sections.csv");
        ArrayList<ArrayList<String>> studentData = CsvUtil.readFile(dir+"students.csv");
        /**
         * Calling SQL commands across different classes to load data into database
         */
        for(ArrayList<String> row: assignementData){
            Assignment.loadAssignment(conn, row);
        }
        for(ArrayList<String> row: gridTemplateData){
            //GridTemplate.loadAssignment(conn, row);
        }
        for(ArrayList<String> row: studentData){
            Student.loadAssignment(conn, row);
        }
        for(ArrayList<String> row: gradeData){
            //GradeSet.loadAssignment(conn, row);
        }
        for(ArrayList<String> row: sectionData){
            //Section.loadAssignment(conn, row);
        }
    }

    /**
     * Used to read filePath
     * @param filePath
     * @return
     */
    public static ArrayList<ArrayList<String>> readFile(String filePath) {
        ArrayList<ArrayList<String>> data;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Integer lineNum=0;
            data = new ArrayList<ArrayList<String>>();
            while((line=br.readLine())!=null){
                //parse the line
                data.add(new ArrayList<String>());
                for(int colNum=0; line.indexOf(',')!=-1; colNum++){
                    data.get(lineNum).add(line.substring(0,line.indexOf(',')));
                    line=line.substring(line.indexOf(','),line.length());
                }
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
