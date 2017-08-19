package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.impl.ClientImpl;
import com.bin.otkrivashkin.model.impl.Hotel;
import com.bin.otkrivashkin.model.impl.RoomImpl;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.impl.HotelServiceImpl;

import java.io.IOException;

public class Factory {

    private HotelServiceImpl hotelServiceImpl;

    public Factory(HotelServiceImpl hotelServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;
    }

    public void initHotel() throws IOException {
        Hotel hotel = new Hotel();
        hotel.setName("test");

        RoomImpl room1 = new RoomImpl(RoomType.CHEAP, 1, 250.0, true);
        RoomImpl room2 = new RoomImpl(RoomType.LUX, 2, 1250.0, true);
        RoomImpl room3 = new RoomImpl(RoomType.INDIAN, 3, 2250.0, true);
        RoomImpl room4 = new RoomImpl(RoomType.COUNTRY, 4, 3250.0, true);
        RoomImpl room5 = new RoomImpl(RoomType.PRESIDENT, 5, 4250.0, true);

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);
        hotel.addRoom(room4);
        hotel.addRoom(room5);

        ClientImpl client1 = new ClientImpl("Igor", "bin");
        ClientImpl client2 = new ClientImpl("Evgen", "bon");
        ClientImpl client3 = new ClientImpl("Bro", "gud");
        ClientImpl client4 = new ClientImpl("Aulio", "zorro");
        ClientImpl client5 = new ClientImpl("Kent", "fine");

        try {
            hotel.addClient(client1);
            hotel.addClient(client2);
            hotel.addClient(client3);
            hotel.addClient(client4);
            hotel.addClient(client5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            hotel.bookClient(client1, room1);
            hotel.bookClient(client2, room2);
            hotel.bookClient(client3, room3);
        } catch (NotFoundException | WrongArgumentException e) {
            e.printStackTrace();
        }

        hotelServiceImpl.add(hotel);
    }
}
