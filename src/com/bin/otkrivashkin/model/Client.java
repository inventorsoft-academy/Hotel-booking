package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.WrongArgumentException;

import java.util.HashMap;
import java.util.Map;

public class Client implements Validator {

    private String firstName;
    private String lastName;

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) throws WrongArgumentException {
        if (firstName.length() < 3) throw new WrongArgumentException("First name is too short!");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws WrongArgumentException {
        if (firstName.length() < 3) throw new WrongArgumentException("Last name is too short!");
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "" +
                firstName
                + "," +
                lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!firstName.equals(client.firstName)) return false;
        return lastName.equals(client.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public Map<String, String> validate() {
        Map<String, String> res = new HashMap<>();

        if (firstName.length() < 4) res.put(firstName, "The first name must be longer than 4 chars!");
        if (firstName.length() > 15) res.put(firstName, "The first name is too long! Maximum is 14!");
        if (lastName.length() < 4) res.put(lastName, "The last name is too short! Minimum length is 4!");
        if (firstName.length() > 15) res.put(firstName, "The first name is too long! Maximum is 14!");

        return res;
    }
}
