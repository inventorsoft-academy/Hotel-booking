package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.impl.ClientImpl;

import java.io.IOException;

public interface Client {

    void addClient(ClientImpl cLient) throws IOException;

    ClientImpl getClient(String firstName) throws IOException, NotFoundException;

    ClientImpl getClient(ClientImpl client) throws WrongArgumentException, NotFoundException;

    void editClient(String oldFirstName, String newFirstName) throws IOException, NotFoundException, WrongArgumentException;

    void deleteClient(String firstName) throws IOException, NotFoundException;
}
