package edu.idatt2001.marcusjohannessen.data;

import edu.idatt2001.marcusjohannessen.NorwegianPostOffice;
import edu.idatt2001.marcusjohannessen.PostOffice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    /**
     *
     * @param filePath
     * @throws IOException
     * Reads from a chosen file
     * with post offices
     * that contains zipcode,
     * municipality and city
     */
    public void readFromFile(File filePath) throws IOException{
        BufferedReader br = null;
        try{
            br = Files.newBufferedReader(filePath.toPath());
        }catch (NullPointerException npe){
            npe.printStackTrace();
            System.out.println("No file was choosen");
        }
        String input;
        try {
            while ((input = br.readLine()) != null){
                String[] pieces = input.split("\t");
                String zipCode = pieces[0];
                String municipality = pieces[1];
                String city = pieces[3];

                PostOffice po = new NorwegianPostOffice(zipCode, municipality, city);
                addPostOffice(po);
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        } finally {
            if(br != null){
                br.close();
            }
        }
    }

    /**
     *
     * @param file
     * @throws IOException
     * Saves postofices in Application to a file
     */
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
