package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;

import java.io.IOException;

public interface ClientInterface {

    void addClient(Client cLient) throws IOException;

    Client getClient(String firstName) throws IOException, NotFoundException;

    Client getClient(Client client) throws WrongArgumentException, NotFoundException;

    void editClient(String oldFirstName, String newFirstName) throws IOException, NotFoundException, WrongArgumentException;

    void deleteClient(String firstName) throws IOException, NotFoundException;
}
