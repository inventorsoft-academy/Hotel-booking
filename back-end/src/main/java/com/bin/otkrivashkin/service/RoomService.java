package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;

import java.util.List;

public interface RoomService {

//    boolean saveRoom(Room room);

    Room getRoom(Room room) throws NotFoundException, DataManagerException;

    Room getRoomByType(RoomType roomType) throws DataManagerException;

    boolean editRoom(Room room, int id) throws DataManagerException;

    boolean editRoom(Room room);

    boolean addRoom(Room room) throws DataManagerException;

    Room getRoomById(int id);

    List<Room> getRooms() throws DataManagerException;

    boolean deleteRoomById(int roomId) throws Exception;

    void addRooms(List<Room> rooms);

    void printTypes();

    void addRoomsByCount(int count, RoomType type);

    RoomType getRoomType(int typeOfRoom);

    void printRooms() throws DataManagerException;

    List<RoomType> getRoomTypes();
}
