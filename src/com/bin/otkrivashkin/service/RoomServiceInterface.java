package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;

import java.util.List;

public interface RoomServiceInterface {

    void createRooms(int countOfRooms, RoomType type, double price);

    List<Room> getRooms();

    void editTypeOfRooms(RoomType oldType, RoomType newType);

    List<Room> getRoomsByType(RoomType type);

    void deleteRooms(RoomType deletedRoomType);

}
