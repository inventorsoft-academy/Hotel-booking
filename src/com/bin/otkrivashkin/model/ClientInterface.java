package com.bin.otkrivashkin.model;

public interface ClientInterface {

    void addClient(Client cLient);

    Client getClient(String firstName);

    void editClient(String firstName);

    void deleteClient(String firstName);
}
