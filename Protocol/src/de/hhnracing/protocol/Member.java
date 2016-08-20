package de.hhnracing.protocol;

import java.io.Serializable;

public class Member extends Person implements Serializable {

    private final int matNumber;
    private final

    public Member(int matNumber) {

        this.matNumber = matNumber;

    }

}
