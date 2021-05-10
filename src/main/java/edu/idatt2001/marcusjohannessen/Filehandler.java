package edu.idatt2001.marcusjohannessen;

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

    public ObservableList<PostOffice> readFromFile() throws IOException{
        ObservableList<PostOffice> list = FXCollections.observableArrayList();
        File filePath = new File("src/main/resources/edu/idatt2001/marcusjohannessen/PostNum.txt");

        BufferedReader br = Files.newBufferedReader(filePath.toPath());
        String input;
        try {
            while ((input = br.readLine()) != null){
                String[] pieces = input.split("\t");
                String zipCode = pieces[0];
                String municipality = pieces[1];
                String city = pieces[3];

                PostOffice po = new PostOffice(zipCode, municipality, city);
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
