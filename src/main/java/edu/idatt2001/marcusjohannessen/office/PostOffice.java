package edu.idatt2001.marcusjohannessen.office;

public class PostOffice {
    private String city;
    private String municipality;
    private String municipalityNumber;


    public PostOffice (String municipalityNumber, String municipality){
        this(municipalityNumber, municipality, null);
    }

    public PostOffice(String municipalityNumber, String municipality, String city) {
        this.city = city;
        this.municipality = municipality;
        this.municipalityNumber = municipalityNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getMunicipalityNumber() {
        return municipalityNumber;
    }

    public void setMunicipalityNumber(String municipalityNumber) {
        this.municipalityNumber = municipalityNumber;
    }

    /*
    @Override
    public String toString() {
        return municipalityNumber +
                ", " + municipality +
                ", " + city;
    }

     */
}
