package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by otkrivashkin on 09.08.2017.
 */
public class HotelService {

    private List<Hotel> hotels;

    public HotelService() {
        hotels = new ArrayList<>();
    }

    public Hotel getHotelByName(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return hotel;
            }
        }
        return null;
    }
}
