package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.impl.Client;
import com.bin.otkrivashkin.model.impl.Room;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;

public interface Booking {


    void bookClient(Client client) throws NotFoundException, WrongArgumentException;

    void bookClient(Client client, RoomType type) throws NotFoundException, WrongArgumentException;

    void bookClient(Client client, double price) throws WrongArgumentException, WrongNumberArgsException, NotEnoughMoneyException, NotFoundException, NegativePriceException;

    void bookClient(String firstName) throws IOException, NotFoundException, WrongArgumentException;

    void bookClient(Client client, Room room) throws NotFoundException, WrongArgumentException;

    void bookClient(String firstName, RoomType type) throws IOException, NotFoundException, WrongArgumentException;

    void unBookClient(Room room, Client client) throws WrongArgumentException;
}
