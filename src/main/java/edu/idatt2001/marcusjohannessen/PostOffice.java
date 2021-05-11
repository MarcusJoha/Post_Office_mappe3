package edu.idatt2001.marcusjohannessen;

public class PostOffice {
    private String zipCode;
    private String municipality;
    private String city;
    static final long serialVersionUID = 1L;

    public PostOffice(String zipCode, String municipality, String city) {
        this.zipCode = zipCode;
        this.municipality = municipality;
        this.city = city;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setMunicipalityNumber(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return zipCode +
                ", " + municipality +
                ", " + city;
    }
}
