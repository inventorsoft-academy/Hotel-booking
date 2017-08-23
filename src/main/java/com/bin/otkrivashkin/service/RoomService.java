package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.ChooseAnotherOneException;
import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.model.Room;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.util.List;

public interface RoomService {

    void addRoom(Room room);

    Room getRoom(Room room) throws NotFoundException;

    Room getRoomByType(RoomType roomType);

    Room getRoomById(int id) throws NotFoundException;

    boolean editRoom(Room room) throws NotFoundException;

    List<Room> getRooms();

    void deleteRoomById(int roomId) throws Exception;

    void addRooms(List<Room> rooms);

    void printTypes();

    void addRoomsByCount(int count, RoomType type);

    RoomType getRoomType(int typeOfRoom);

    void printRooms();
}
