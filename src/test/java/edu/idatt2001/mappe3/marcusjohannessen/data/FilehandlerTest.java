package edu.idatt2001.mappe3.marcusjohannessen.data;

import edu.idatt2001.mappe3.marcusjohannessen.NorwegianPostOffice;
import edu.idatt2001.mappe3.marcusjohannessen.PostOffice;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilehandlerTest {

    static Filehandler filehandler;
    static File file;
    static PostOffice postOffice1;
    static PostOffice postOffice2;
    static PostOffice postOffice3;


    @BeforeAll
    static void setUp(){
        filehandler = new Filehandler();
        file = new File("src/test/resources/edu.idatt2001.mappe3.marcusjohannessen/Test_Storage.txt");
        postOffice1 = new NorwegianPostOffice("1234", "Test1","Test1");
        postOffice2 = new NorwegianPostOffice("1234", "Test2","Test2");
        postOffice3 = new NorwegianPostOffice("1234", "Test3","Test3");

        filehandler.addPostOffice(postOffice1);
        filehandler.addPostOffice(postOffice2);
        filehandler.addPostOffice(postOffice3);
    }


    @Test
    void expected_postoffice_from_get_method() {
        Assertions.assertEquals(postOffice1, filehandler.getPostOffices().get(0));
        Assertions.assertEquals(postOffice2, filehandler.getPostOffices().get(1));
        Assertions.assertEquals(postOffice3, filehandler.getPostOffices().get(2));
    }

    @org.junit.jupiter.api.Test
    void assert_that_post_office_is_added() {
       PostOffice postOffice4 = new NorwegianPostOffice("1234", "Test4","Test4");
       filehandler.addPostOffice(postOffice4);
       Assertions.assertTrue(filehandler.getPostOffices().contains(postOffice4));
    }

    @Test
    void fail_exception_thrown_reading_from_file() {
        try{
            //Adds it to Observable list in filehandler
            filehandler.readFromFile(file);
            ObservableList<PostOffice> offices = filehandler.getPostOffices();
            Assertions.assertEquals(filehandler.getPostOffices().get(0), offices.get(0));
        }catch (IOException ioe){
            fail(ioe);
        }
    }

    @Test
    void fail_if_exception_thrown_saving_to_file() {
        try{
            File file = new File("src/test/resources/edu.idatt2001.mappe3.marcusjohannessen/Test_Storage1.txt");
            filehandler.saveToFile(file);
            filehandler.readFromFile(file);
            Assertions.assertEquals(postOffice1, filehandler.getPostOffices().get(0));
            Assertions.assertEquals(postOffice2, filehandler.getPostOffices().get(1));
            Assertions.assertEquals(postOffice3, filehandler.getPostOffices().get(2));
        }catch (IOException ioe){
            fail(ioe);
        }
    }
}