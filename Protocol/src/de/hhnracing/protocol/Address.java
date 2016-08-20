package de.hhnracing.protocol;

public class Address {

    private String phoneNumber;
    private String postalCode;

    private String country;
    private String town;
    private String street;
    private String streetNumber;

    Address(String phoneNumber, String postalCode, String country, String town, String street, String streetNumber) {
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.country = country;
        this.town = town;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

}
