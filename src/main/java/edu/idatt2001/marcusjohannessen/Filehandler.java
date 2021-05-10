package edu.idatt2001.marcusjohannessen;

import edu.idatt2001.marcusjohannessen.office.PostOffice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Filehandler {

    public Filehandler() {
    }

    public void writeToCSV() throws IOException {

    }

    //Todo: må finne ut hvordan jeg henter de
    // riktige dataene jeg trenger.
    public ObservableList<PostOffice> readFromCSV() throws IOException{
        ObservableList<PostOffice> list = FXCollections.observableArrayList();
        File filePath = new File("src/main/resources/edu/idatt2001/marcusjohannessen/Postnummerregister.txt");

        BufferedReader br = Files.newBufferedReader(filePath.toPath());
        String input;
        try {
            while ((input = br.readLine()) != null){
                String[] pieces = input.split("\t");
                String zipCodeNumber = pieces[0];
                String municipality = pieces[1];

                PostOffice po = new PostOffice(zipCodeNumber, municipality);
                list.add(po);
            }
        }finally {
            if(br != null){
                br.close();
            }
        }
        return list;
    }
}
