package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.WrongArgumentException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class Client implements Validator {

    private static AtomicInteger uniqueId = new AtomicInteger();
    private int clientId;
    private String firstName;
    private String lastName;
    private double cash;
    private LocalDate startDate;
    private LocalDate endDate;

    public Client() {
        clientId = uniqueId.getAndIncrement();
    }

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        clientId = uniqueId.getAndIncrement();
    }

    public Client(String firstName, String lastName, double cash) {
        this(firstName, lastName);
        this.cash = cash;
        clientId = uniqueId.getAndIncrement();
    }

    public Client(String firstName, String lastName, double cash, LocalDate startDate, LocalDate endDate) {
        this(firstName, lastName, cash);
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientId = uniqueId.getAndIncrement();
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate date) {
        this.endDate = date;
    }

    public void setStartDate() {
        this.startDate = LocalDate.now();
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
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
        return "Client{" +
                firstName + ',' +
                lastName + ',' +
                cash + ',' +
                startDate  + ',' +
                endDate +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getClientId() != client.getClientId()) return false;
        if (Double.compare(client.getCash(), getCash()) != 0) return false;
        if (!getFirstName().equals(client.getFirstName())) return false;
        if (!getLastName().equals(client.getLastName())) return false;
        if (getStartDate() != null ? !getStartDate().equals(client.getStartDate()) : client.getStartDate() != null)
            return false;
        return getEndDate() != null ? getEndDate().equals(client.getEndDate()) : client.getEndDate() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getClientId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        temp = Double.doubleToLongBits(getCash());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getStartDate() != null ? getStartDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
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
