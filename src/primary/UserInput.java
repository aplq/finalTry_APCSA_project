package primary;
/*Ethan Brinser
 *November 11th 2018
 *AP CSA Student
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//a class to deal with user input
//Molded after the cs50 library
public class UserInput {

    //buffered reader storage
    BufferedReader buffReader = null;

    //Default constructor
    public UserInput(){
        this.buffReader = new BufferedReader(new InputStreamReader(System.in));
    }
    //full
    public UserInput(BufferedReader buffReader) {
        this.buffReader=buffReader;
    }

    //getter and setter
    public BufferedReader getBuffReader() {
        return this.buffReader;
    }
    public void setBuffReader(BufferedReader buffReader) {
        this.buffReader=buffReader;
    }

    //the important methods
    //will use the method in all others
    public String getString(String prompt) {
        System.out.print(prompt);
        try {
            return this.buffReader.readLine();
        } catch (IOException e) {
            //Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    //int with a rePrompt option
    public int getInt(String prompt, String rePrompt) {
        String input=this.getString(prompt);
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException e) {
            //keep retrying until an input is received
            return this.getInt(rePrompt, rePrompt);
        }
    }
    //int with no provided reprompt
    //returns uses default "retry"
    public int getInt(String prompt) {
        return this.getInt(prompt, "Retry: ");
    }

    //double with a rePrompt option
    public double getDouble(String prompt, String rePrompt) {
        String input=this.getString(prompt);
        try {
            return Double.parseDouble(input);
        } catch(NumberFormatException e) {
            //keep retrying until an input is received
            return this.getDouble(rePrompt, rePrompt);
        }
    }
    //Double with no provided reprompt
    //returns uses default "retry"
    public double getDouble(String prompt) {
        return this.getDouble(prompt, "Retry: ");
    }

    //boolean with a rePrompt option
    public Boolean getBoolean(String prompt, String rePrompt) {
        String input=this.getString(prompt);
        try {
            return Boolean.parseBoolean(input);
        } catch(NumberFormatException e) {
            //keep retrying until an input is received
            return this.getBoolean(rePrompt, rePrompt);
        }
    }
    //Boolean with no provided reprompt
    //returns uses default "retry"
    public Boolean getBoolean(String prompt) {
        return this.getBoolean(prompt, "Retry: ");
    }
}
