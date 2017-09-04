package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientService {

	boolean addClient(Client cLient) throws DataManagerException;

	List<Client> getClients();

	Client getClientById(int id);

	boolean editClientById(int id, Client client);

	boolean deleteClientById(int id);


}
