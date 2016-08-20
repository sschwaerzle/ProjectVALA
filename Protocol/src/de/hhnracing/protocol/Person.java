package de.hhnracing.protocol;

import java.io.Serializable;

public class Person implements Serializable {

    private final String name;
    private final String surname;
    private Address address;

    Person(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }

    // change whole address instead of parts of it
    public void setAddress(Address newAddress) {
        address = newAddress;
    }
}
