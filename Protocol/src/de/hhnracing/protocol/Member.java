package de.hhnracing.protocol;

import java.io.Serializable;

public class Member extends Person implements Serializable {

    private final int matNumber;
    private final String course;

    public Member(Person existing, int matNumber, String course) {
        super(existing.getName(), existing.getSurname(), existing.getAddress());
        this.matNumber = matNumber;
        this.course = course;
    }

}
