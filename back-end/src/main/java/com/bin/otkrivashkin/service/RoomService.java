package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;

import java.util.List;

public interface RoomService {

    List<Room> getAvailableRooms();

    boolean editRoom(Room room, int id) throws DataManagerException;

    boolean addRoom(Room room) throws DataManagerException;

    Room getRoomById(int id);

    List<Room> getRooms() throws DataManagerException;

    boolean deleteRoomById(int roomId) throws Exception;


    List<RoomType> getRoomTypes();
}
