package edu.idatt2001.marcusjohannessen;

import edu.idatt2001.marcusjohannessen.office.PostOffice;
import edu.idatt2001.marcusjohannessen.office.PostOfficeRegister;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class PrimaryController {


    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableView<PostOffice> tableView;
    @FXML
    private TableColumn<String, PostOffice> zipCodeCol;
    @FXML
    private TableColumn<String, PostOffice> municipalityCol;
    @FXML
    private TableColumn<String, PostOffice> cityCol;
    @FXML
    private VBox leftVbox;
    @FXML
    private TextField zipCodeInput;
    @FXML
    private TextField municipalityInput;

    PostOfficeRegister postOfficeRegister = new PostOfficeRegister();

    /**
     * Initializes when the application is started.
     */

    public void initialize(){
        setCellProperty();
    }

    private void setCellProperty(){
        zipCodeCol.setCellValueFactory(new PropertyValueFactory<>("zip code"));
        municipalityCol.setCellValueFactory(new PropertyValueFactory<>("municipality"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
    }


}
