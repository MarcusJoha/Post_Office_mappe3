package edu.idatt2001.marcusjohannessen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Filehandler {

    private ObservableList<PostOffice> postOffices;

    public Filehandler() {
        postOffices = FXCollections.observableArrayList();
    }

    public ObservableList<PostOffice> getPostOffices() {
        return postOffices;
    }

    public void addPostOffice(PostOffice postOffice){
        postOffices.add(postOffice);
    }

    public void writeToCSV() throws IOException {

    }

    //Todo: må finne ut hvordan jeg henter de
    // riktige dataene jeg trenger.

    public void readFromFile() throws IOException{
        File filePath = new File("src/main/resources/edu/idatt2001/marcusjohannessen/storage/register.txt");

        BufferedReader br = Files.newBufferedReader(filePath.toPath());
        String input;
        try {
            while ((input = br.readLine()) != null){
                String[] pieces = input.split("\t");
                String zipCode = pieces[0];
                String municipality = pieces[1];
                String city = pieces[3];

                PostOffice po = new PostOffice(zipCode, municipality, city);
                addPostOffice(po);
            }
        }finally {
            if(br != null){
                br.close();
            }
        }
    }

    public void saveToFile(File file) throws IOException{
        try (FileWriter fr = new FileWriter(file)){
            for (PostOffice p: postOffices){
                fr.append(p.getZipCode());
                fr.append(",");
                fr.append(p.getMunicipality());
                fr.append(",");
                fr.append(p.getCity());
                fr.append("\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
