package primary;
/**
 * The backbone of the Project- the Main
 * Some variables used throughout the class-
 *                  *
 *
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;


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

            primaryStage.setTitle("Title");
            primaryStage.show();
            Group root = new Group();
            Scene scene = new Scene(root, 600, 330, Color.LIGHTGOLDENRODYELLOW);
        primaryStage.setScene(scene);


        GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(5));
            gridpane.setHgap(10);
            gridpane.setVgap(10);

            Label welcome = new Label("Welcome Commander Schenk");
            GridPane.setHalignment(welcome, HPos.CENTER);
            gridpane.add(welcome, 3, 3);


        final ComboBox periodComboBox = new ComboBox<>();
        periodComboBox.getItems().addAll(
                "period 1",
                "period 2",
                "period 3",
                "period 4",
                "period 5",
                "period 6",
                "master list"
        );

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

            if(selectedPeriod.equals("period 1")) {
                primaryStage.hide();
                secondaryStage.show();
                secondaryStage.setTitle("Period 1");
                Group root0 = new Group();
                Scene period1 = new Scene(root0,600, 330, Color.LIGHTBLUE);
                secondaryStage.setScene(period1);
                GridPane gridpane1 = new GridPane();
                gridpane1.add(new Label("period 1 roster"), 3, 3);
            }

            if(selectedPeriod.equals("period 2")) {
                primaryStage.hide();
                trimaryStage.show();
                trimaryStage.setTitle("Period 2");
                Group root1 = new Group();
                Scene period2 = new Scene(root1,600, 330, Color.LEMONCHIFFON);
                trimaryStage.setScene(period2);
                GridPane gridpane2 = new GridPane();
                gridpane2.add(new Label("period 2 roster"), 3, 3);
            }

            if(selectedPeriod.equals("period 3")) {
                primaryStage.hide();
                fourmaryStage.show();
                fourmaryStage.setTitle("Period 3");
                Group root2 = new Group();
                Scene period3 = new Scene(root2,600, 330, Color.PLUM);
                fourmaryStage.setScene(period3);
                GridPane gridpane3 = new GridPane();
                gridpane3.add(new Label("period 3 roster"), 3, 3);
            }

            if(selectedPeriod.equals("period 4")) {
                primaryStage.hide();
                fivemaryStage.show();
                fivemaryStage.setTitle("Period 4");
                Group root3 = new Group();
                Scene period4 = new Scene(root3,600, 330, Color.THISTLE);
                fivemaryStage.setScene(period4);
                GridPane gridpane4 = new GridPane();
                gridpane4.add(new Label("period 4 roster"), 3, 3);
            }

            if(selectedPeriod.equals("period 5")) {
                primaryStage.hide();
                sixmaryStage.show();
                sixmaryStage.setTitle("Period 5");
                Group root4 = new Group();
                Scene period5 = new Scene(root4,600, 330, Color.FIREBRICK);
                sixmaryStage.setScene(period5);
                GridPane gridpane5 = new GridPane();
                gridpane5.add(new Label("period 5 roster"), 3, 3);
            }

            if(selectedPeriod.equals("period 6")) {
                primaryStage.hide();
                sevenmaryStage.show();
                sevenmaryStage.setTitle("Period 6");
                Group root5 = new Group();
                Scene period6 = new Scene(root5,600, 330, Color.DARKORCHID);
                secondaryStage.setScene(period6);
                GridPane gridpane6 = new GridPane();
                gridpane6.add(new Label("period 6 roster"), 3, 3);
            }

            if(selectedPeriod.equals("master list")) {
                primaryStage.hide();
                eightmaryStage.show();
                eightmaryStage.setTitle("master list");
                Group root6 = new Group();
                Scene period7 = new Scene(root6,600, 330, Color.CORAL);
                eightmaryStage.setScene(period7);
                GridPane gridpane8 = new GridPane();
                gridpane8.add(new Label("master list"), 3, 3);
            }
            //button for back to main / primary stage!!
           // selectedPeriod = (String) periodComboBox.getValue();
        });


    }


        public static void main(String[] args) {
        launch(args);
    }
}
