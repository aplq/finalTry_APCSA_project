package primary;

import javafx.application.Application;
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
import java.util.ArrayList;


public class Main_Combined extends Application {
    //Database locations
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/projectgrids";

    //Database credentials- username and password
    private static final String USER = "root";
    private static final String PASS = "";

    @Override
    public void start (Stage primaryStage){
        try {
        Connection conn = Main_Console.getConected();

        /*final ComboBox semester = new ComboBox<>();
        semester.getItems().add("spring");
        semester.getItems().add("fall");*/

        primaryStage.setTitle("Title");
        primaryStage.show();
        Group root = new Group();
        Scene scene = new Scene(root, 600, 330, Color.ALICEBLUE);
        primaryStage.setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        Label welcome = new Label("Welcome Commander Schenk");
        GridPane.setHalignment(welcome, HPos.CENTER);
        gridpane.add(welcome, 3, 3);

        final ArrayList<Section> activeSections = SqlUtil.getActiveSections(conn);

        final ComboBox periodComboBox = new ComboBox<>();
        for(Section sec: activeSections){
            periodComboBox.getItems().add(sec.getName());
        }

        gridpane.add(new Label("Class period:"), 4, 5);
        gridpane.add(periodComboBox, 5, 5);

        //String selectedPeriod = null;
        gridpane.add(new Label("oof"), 7, 7);
        // final String selectedPeriod;
        root.getChildren().add(gridpane);
        periodComboBox.setOnAction((e) -> {
            System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
            final String selectedPeriod = (String) periodComboBox.getSelectionModel().getSelectedItem();
            gridpane.add(new Label(selectedPeriod), 7, 3);

            Button back = new Button("Back");
            for(Section sec: activeSections){
                if(selectedPeriod.equals(sec.getName())){
                    GridPane gridpane1 = new GridPane();
                    //gridpane.getChildren().clear();
                    Stage secondaryStage = new Stage(); //period 1

                    primaryStage.hide();
                    secondaryStage.show();
                    secondaryStage.setTitle("Period 1");
                    Scene period1 = new Scene(gridpane1, 600, 330, Color.LIGHTBLUE);
                    secondaryStage.setScene(period1);

                    gridpane1.setPadding(new Insets(5));
                    gridpane1.setHgap(10);
                    gridpane1.setVgap(10);
                    gridpane1.add(new Label("period 1 roster"), 3, 3);

                    gridpane1.add(back, 3,7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        secondaryStage.hide();
                        primaryStage.show();
                    });

                    /*gridpane1.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane1.add(new Label(selectedSemester + " roster"), 3, 4);

                        if (selectedSemester == "spring"){
                            //GridTemplate( , 239494342);
                        }
                    });*/
                }
            }
            //button for back to main / primary stage!!
            // selectedPeriod = (String) periodComboBox.getValue();
        });
        } catch(SQLException e){
            System.out.println("Failed");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
