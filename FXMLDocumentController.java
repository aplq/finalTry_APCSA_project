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
        TableColumn email = new TableColumn("EMAIL");

        TableColumn primary = new TableColumn("PRIMARY");
        TableColumn secondry = new TableColumn("SECONDRY");

        email.getColumns().addAll(primary, secondry);


        myTable.getColumns().addAll(id, name, email);


        //Create a person class that will represtent data

        //Define data in an Observable list and add data as you want to show inside table
        final ObservableList<Person> data = FXCollections.observableArrayList(
                new Person("1", "Schenk", "schenk@fultonschools.org"),
                new Person("2", "Shreyans","shreyanssaragoni@gmail.com"),
                new Person("3", "Ethan", "27", "ethan@gmail.com"),
                new Person("4", "Allie", "28", "allisonfail08@gmail.com"),
                new Person("5", "Nikhil", "29", "nikhileuppal@gmail.com"),
                );


        //Associate data with columns
        id.setCellValueFactory(new PropertyValueFactory<Person,String>("id"));

        name.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));

        age.setCellValueFactory(new PropertyValueFactory<Person,String>("age"));

        primary.setCellValueFactory(new PropertyValueFactory<Person,String>("primary"));

        secondry.setCellValueFactory(new PropertyValueFactory<Person,String>("secondry"));



        //Add data inside table
        myTable.setItems(data);





    }

}

