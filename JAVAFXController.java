package tableviewfxmlexample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TableView myTable;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn id = new TableColumn("ID");
        TableColumn name = new TableColumn("NAME");
        TableColumn age = new TableColumn("AGE");
        TableColumn email = new TableColumn("EAMIL");

        TableColumn primary = new TableColumn("PRIMARY");
        TableColumn secondry = new TableColumn("SECONDRY");

        email.getColumns().addAll(primary, secondry);


        myTable.getColumns().addAll(id, name, age, email);


        //Step : 1# Create a person class that will represtent data

        //Step : 2# Define data in an Observable list and add data as you want to show inside table
        final ObservableList<EditCell> data = FXCollections.observableArrayList(
                new Person("1", "Jacob", "24", "", "jacob.smith@example.com", "jacob.smith@example.com"),
                new Person("2","Isabella", "25", "","isabella.johnson@example.com", "jacob.smith@example.com"),
                new Person("3","Ethan", "27","" ,"ethan.williams@example.com", "jacob.smith@example.com"),
                new Person("4","Emma", "28","" ,"emma.jones@example.com", "jacob.smith@example.com"),
                new Person("5","Michael", "29", "" ,"michael.brown@example.com", "jacob.smith@example.com"),
                new Person("5","Michael", "29", "","michael.brown@example.com", "jacob.smith@example.com")   );


        //Step : 3#  Associate data with columns
        id.setCellValueFactory(new PropertyValueFactory<EditCell,String>("id"));

        name.setCellValueFactory(new PropertyValueFactory<EditCell,String>("name"));

        age.setCellValueFactory(new PropertyValueFactory<EditCell,String>("age"));

        primary.setCellValueFactory(new PropertyValueFactory<EditCell,String>("primary"));

        secondry.setCellValueFactory(new PropertyValueFactory<EditCell,String>("secondry"));



        //Step 4: add data inside table
        myTable.setItems(data);





    }

}
