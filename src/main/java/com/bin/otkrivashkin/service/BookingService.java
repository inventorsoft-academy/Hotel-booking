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


    void bookClient(Client client) throws NotFoundException, WrongArgumentException;

    void bookClient(Client client, RoomType type) throws NotFoundException, WrongArgumentException;

    void bookClient(Client client, Room room, long days) throws NotFoundException, WrongArgumentException;

    void bookClient(String firstName, RoomType type, long days) throws IOException, NotFoundException, WrongArgumentException;

    void bookClient(Client client, double price) throws WrongArgumentException, WrongNumberArgsException, NotEnoughMoneyException, NotFoundException, NegativePriceException;

    void bookClient(String firstName) throws IOException, NotFoundException, WrongArgumentException;

    void bookClient(Client client, Room room) throws NotFoundException, WrongArgumentException;

    void bookClient(String firstName, RoomType type) throws IOException, NotFoundException, WrongArgumentException;

    void unBookClient(Room room, Client client) throws WrongArgumentException;

    Map<Room, Client> getBooking();

    void addBooking(Map<Room, Client> roomViaClient);
}
