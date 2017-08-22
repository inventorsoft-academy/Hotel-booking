package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientService {

    void addClient(Client cLient) throws IOException;

    Client getClient(String firstName) throws IOException, NotFoundException;

    Client getClient(Client client) throws WrongArgumentException, NotFoundException;

    List<Client> getClients();

    void editClient(String oldFirstName, String newFirstName) throws IOException, NotFoundException, WrongArgumentException;

    void deleteClient(String firstName) throws IOException, NotFoundException;

    void deleteClient(Client client) throws NotFoundException;

    void addClients(List<Client> clients);

    void printClients();

    int getClientId(Client clientToEdit);

    void setClient(int clientId, Client clientToEdit);
}
