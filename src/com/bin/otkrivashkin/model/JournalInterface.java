package com.bin.otkrivashkin.model;

/**
 * Created by otkrivashkin on 10.08.2017.
 */
public interface JournalInterface {
    boolean saveRoom(Room room);

    boolean loadRooms();

    boolean attachClientToRoom(Client client, int numberOfRoom);

    boolean detachClientFromRoom(Client client);

    boolean saveHotel(Hotel hotel);

    boolean loadHotel(Hotel hotel);
}
