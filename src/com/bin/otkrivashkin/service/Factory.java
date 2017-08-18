package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;

import java.io.IOException;

public class Factory {

    private HotelService hotelService;

    public Factory(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public void initHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("test");

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

        Client client1 = new Client("Igor", "bin");
        Client client2 = new Client("Evgen", "bon");
        Client client3 = new Client("Bro", "gud");
        Client client4 = new Client("Aulio", "zorro");
        Client client5 = new Client("Kent", "fine");

        try {
            hotel.addClient(client1);
            hotel.addClient(client2);
            hotel.addClient(client3);
            hotel.addClient(client4);
            hotel.addClient(client5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        hotel.bookClient(client1, room1);
        hotel.bookClient(client2, room2);
        hotel.bookClient(client3, room3);

        hotelService.add(hotel);
    }
}
