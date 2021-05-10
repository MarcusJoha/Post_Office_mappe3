package edu.idatt2001.marcusjohannessen;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TableColumn<PostOffice, String> codeCol;
    @FXML
    private TableColumn<PostOffice, String> municipalityCol;
    @FXML
    private TableColumn<PostOffice, String> cityCol;
    @FXML
    private VBox leftVbox;
    @FXML
    private TextField zipCodeInput;
    @FXML
    private TextField municipalityInput;
    @FXML
    private Button zipCodeSearch;
    @FXML
    private Button municipalitySearch;



    //PostOfficeRegister postOfficeRegister = new PostOfficeRegister();
    Filehandler filehandler = new Filehandler();

    /**
     * Initializes when the application is started.
     */

    public void initialize(){
        setCellProperty();
        codeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

        //fillTableviewContent();
    }

    private void setCellProperty() {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        municipalityCol.setCellValueFactory(new PropertyValueFactory<>("municipality"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    //TODO: skriver fra fil i Filehandler.
    //TODO: legger til i listen i PostOfficeRegister.
    //TODO:
    public void fillTableviewContent(){
        try{
            ObservableList<PostOffice> list = filehandler.readFromFile();
            tableView.setItems(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //TODO: tableview oppdaterer seg mens brukeren skriver
    //Når man trykker enter søker man
    public void searchByZipCode(){

    }


    //TODO: tableview oppdaterer seg mens brukeren skriver
    //Når man trykker enter søker man
    public void searchByMunicipiality(){

    }

}
