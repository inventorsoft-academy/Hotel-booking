package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.service.ClientService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private List<Client> clients;

    public ClientServiceImpl() {
        this.clients = new ArrayList<>();
    }

    @Override
    public void addClient(Client cLient) throws IOException {
        if (cLient.validate().isEmpty()) {
            clients.add(cLient);
        }
        else {
            throw new IOException(String.valueOf(cLient.validate().values()));
        }
    }

    @Override
    public Client getClient(String firstName) throws IOException, NotFoundException {
        if (firstName == null || firstName.length() < 3) throw new IOException("The first name is too short!");

        for (Client client : clients) {
            if (client.getFirstName().equalsIgnoreCase(firstName)) {
                return client;
            }
        }

        throw new NotFoundException("The client is not exist");
    }



    @Override
    public Client getClient(Client client) throws WrongArgumentException, NotFoundException {
        if (client.validate().keySet().isEmpty()) {
            for (Client visitor : clients) {
                if (visitor.equals(client)) {
                    return visitor;
                }
            }
        }
        else {
            throw new WrongArgumentException(String.valueOf(client.validate().values()));
        }
        throw new NotFoundException("Client with first name " + client.getFirstName() + " not found");
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public void editClient(String oldFirstName, String newFirstName) throws IOException, NotFoundException, WrongArgumentException {
        getClient(oldFirstName).setFirstName(newFirstName);
    }

    @Override
    public void deleteClient(String firstName) throws IOException, NotFoundException {
        deleteClient(getClient(firstName));
    }

    @Override
    public void deleteClient(Client client) throws NotFoundException {
        if (client.validate().keySet().isEmpty()) {
            clients.remove(client);
        }
        else {
            throw  new NotFoundException(String.valueOf(client.validate().values()));
        }
    }

    @Override
    public int addClients(List<Client> clients) {
        this.clients.addAll(clients);
        return clients.size();
    }

    @Override
    public void printClients() {

    }

    @Override
    public int getClientIndex(Client client) {
        return clients.indexOf(client);
    }

    @Override
    public int getClientId(Client clientToEdit) {
        return clients.stream()
                .filter(client -> client.equals(clientToEdit))
                .findFirst()
                .get()
                .getClientId();
    }

    @Override
    public void setClient(int clientIndex, Client client) {
        clients.set(clientIndex, client);
    }

    @Override
    public void deleteClients() {
        clients.clear();
    }
}
