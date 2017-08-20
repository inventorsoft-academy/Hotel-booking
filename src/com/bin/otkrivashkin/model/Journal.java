package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.impl.ClientImpl;
import com.bin.otkrivashkin.model.impl.Hotel;
import com.bin.otkrivashkin.model.impl.RoomImpl;

import java.util.List;

public interface Journal {

    ClientImpl getInfoAboutClient(Hotel hotel, ClientImpl client) throws NotFoundException, WrongArgumentException;

    RoomImpl getInfoAboutRoom(Hotel hotel, RoomImpl room) throws NotFoundException;

    Hotel getInfoAboutHotel(Hotel hotel) throws NotFoundException;

    void printAll(String hotelName) throws NotFoundException;

}
