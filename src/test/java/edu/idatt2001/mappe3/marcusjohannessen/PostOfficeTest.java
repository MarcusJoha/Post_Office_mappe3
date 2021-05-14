package edu.idatt2001.mappe3.marcusjohannessen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostOfficeTest {

    static PostOffice postOffice;

    @BeforeAll
    static void setUp(){
        postOffice = new NorwegianPostOffice("1234", "Mun","Test1");
    }

    @Test
    @DisplayName("Test for setCity() method")
    void set_method_for_city_equals_expected() {
        postOffice.setCity("city");
        Assertions.assertEquals("city", postOffice.getCity());
    }

    @Test
    @DisplayName("Test for getMunicipality() method")
    void return_expected_municipality() {
        Assertions.assertEquals("Mun", postOffice.getMunicipality());
    }

    @Test
    @DisplayName("Test for getMunicipality() method")
    void sets_expected_municipality() {
        postOffice.setMunicipality("Mun1");
        Assertions.assertEquals("Mun1", postOffice.getMunicipality());
    }

    @Test
    @DisplayName("Test for getZipCode() method")
    void returns_expected_zip_code() {
        Assertions.assertEquals("1234", postOffice.getZipCode());
    }

    @Test
    @DisplayName("Test for setZipCode() method")
    void sets_expected_zip_code() {
        PostOffice post = new NorwegianPostOffice("4231", "test", "test");
        post.setZipCode("4321");
        Assertions.assertEquals("4321", post.getZipCode());

    }
}