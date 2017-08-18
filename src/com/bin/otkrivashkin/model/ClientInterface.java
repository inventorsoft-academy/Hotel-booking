package com.bin.otkrivashkin.model;

import java.io.IOException;

public interface ClientInterface {

    void addClient(Client cLient) throws IOException;

    Client getClient(String firstName);

    Client getClient(Client client);

    void editClient(String oldFirstName, String newFirstName);

    void deleteClient(String firstName);
}
