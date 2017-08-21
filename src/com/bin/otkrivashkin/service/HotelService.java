package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Hotel;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.util.List;

public interface HotelService {

    void add(Hotel hotel) throws IOException;

    Hotel getByName(String hotelName) throws NotFoundException;

    List<Hotel> getHotels();

    void updateHotel(String oldName, String newName) throws NotFoundException, WrongNumberArgsException;

    void deleteHotel(String hotelToDelete) throws NotFoundException;
}
