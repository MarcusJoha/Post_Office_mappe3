package edu.idatt2001.mappe3.marcusjohannessen;

import edu.idatt2001.mappe3.marcusjohannessen.data.Filehandler;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
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

    //PostOfficeRegister postOfficeRegister = new PostOfficeRegister();
    Filehandler filehandler = new Filehandler();

    /**
     * Initializes when the application is started.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Fills tableview with data from norwegian post offices when initializing application
        File filePath = new File("src/main/resources/edu/idatt2001/mappe3/marcusjohannessen/storage/register.txt");
        setCellProperty();
        try {
            filehandler.readFromFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filterList();
    }

    /**
     * Puts post offices in tableview that contains key word
     * from zip code or municipality.
     */
    private void filterList() {
        FilteredList<PostOffice> filteredList = new FilteredList<>(filehandler.getPostOffices(), f -> true);
        filterfield.textProperty().addListener((observableValue, oldVal, newVal) -> {
            filteredList.setPredicate(PostOffice -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newVal.toLowerCase();
                if (PostOffice.getZipCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return PostOffice.getMunicipality().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<PostOffice> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
        amountField.setText("Amount: " + sortedList.size());
    }

    /**
     * associate tablecolumns in tableview to PostOffice object variables
     */
    private void setCellProperty() {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        municipalityCol.setCellValueFactory(new PropertyValueFactory<>("municipality"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    /**
     * shows amount of post offices in tableview when pressed
     */
    public void handleRefreshButton() {
        amountField.setText("Amount: " + String.valueOf(tableView.getItems().size()));
    }

    /**
     * Let user save content in tableview to a file on computer
     * as a .txt or .csv
     */
    @FXML
    public void handleSave() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        //Opens where the main window is
        //User can not interact with Application before
        //file chooser window is closed
        File file = fc.showSaveDialog(mainBorderPane.getScene().getWindow());
        if (file != null){
            try {
                filehandler.saveToFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try{
                throw new FileNotFoundException("No file was chosen");
            }catch (FileNotFoundException fnfe){
                fnfe.printStackTrace();
            }
        }
    }

    /**
     * Lets user load from a .txt or .csv file on computer
     */
    @FXML
    public void handleLoadFromFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load from file");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
        File file = fc.showOpenDialog(mainBorderPane.getScene().getWindow());
        if (file != null) {
            try {
                filehandler.readFromFile(file);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            try {
                throw new FileNotFoundException("No File was chosen");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * shows pop up box containing information of application and version
     */
    @FXML
    public void handleInformationButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("This is an application that handles Post Offices\n" +
                            "The register provides information about: zipcode, " +
                            "\nmunicipiality and city for places in Norway");
        alert.setContentText("Version: 1.0");
        alert.show();
    }
}
