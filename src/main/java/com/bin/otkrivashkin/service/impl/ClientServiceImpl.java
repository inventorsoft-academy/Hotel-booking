package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.util.LogManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private LogManager logManager = LogManager.getLogger(ClientServiceImpl.class);

    private List<Client> clients;

    public ClientServiceImpl() {
        this.clients = new ArrayList<>();
    }

    @Override
    public void addClient(Client cLient) throws IOException {
        if (cLient.validate().isEmpty()) {
            clients.add(cLient);
        } else {
            throw new IOException(String.valueOf(cLient.validate().values()));
        }
    }

    @Override
    public Client getClientByFirstName(String firstName) throws IOException, NotFoundException {
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
        } else {
            throw new WrongArgumentException(String.valueOf(client.validate().values()));
        }
        throw new NotFoundException("Client with first name " + client.getFirstName() + " not found");
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public void deleteClient(Client client) throws NotFoundException {
        if (client.validate().keySet().isEmpty()) {
            clients.remove(client);
        } else {
            throw new NotFoundException(String.valueOf(client.validate().values()));
        }
    }

    @Override
    public int addClients(List<Client> clients) {
        this.clients.addAll(clients);
        return clients.size();
    }

    @Override
    public void printClients() {
        System.out.println(String.format("%10s %15s %15s %10s", "ID", "FIRST NAME", "LAST NAME", "MONEY"));
        for (Client client : clients) {
            System.out.println(String.format("%10s %15s %15s %10s",
                    client.getClientId(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getCash()
            ));
        }
    }

    @Override
    public void setClient(int clientIndex, Client client) {
        clients.set(clientIndex, client);
    }

    @Override
    public Client getClientById(int id) {
        for (Client client : clients) {
            if (client.getClientId() == id) {
                return client;
            }
        }

        return null;
    }

    @Override
    public void editClientById(int id, Client client) {
        Client clientById = getClientById(id);

        if (clientById != null) {
            try {
                clientById.setFirstName(client.getFirstName());
                clientById.setLastName(client.getLastName());
                clientById.setCash(client.getCash());
                clients.set(clients.indexOf(clientById), clientById);
            } catch (WrongArgumentException e) {
                logManager.error(e.getMessage());
            }
        } else {
            try {
                throw new NotFoundException("This client is not exist");
            } catch (NotFoundException e) {
                logManager.error(e.getMessage());
            }
        }
    }

    @Override
    public void deleteClientById(int id) {
        clients.remove(getClientById(id));
    }

    @Override
    public void deleteClientByFirstName(String firstName) {

    }
}
