package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.util.Map;

public interface BookingService {

    void registerClient(Client client, Room room, long days) throws NotFoundException, WrongArgumentException, NotEnoughMoneyException;

    Map<Room, Client> getRegisterClients();

    void unregisterClientByFirstName(String name);

    void unregisterClientById(int clientId) throws NotFoundException;

    Client getRegisterClientById(int id) throws NotFoundException;

    void addMapOfClientsWithRooms(Map<Room, Client> roomViaClient);
}
