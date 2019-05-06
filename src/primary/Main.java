//ALLISON FAIN
//COMMANDER SCHENK
//PERIOD 2
//MASTER PROJECT


package primary;

import javafx.application.Application;                                                                                  //imports
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";                                                          //sql util get active sections . get name
    private static final String DB_URL = "jdbc:mysql://localhost/projectgrids";                                         //this part doesn't frickin work and I'm annoyed

    private static final String USER = "root";                                                                          //Database credentials
    private static final String PASS = "";

    public static void main(String[] args) {                                                                            //basically kickstarts the method below
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        Connection conn = null;                                                                                         //apparently i get a list of possible sections from this crap
        try{                                                                                                            //but plot twist i don't

            Class.forName("com.mysql.jdbc.Driver");                                                                     //STEP 2: Register JDBC driver

            System.out.println("Connecting to database...");                                                            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Section ection;

            DisplayGrid dg = new DisplayGrid(conn, new Section(conn,1), 0);                     //supposedly plugs in the correct parameters to the Displaygrid method
            dg.displayOnConsole();
        }
        catch (ClassNotFoundException ex) {                                                                             //catches errors
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Stage secondaryStage = new Stage();                                                                             //period 1
        Stage trimaryStage = new Stage();                                                                               //period 2
        Stage fourmaryStage = new Stage();                                                                              //period 3
        Stage fivemaryStage = new Stage();                                                                              //period 4
        Stage sixmaryStage = new Stage();                                                                               //period 5
        Stage sevenmaryStage = new Stage();                                                                             //period 6
        Stage eightmaryStage = new Stage();                                                                             //master list

        final ComboBox semester = new ComboBox<>();                                                                     //dropdown menu for semester selection for all stages
        semester.getItems().add("spring");
        semester.getItems().add("fall");

        primaryStage.setTitle("Title");
        primaryStage.show();
        Group root = new Group();
        Scene scene = new Scene(root, 600, 330, Color.ALICEBLUE);                                          //sets up a blank screen
        primaryStage.setScene(scene);

        GridPane gridpane = new GridPane();                                                                             //basic formatting for the gridpane
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        Label welcome = new Label("Welcome Commander Schenk");                                                     //basically a title
        GridPane.setHalignment(welcome, HPos.CENTER);
        gridpane.add(welcome, 3, 3);

        final ComboBox periodComboBox = new ComboBox<>();                                                               //fills in the dropdown menu with the different periods
        periodComboBox.getItems().add("period 1");
        periodComboBox.getItems().add("period 2");
        periodComboBox.getItems().add("period 3");
        periodComboBox.getItems().add("period 4");
        periodComboBox.getItems().add("period 5");
        periodComboBox.getItems().add("period 6");
        periodComboBox.getItems().add("master list");

        gridpane.add(new Label("Class period:"), 4, 5);                                         //reports the user input
        gridpane.add(periodComboBox, 5, 5);                                                          //puts the combobox in the right spot

        root.getChildren().add(gridpane);
        periodComboBox.setOnAction((e) -> {                                                                             //actually registers that input was recieved
            System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
            final String selectedPeriod = (String) periodComboBox.getSelectionModel().getSelectedItem();
            gridpane.add(new Label(selectedPeriod), 7, 3);

            Button back = new Button("Back");                                                                      //back button

            switch (selectedPeriod) {                                                                                   //switch statement
                case "period 1":                                                                                        //period 1
                    GridPane gridpane1 = new GridPane();                                                                //new grid
                    primaryStage.hide();                                                                                //gets rid of the original page
                    secondaryStage.show();                                                                              //puts up the period 1 page
                    secondaryStage.setTitle("Period 1");
                    Scene period1 = new Scene(gridpane1, 600, 330, Color.LIGHTBLUE);                       //assigns the new stage
                    secondaryStage.setScene(period1);

                    gridpane1.setPadding(new Insets(5));                                                //more basic formatting
                    gridpane1.setHgap(10);
                    gridpane1.setVgap(10);
                    gridpane1.add(new Label("period 1 roster"), 3, 3);                          //label bc we SHOULD see a roster but wouldn't you know, we don't...

                    gridpane1.add(back, 3, 7);                                                       //back button that
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        secondaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane1.add(semester, 4, 3);
                    semester.setOnAction((r) -> {                                                                       //picks the semester
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane1.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring") {                                                             //the part that breaks. love my life. idk whats wrong

                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "Spring period 1", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (selectedSemester == "fall") {                                                             //the part that breaks. love my life. idk whats wrong
                            try {
                                Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "fall period 1", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    break;

                case "period 2":                                                                                        //period 2
                    primaryStage.hide();
                    trimaryStage.show();
                    GridPane gridpane2 = new GridPane();
                    trimaryStage.setTitle("Period 2");
                    Scene period2 = new Scene(gridpane2, 600, 330, Color.LEMONCHIFFON);
                    trimaryStage.setScene(period2);

                    gridpane2.setPadding(new Insets(5));
                    gridpane2.setHgap(10);
                    gridpane2.setVgap(10);
                    gridpane2.add(new Label("period 2 roster"), 3, 3);

                    gridpane2.add(back, 3, 7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        trimaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane2.add(semester, 4, 3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane2.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring") {                                                             //the part that breaks. love my life. idk whats wrong

                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "Spring period 2", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (selectedSemester == "fall") {                                                             //the part that breaks. love my life. idk whats wrong
                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "fall period 2", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    });

                    break;

                case "period 3":
                    primaryStage.hide();
                    fourmaryStage.show();
                    GridPane gridpane3 = new GridPane();
                    fourmaryStage.setTitle("Period 3");
                    Group root2 = new Group();
                    Scene period3 = new Scene(gridpane3, 600, 330, Color.PLUM);
                    fourmaryStage.setScene(period3);

                    gridpane3.setPadding(new Insets(5));
                    gridpane3.setHgap(10);
                    gridpane3.setVgap(10);
                    gridpane3.add(new Label("period 3 roster"), 3, 3);

                    gridpane3.add(back, 3, 7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        fourmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane3.add(semester, 4, 3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane3.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring") {                                                             //the part that breaks. love my life. idk whats wrong

                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "Spring period 3", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (selectedSemester == "fall") {                                                             //the part that breaks. love my life. idk whats wrong
                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "fall period 3", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    });

                    break;

                case "period 4":
                    primaryStage.hide();
                    fivemaryStage.show();
                    GridPane gridpane4 = new GridPane();
                    fivemaryStage.setTitle("Period 4");
                    Scene period4 = new Scene(gridpane4, 600, 330, Color.THISTLE);
                    fivemaryStage.setScene(period4);

                    gridpane4.setPadding(new Insets(5));
                    gridpane4.setHgap(10);
                    gridpane4.setVgap(10);
                    gridpane4.add(new Label("period 4 roster"), 3, 3);

                    gridpane4.add(back, 3, 7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        fivemaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane4.add(semester, 4, 3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane4.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring") {                                                             //the part that breaks. love my life. idk whats wrong

                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "Spring period 4", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (selectedSemester == "fall") {                                                             //the part that breaks. love my life. idk whats wrong
                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "fall period 4", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    });

                    break;

                case "period 5":
                    primaryStage.hide();
                    sixmaryStage.show();
                    GridPane gridpane5 = new GridPane();
                    sixmaryStage.setTitle("Period 5");
                    Scene period5 = new Scene(gridpane5, 600, 330, Color.FIREBRICK);
                    sixmaryStage.setScene(period5);

                    gridpane5.setPadding(new Insets(5));
                    gridpane5.setHgap(10);
                    gridpane5.setVgap(10);
                    gridpane5.add(new Label("period 5 roster"), 3, 3);

                    gridpane5.add(back, 3, 7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        sixmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane5.add(semester, 4, 3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane5.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring") {                                                             //the part that breaks. love my life. idk whats wrong

                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "Spring period 5", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (selectedSemester == "fall") {                                                             //the part that breaks. love my life. idk whats wrong
                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "fall period 5", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    });

                    break;

                case "period 6":
                    primaryStage.hide();
                    sevenmaryStage.show();
                    GridPane gridpane6 = new GridPane();
                    sevenmaryStage.setTitle("Period 6");
                    Group root5 = new Group();
                    Scene period6 = new Scene(gridpane6, 600, 330, Color.DARKORCHID);
                    secondaryStage.setScene(period6);

                    gridpane6.setPadding(new Insets(5));
                    gridpane6.setHgap(10);
                    gridpane6.setVgap(10);
                    gridpane6.add(new Label("period 6 roster"), 3, 3);

                    gridpane6.add(back, 3, 7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        sevenmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane6.add(semester, 4, 3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane6.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring") {                                                             //the part that breaks. love my life. idk whats wrong

                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "Spring period 6", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (selectedSemester == "fall") {                                                             //the part that breaks. love my life. idk whats wrong
                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "fall period 6", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    });

                    break;

                case "master list":
                    primaryStage.hide();
                    eightmaryStage.show();
                    GridPane gridpane7 = new GridPane();
                    eightmaryStage.setTitle("master list");
                    Scene period7 = new Scene(gridpane7, 600, 330, Color.CORAL);
                    eightmaryStage.setScene(period7);

                    gridpane7.setPadding(new Insets(5));
                    gridpane7.setHgap(10);
                    gridpane7.setVgap(10);
                    gridpane7.add(new Label("master list roster"), 3, 3);

                    gridpane7.add(back, 3, 7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        eightmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane7.add(semester, 4, 3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane7.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring") {                                                             //the part that breaks. love my life. idk whats wrong

                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "Spring master list", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                        if (selectedSemester == "fall") {                                                             //the part that breaks. love my life. idk whats wrong
                            try {
                               Section ection = new Section(DriverManager.getConnection(DB_URL, USER, PASS), "fall master list", "Schenk");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                new DisplayGrid(DriverManager.getConnection(DB_URL, USER, PASS), ection, 34);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                    });

                    break;
            }
            //button for back to main / primary stage!!
            // selectedPeriod = (String) periodComboBox.getValue();
        });

      //  conn.close;
    }
}
