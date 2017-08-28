package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientService {

    void addClient(Client cLient) throws IOException;

    Client getClientByFirstName(String firstName) throws IOException, NotFoundException;

    Client getClient(Client client) throws WrongArgumentException, NotFoundException;

    List<Client> getClients();

    int addClients(List<Client> clients);

    void printClients();

    Client getClientById(int id);

    void editClientById(int id, Client client);

    void deleteClientById(int id);

    void deleteClientByFirstName(String firstName);

    void setClient(int clientId, Client client);

    void deleteClient(Client client) throws NotFoundException;
}
