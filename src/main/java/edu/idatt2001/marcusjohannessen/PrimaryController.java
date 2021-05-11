package edu.idatt2001.marcusjohannessen;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TableView<PostOffice> tableView;
    @FXML
    private TableColumn<PostOffice, String> codeCol;
    @FXML
    private TableColumn<PostOffice, String> municipalityCol;
    @FXML
    private TableColumn<PostOffice, String> cityCol;
    @FXML
    private TextField filterfield;
    @FXML
    private Label amountField;
    @FXML
    private VBox slider;
    @FXML
    private Label menu;

    @FXML
    private Label menuBack;





    //PostOfficeRegister postOfficeRegister = new PostOfficeRegister();
    Filehandler filehandler = new Filehandler();

    /**
     * Initializes when the application is started.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources){
        setCellProperty();
        try {
            filehandler.readFromFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        FilteredList<PostOffice> filteredList = new FilteredList<>(filehandler.getPostOffices(), f -> true );

        //Filters tableview based on zipcode and municipality
        filterfield.textProperty().addListener((observableValue, oldVal, newVal) -> {
            filteredList.setPredicate(PostOffice -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newVal.toLowerCase();
                if (PostOffice.getZipCode().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else return PostOffice.getMunicipality().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<PostOffice> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }


    //TODO: Er ikke viktig, sparer den til senere
    private void getSizeOfTableview(){

    }

    private void setCellProperty() {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        municipalityCol.setCellValueFactory(new PropertyValueFactory<>("municipality"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    //TODO: tableview oppdaterer seg mens brukeren skriver
    //Når man trykker enter søker man
    public void filterByZipCode(){

    }


    //TODO: tableview oppdaterer seg mens brukeren skriver
    //Når man trykker enter søker man
    public void filterByMunicipality(){

    }

}
