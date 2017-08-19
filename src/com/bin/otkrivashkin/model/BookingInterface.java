package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;

public interface BookingInterface {


    void bookClient(Client client) throws NotFoundException, WrongArgumentException;

    void bookClient(Client client, RoomType type) throws NotFoundException, WrongArgumentException;

    void bookClient(Client client, double price) throws WrongArgumentException, WrongNumberArgsException, NotEnoughMoneyException;

    void bookClient(String firstName) throws IOException;

    void bookClient(Client client, Room room);

    void bookClient(String firstName, RoomType type) throws IOException;

    void unBookClient(Client client);
}
