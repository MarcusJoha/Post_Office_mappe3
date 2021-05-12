package edu.idatt2001.mappe3.marcusjohannessen;

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

    public void setContentInList(ObservableList<PostOffice> list){
        postOfficeRegister.addAll(list);
    }
}
