package primary;
/**
 * The backbone of the Project- the Main
 * Some variables used throughout the class-
 *                  *
 *
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import java.lang.String;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
//import sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums;
//     sql util get active sections . get name

public class Main extends Application {

    @Override
        public void start (Stage primaryStage){

            Stage secondaryStage = new Stage(); //period 1
            Stage trimaryStage = new Stage();   //period 2
            Stage fourmaryStage = new Stage();  //period 3
            Stage fivemaryStage = new Stage();  //period 4
            Stage sixmaryStage = new Stage();   //period 5
            Stage sevenmaryStage = new Stage(); //period 6
            Stage eightmaryStage = new Stage(); //master list

            final ComboBox semester = new ComboBox<>();
            semester.getItems().add("spring");
            semester.getItems().add("fall");

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

        final ComboBox periodComboBox = new ComboBox<>();
        periodComboBox.getItems().add("period 1");
        periodComboBox.getItems().add("period 2");
        periodComboBox.getItems().add("period 3");
        periodComboBox.getItems().add("period 4");
        periodComboBox.getItems().add("period 5");
        periodComboBox.getItems().add("period 6");
        periodComboBox.getItems().add("master list");

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

                     switch(selectedPeriod) {
                case "period 1":
                    GridPane gridpane1 = new GridPane();
                    //gridpane.getChildren().clear();
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

                    gridpane1.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane1.add(new Label(selectedSemester + " roster"), 3, 4);

                    if (selectedSemester == "spring"){
                        //GridTemplate( , 239494342);
                    }
                    });
                    break;

                case "period 2":
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

                    gridpane2.add(back, 3,7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        trimaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane2.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane2.add(new Label(selectedSemester + " roster"), 3, 4);
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

                    gridpane3.add(back, 3,7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        fourmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane3.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane3.add(new Label(selectedSemester + " roster"), 3, 4);
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

                    gridpane4.add(back, 3,7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        fivemaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane4.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane4.add(new Label(selectedSemester + " roster"), 3, 4);
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

                    gridpane5.add(back, 3,7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        sixmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane5.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane5.add(new Label(selectedSemester + " roster"), 3, 4);
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

                    gridpane6.add(back, 3,7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        sevenmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane6.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane6.add(new Label(selectedSemester + " roster"), 3, 4);
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

                    gridpane7.add(back, 3,7);
                    back.setOnAction((EventHandler) event -> {
                        // instructions executed when the button is clicked
                        eightmaryStage.hide();
                        primaryStage.show();
                    });

                    gridpane7.add(semester, 4,3);
                    semester.setOnAction((r) -> {
                        System.out.println(periodComboBox.getSelectionModel().getSelectedItem());
                        final String selectedSemester = (String) semester.getSelectionModel().getSelectedItem();
                        gridpane7.add(new Label(selectedSemester + " roster"), 3, 4);
                    });

                    break;
            }
            //button for back to main / primary stage!!
           // selectedPeriod = (String) periodComboBox.getValue();
        });
    }

        public static void main(String[] args) {
        launch(args);
    }
}
