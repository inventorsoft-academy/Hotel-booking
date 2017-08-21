package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.impl.Hotel;

import java.io.IOException;
import java.util.List;

public interface HotelService {

    void add(Hotel hotel) throws IOException;

    Hotel getHotel(String hotelName) throws NotFoundException;

    List<Hotel> getHotels();

    Hotel getHotel(Hotel hotel) throws NotFoundException;
}
