package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;

import java.util.Map;

public interface BookingService {

    boolean registerClient(Client client, Room room, long days) throws NotFoundException, WrongArgumentException, NotEnoughMoneyException, DataManagerException;

    Map<Room, Client> getRegisterClients();

    void unregisterClientByFirstName(String name);

    void unregisterClientById(int clientId) throws NotFoundException, DataManagerException;

    Client getRegisterClientById(int id) throws NotFoundException;

    void addMapOfClientsWithRooms(Map<Room, Client> roomViaClient);
}
