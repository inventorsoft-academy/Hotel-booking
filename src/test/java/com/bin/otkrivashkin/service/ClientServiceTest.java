package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClientServiceTest {

    private ClientService clientService;

    @Before
    public void setUp() throws Exception {
        clientService = new ClientServiceImpl();
    }

    @Test
    public void addClient() throws Exception {
        Client expected = new Client("Igor", "Bondarenko", 3000.0);
        clientService.addClient(expected);
        Client actual = clientService.getClient(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void getClient() throws Exception {
        addClient();
        String firstName = "Igor";
        Client expected = clientService.getClientByFirstName(firstName);
        assertEquals(expected.getLastName(), "Bondarenko");
    }

    @Test
    public void getClients() throws Exception {
        Client client1 = new Client("testing", "first", 1234.5);
        Client client2 = new Client("testing", "second", 1234.5);
        clientService.addClient(client1);
        clientService.addClient(client2);
        int actual = clientService.getClients().size();
        int expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    public void editClient() throws Exception {
        addClient();
        String oldFirstName = "Igor";
        Client client = clientService.getClientByFirstName(oldFirstName);

        String newFirstName = "BobbyBob";
        client.setFirstName(newFirstName);

        int clientIndex = clientService.getClientIndex(client);
        clientService.setClient(clientIndex, client);

        assertEquals(clientIndex, clientService.getClientIndex(client));
    }

    @Test
    public void deleteClient() throws Exception {
        addClient();
        assertEquals(1, clientService.getClients().size());
        Client igor = clientService.getClientByFirstName("Igor");
        clientService.deleteClient(igor);
        int size = clientService.getClients().size();
        assertEquals(0, size);
    }


    @Test
    public void addClients() throws Exception {
        List<Client> clientList = Arrays.asList(
                new Client("testing", "testing", 1234.5),
                new Client("testing", "testing", 1234.5),
                new Client("testing", "testing", 1234.5),
                new Client("testing", "testing", 1234.5)
        );

        int actual = clientService.addClients(clientList);
        assertEquals(4, actual);
    }

    @Test
    public void printClients() throws Exception {
        //
    }

    @Test
    public void getClientId() throws Exception {
        Client client = new Client("testing", "testing", 1234.5);

        int clientId = client.getClientId();

        clientService.addClient(client);

        int actual = clientService.getClient(client).getClientId();

        assertEquals(clientId, actual);

    }

    @Test
    public void setClient() throws Exception {
        Client client1 = new Client("testing", "testing", 1234.5);
        Client client2 = new Client("testing", "testing", 1234.5);

        clientService.addClient(client1);
        clientService.addClient(client2);

        Client client3 = new Client("bordof", "testing", 1234.5);

        clientService.setClient(0, client3);

        Client client = clientService.getClient(client3);

        assertEquals(client, client3);
    }


}