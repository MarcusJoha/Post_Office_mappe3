package edu.idatt2001.marcusjohannessen;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import java.io.File;
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
    @FXML
    private Menu about;


    //PostOfficeRegister postOfficeRegister = new PostOfficeRegister();
    Filehandler filehandler = new Filehandler();

    /**
     * Initializes when the application is started.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources){
        File filePath = new File("src/main/resources/edu/idatt2001/marcusjohannessen/storage/register.txt");
        setCellProperty();
        try {
            filehandler.readFromFile(filePath);
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

    @FXML
    public void handleLoadFromFile(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Load from file");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", ".csv"),
                new FileChooser.ExtensionFilter("TXT", ".txt")
        );
        File file = fc.showOpenDialog(mainBorderPane.getScene().getWindow());

        try{
            filehandler.readFromFile(file);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    @FXML
    public void handleInformationButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Litt informasjon, fikser senere");
        alert.show();
    }
}
