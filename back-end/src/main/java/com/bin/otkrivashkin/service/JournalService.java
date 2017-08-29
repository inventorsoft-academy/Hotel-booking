package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;

public interface JournalService {

    void printAll() throws NotFoundException;

    String getInfo(Client client);

    String getInfo(Room room);

}
