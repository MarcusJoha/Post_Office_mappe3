package edu.idatt2001.marcusjohannessen;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class PrimaryController {

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
    private Label amount;


    //PostOfficeRegister postOfficeRegister = new PostOfficeRegister();
    Filehandler filehandler = new Filehandler();

    /**
     * Initializes when the application is started.
     */

    @FXML
    public void initialize(){
        setCellProperty();
        try {
            filehandler.readFromFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        FilteredList<PostOffice> filteredList = new FilteredList<>(filehandler.getPostOffices(), f -> true );

        //Filters tableview based on zipcode and municipality
        filterfield.textProperty().addListener((observableValue, obVal, newVal) -> {
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
        //
        tableView.setItems(sortedList);

        //ToDo: få denne til å forandre seg.
        amount.setText("Amount: " + sortedList.size());
    }

    private void setCellProperty() {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        municipalityCol.setCellValueFactory(new PropertyValueFactory<>("municipality"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    public void setAmount(){
        amount.setText("Amount: " + tableView.getItems().size());

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
