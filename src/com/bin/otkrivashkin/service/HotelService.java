package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by otkrivashkin on 10.08.2017.
 */
public class HotelService {

    private List<String> listOfOptions;
    private List<Hotel> listOfHotels;

    public HotelService() {
        listOfHotels = new ArrayList<>();
    }

    public List<String> getOptions() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1 - create hotel.");
        stringList.add("2 - view hotel.");
        stringList.add("3 - edit hotel.");
        stringList.add("4 - delete hotel.");
        return stringList;
    }

    public void create(Hotel hotel) {
        listOfHotels.add(hotel);
    }

    public Hotel getByName(String hotelByName) {
        for (Hotel hotel: listOfHotels) {
            if (hotel.getName().equals(hotelByName)) {
                return hotel;
            }
        }
        return null;
    }

    public List<Hotel> getHotels() {
        return listOfHotels;
    }

    public void updateHotel(String oldName, String newName) {
        getByName(oldName).setName(newName);
    }

    public void deleteHotel(String hotelToDelete) {
        listOfHotels.remove(getByName(hotelToDelete));
    }
}
