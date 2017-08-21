package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.impl.HotelServiceImpl;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;

public class Factory {

    private HotelServiceImpl hotelServiceImpl;

    public Factory(HotelServiceImpl hotelServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;
    }

    public void initHotel() throws IOException, WrongNumberArgsException {
        Hotel hotel = new Hotel();
        hotel.setName("testhotel");

        Room room1 = new Room(RoomType.CHEAP, 1, 250.0, true);
        Room room2 = new Room(RoomType.LUX, 2, 1250.0, true);
        Room room3 = new Room(RoomType.INDIAN, 3, 2250.0, true);
        Room room4 = new Room(RoomType.COUNTRY, 4, 3250.0, true);
        Room room5 = new Room(RoomType.PRESIDENT, 5, 4250.0, true);

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);
        hotel.addRoom(room4);
        hotel.addRoom(room5);

        Client client1 = new Client("joejoe", "joejoe", 7000.0);
        Client client2 = new Client("Evgen", "evgen", 9000.5);
        Client client3 = new Client("brobro", "brobro", 6500.2);
        Client client4 = new Client("kotop", "kotop", 5400.78);

        try {
            hotel.addClient(client1);
            hotel.addClient(client2);
            hotel.addClient(client3);
            hotel.addClient(client4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            hotel.bookClient(client1, room1, 5);
            hotel.bookClient(client2, room2, 3);
            hotel.bookClient(client3, room3, 1);
        } catch (NotFoundException | WrongArgumentException e) {
            e.printStackTrace();
        }

        hotelServiceImpl.add(hotel);
    }
}
