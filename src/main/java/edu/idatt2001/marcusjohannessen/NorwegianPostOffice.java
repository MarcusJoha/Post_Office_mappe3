package edu.idatt2001.marcusjohannessen;

/**
 * Norwegian Post Office
 * this is just to demonstrate that it is possible to
 * different post offices based on different countries
 * that extends from Post office.
 * This means that you can extends classes with more variables and methods
 * Also you have to possibility of implementing a list in the application
 * that allows you to switch between different postoffices
 */

public class NorwegianPostOffice extends PostOffice {


    public NorwegianPostOffice(String zipCode, String municipality, String city) {
        super(zipCode, municipality, city);
    }
}
