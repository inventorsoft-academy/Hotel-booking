package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<String> getMainOptions() {
        return Arrays.asList(
                "1 - hotel options\n",
                "2 - room options\n",
                "3 - client options\n",
                "4 - booking options\n",
                "5 - journal options\n",
                "100 - save\n",
                "200 - load <hotelName>\n",
                "300 - exit\n",
                "-1 - hotel in one click!\n"
        );
    }

    public List<String> getHotelOptions() {
        return Arrays.asList(
                "1 - add hotel\n",
                "2 - view hotel\n",
                "3 - edit hotel\n",
                "4 - delete hotel\n",
                "100 - save\n",
                "200 - load <hotelName>\n",
                "300 - quit"
        );
    }

    public List<String> getRoomOptions() {
        return Arrays.asList(
                "1 - add rooms\n",
                "2 - view rooms\n",
                "3 - edit rooms\n",
                "4 - delete rooms\n"
        );
    }

    public List<String> getClientOptions(){
        return Arrays.asList(
                "1 - add client\n",
                "2 - view client\n",
                "3 - edit client\n",
                "4 - delete client\n"
        );
    }

    public List<String> getBookingOptions() {
        return Arrays.asList(
                "1 - book client\n",
                "2 - unbook client\n"
        );
    }

    public List<String> getJournalOptions() {
        return Arrays.asList(
                "1 - print client\n",
                "2 - print room\n",
                "3 - print rooms\n",
                "4 - print available rooms\n"
        );
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
