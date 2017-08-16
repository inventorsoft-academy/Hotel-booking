package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;

import java.util.ArrayList;
import java.util.List;

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
        stringList.add("1 - add hotel\n");
        stringList.add("2 - view hotel\n");
        stringList.add("3 - edit hotel\n");
        stringList.add("4 - delete hotel\n");

        stringList.add("5 - add rooms\n");
        stringList.add("6 - view rooms\n");
        stringList.add("7 - edit rooms\n");
        stringList.add("8 - delete rooms\n");

        stringList.add("99 - quit|save.");

        return stringList;
    }

    public void add(Hotel hotel) {
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
