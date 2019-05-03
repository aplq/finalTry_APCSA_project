package tableviewfxmlexample;

//Arthur Nikhil Uppal 

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


        //Create a EditTable class that will represtent data

        //Define data in an Observable list and add data as you want to show inside table
        final ObservableList<EditTable> data = FXCollections.observableArrayList(
                new EditTable("1", "Schenk", "schenk@fultonschools.org"),
                new EditTable("2", "Shreyans","shreyanssaragoni@gmail.com"),
                new EditTable("3", "Ethan", "27", "ethan@gmail.com"),
                new EditTable("4", "Allie", "28", "allisonfail08@gmail.com"),
                new EditTable("5", "Nikhil", "29", "nikhileuppal@gmail.com"),
                );


        //Associate data with columns
        id.setCellValueFactory(new PropertyValueFactory<EditTable,String>("id"));

        name.setCellValueFactory(new PropertyValueFactory<EditTable,String>("name"));

        age.setCellValueFactory(new PropertyValueFactory<EditTable,String>("age"));

        primary.setCellValueFactory(new PropertyValueFactory<EditTable,String>("primary"));

        secondry.setCellValueFactory(new PropertyValueFactory<EditTable,String>("secondry"));



        //Add data inside table
        myTable.setItems(data);





    }

}

