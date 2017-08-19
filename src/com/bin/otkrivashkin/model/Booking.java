package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.impl.ClientImpl;
import com.bin.otkrivashkin.model.impl.RoomImpl;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;

public interface Booking {


    void bookClient(ClientImpl client) throws NotFoundException, WrongArgumentException;

    void bookClient(ClientImpl client, RoomType type) throws NotFoundException, WrongArgumentException;

    void bookClient(ClientImpl client, double price) throws WrongArgumentException, WrongNumberArgsException, NotEnoughMoneyException, NotFoundException, NegativePriceException;

    void bookClient(String firstName) throws IOException, NotFoundException, WrongArgumentException;

    void bookClient(ClientImpl client, RoomImpl room) throws NotFoundException, WrongArgumentException;

    void bookClient(String firstName, RoomType type) throws IOException, NotFoundException, WrongArgumentException;

    void unBookClient(RoomImpl room, ClientImpl client) throws WrongArgumentException;
}
