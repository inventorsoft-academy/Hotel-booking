package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.impl.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getHotels();

    Hotel getHotel(Hotel hotel) throws NotFoundException;
}
