package com.bin.otkrivashkin.model;

import java.util.List;

public interface RoomInterface {

    void addRoom(Room room);

    void addRoom(RoomType type);

    void addRooms(int count, RoomType type);

    Room getRoom(int numberOfRoom);

    Room getRoom(double price);

    Room getRoom(RoomType type);

    Room getRoom(Room room);

    List<Room> getRooms();

    List<Room> getAvailableRooms();



    void editRoom(RoomType oldType, RoomType newType);

    void editRoom(int oldNumberOfRoom, int newNumberOfRoom);

    void editRooms(RoomType oldType, RoomType newType);

    void deleteRoom(int numberOfRoom);

    void deleteRoom(RoomType type);

    void deleteRooms(RoomType type);

    void deleteRooms();
}
