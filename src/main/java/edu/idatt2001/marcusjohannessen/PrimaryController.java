package edu.idatt2001.marcusjohannessen;


import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {

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
    private TextField filterfield;
    @FXML
    private Label amountField;
    @FXML
    private Button refreshAmount;


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
        filterList();


    }

    private void filterList(){
        FilteredList<PostOffice> filteredList = new FilteredList<>(filehandler.getPostOffices(), f -> true );
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
        amountField.setText("Amount: " + sortedList.size());
    }

    private void setCellProperty() {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        municipalityCol.setCellValueFactory(new PropertyValueFactory<>("municipality"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    public void handleRefreshButton(){
        amountField.setText("Amount: " + String.valueOf(tableView.getItems().size()));
    }

    @FXML
    public void handleSave(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Save File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", ".csv"),
                new FileChooser.ExtensionFilter("TXT", ".txt")
        );
        //Opens where the main window is
        //User can not interact with Application before
        //file chooser window is closed
        File file = fc.showSaveDialog(mainBorderPane.getScene().getWindow());
        try {
            filehandler.saveToFile(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
