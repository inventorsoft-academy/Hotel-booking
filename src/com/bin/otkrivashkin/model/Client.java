package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.impl.Client;

import java.io.IOException;

public interface Client {

    void addClient(com.bin.otkrivashkin.model.impl.Client cLient) throws IOException;

    com.bin.otkrivashkin.model.impl.Client getClient(String firstName) throws IOException, NotFoundException;

    com.bin.otkrivashkin.model.impl.Client getClient(com.bin.otkrivashkin.model.impl.Client client) throws WrongArgumentException, NotFoundException;

    void editClient(String oldFirstName, String newFirstName) throws IOException, NotFoundException, WrongArgumentException;

    void deleteClient(String firstName) throws IOException, NotFoundException;
}
