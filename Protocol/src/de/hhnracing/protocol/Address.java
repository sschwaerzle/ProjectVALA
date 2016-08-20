package de.hhnracing.protocol;

import java.io.Serializable;

public class Address implements Serializable {

    private String phoneNumber;
    private String postalCode;

    private final String country;
    private final String town;
    private final String street;
    private final String streetNumber;

    Address(String phoneNumber, String postalCode, String country, String town, String street, String streetNumber) {
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.country = country;
        this.town = town;
        this.street = street;
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
