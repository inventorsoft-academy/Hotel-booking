package com.bin.otkrivashkin.service.impl;
import com.bin.otkrivashkin.dao.ClientDAO;
import com.bin.otkrivashkin.dao.RoomDAO;
import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.util.LogManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	private LogManager logManager = LogManager.getLogger(ClientServiceImpl.class);

	private final ClientDAO clientDAO;

	private final RoomDAO roomDAO;

	public ClientServiceImpl(ClientDAO clientDAO, RoomDAO roomDAO) {
		this.clientDAO = clientDAO;
		this.roomDAO = roomDAO;
	}

	@Override
	public boolean addClient(Client cLient) throws DataManagerException {
		if(clientDAO.addClient(cLient)){
			roomDAO.setRoomAvailable(cLient.getRoomNumber(), false);
			return true;
		}
		return false;
	}

	@Override
	public List<Client> getClients() throws DataManagerException {
		return clientDAO.getAllClients();
	}

	@Override
	public Client getClientById(int id) {
		return clientDAO.getClientById(id);
	}

	@Override
	public boolean editClientById(int id, Client client) {
		return false;
	}

	@Override
	public boolean deleteClientById(int id) {
		int roomId = getClientById(id).getRoomNumber();
		if(clientDAO.deleteClientById(id)){
			roomDAO.setRoomAvailable(roomId, true);
			return true;
		}else {
			throw new DataManagerException("Can not create!");
		}
	}
}


