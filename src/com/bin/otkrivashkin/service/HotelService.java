package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;

import java.util.Map;

/**
 * Created by otkrivashkin on 08.08.2017.
 */
public class HotelService {

    private static int id;
    private Map<Integer, Hotel> hotels;

    public void create(Hotel hotel) {
        id ++;
        hotels.put(id, hotel);
    }

    public Hotel getHotelById(int id) {
        return hotels.get(id);
    }

    public void update(Hotel hotel, int id) {
        hotels.replace(id, hotel);
    }

    public void delete(int id) {
        hotels.remove(id);
    }

}
