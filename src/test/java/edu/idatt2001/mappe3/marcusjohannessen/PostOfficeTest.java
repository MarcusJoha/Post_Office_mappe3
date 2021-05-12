package edu.idatt2001.mappe3.marcusjohannessen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PostOfficeTest {

    static PostOffice postOffice;

    @BeforeAll
    static void setUp(){
        postOffice = new NorwegianPostOffice("1234", "Mun","Test1");
    }

    @Test
    void setCity() {
        postOffice.setCity("city");
        Assertions.assertEquals("city", postOffice.getCity());
    }

    @Test
    void getMunicipality() {
        Assertions.assertEquals("Mun", postOffice.getMunicipality());
    }

    @Test
    void setMunicipality() {
        postOffice.setMunicipality("Mun1");
        Assertions.assertEquals("Mun1", postOffice.getMunicipality());
    }

    @Test
    void getZipCode() {
        Assertions.assertEquals("1234", postOffice.getZipCode());
    }

    @Test
    void setZipCode() {
        PostOffice post = new NorwegianPostOffice("4231", "test", "test");
        post.setZipCode("4321");
        Assertions.assertEquals("4321", post.getZipCode());

    }
}