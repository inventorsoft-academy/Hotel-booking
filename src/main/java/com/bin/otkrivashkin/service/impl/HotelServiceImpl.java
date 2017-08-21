package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.service.HotelService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by otkrivashkin on 10.08.2017.
 */
public class HotelServiceImpl implements HotelService {

    private Logger logger = Logger.getLogger(HotelServiceImpl.class.getName());
    private List<Hotel> listOfHotels;

    public HotelServiceImpl() {
        listOfHotels = new ArrayList<>();
    }

    public void getMainOptions() {

        List<String> strings = Arrays.asList(
                "1 - hotel options",
                "2 - room options",
                "3 - client options",
                "4 - booking options",
                "5 - journal options",
                "100 - save",
                "200 - load",
                "300 - exit",
                "-1 - hotel in one click!"
        );
        for (String string: strings) {
            System.out.println(string);
        }
    }

    public void getHotelOptions() {
        List<String> strings = Arrays.asList(
                "1 - add hotel",
                "2 - view hotel",
                "3 - edit hotel",
                "4 - delete hotel",
                "100 - save",
                "200 - load <hotelName>",
                "300 - quit"
        );
        for (String string: strings) {
            System.out.println(string);
        }
    }

    public void getRoomOptions() {
        List<String> strings = Arrays.asList(
                "1 - add rooms",
                "2 - view rooms",
                "3 - edit rooms",
                "4 - delete rooms"
        );

        for (String string: strings) {
            System.out.println(string);
        }
    }

    public void getClientOptions(){
        List<String> strings = Arrays.asList(
                "1 - add client",
                "2 - view client",
                "3 - edit client",
                "4 - delete client"
        );

        for (String string: strings) {
            System.out.println(string);
        }
    }

    public void getBookingOptions() {
        List<String> strings = Arrays.asList(
                "1 - book client",
                "2 - unbook client"
        );

        for (String string: strings) {
            System.out.println(string);
        }
    }

    public void getJournalOptions() {
        List<String> strings = Arrays.asList(
                "1 - print client",
                "2 - print room",
                "3 - print rooms",
                "4 - print available rooms"
        );

        for (String string: strings) {
            System.out.println(string);
        }
    }

    @Override
    public void add(Hotel hotel) throws IOException {
        if (hotel.validate().keySet().isEmpty()) {
            listOfHotels.add(hotel);
        }
        else {
            throw new IOException(hotel.validate().values().stream().findAny().get());
        }
    }

    @Override
    public Hotel getHotel(String hotelByName) throws NotFoundException {
        if (hotelByName == null) throw new NullPointerException("We need a name of the hotel.");
        for (Hotel hotel: listOfHotels) {
            if (hotel.getName().equals(hotelByName)) {
                return hotel;
            }
        }
        throw new NotFoundException("Hotel not found or doesn't exist.");
    }

    @Override
    public List<Hotel> getHotels() {
        logger.info("return list of hotels.");
        return listOfHotels;
    }

    @Override
    public void updateHotel(String oldName, String newName) throws NotFoundException, WrongNumberArgsException {
        getHotel(oldName).setName(newName);
    }

    @Override
    public void deleteHotel(String hotelToDelete) throws NotFoundException {
        listOfHotels.remove(getHotel(hotelToDelete));
    }

}
