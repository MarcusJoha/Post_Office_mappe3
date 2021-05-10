package edu.idatt2001.marcusjohannessen.office;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PostOfficeRegister {

    private ObservableList<PostOffice> postOfficeRegister;

    public PostOfficeRegister() {
        postOfficeRegister = FXCollections.observableArrayList();
    }

    public ObservableList<PostOffice> getPostOfficeRegister() {
        return postOfficeRegister;
    }
}
