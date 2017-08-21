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
                "1 - hotel options\n",
                "2 - room options\n",
                "3 - client options\n",
                "4 - booking options\n",
                "5 - journal options\n",
                "100 - save\n",
                "200 - load\n",
                "300 - exit\n",
                "-1 - hotel in one click!\n"
        );
        System.out.println(strings);
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
