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

    void addRoom(RoomType type);

    void addRoom(RoomType type, int number);

    void addRooms(int count, RoomType type) throws WrongNumberArgsException;

    Room getRoom(int numberOfRoom) throws Exception;

    Room getRoom(double price) throws NegativePriceException, NotFoundException;

    Room getRoom(RoomType type) throws WrongArgumentException, NotFoundException;

    Room getRoom(Room room) throws NotFoundException;

    List<Room> getRooms();

    List<Room> getAvailableRooms();



    void editRoom(RoomType oldType, RoomType newType) throws NotFoundException, WrongArgumentException;

    void editRoom(int oldNumberOfRoom, int newNumberOfRoom) throws Exception;

    void editRooms(RoomType oldType, RoomType newType) throws WrongArgumentException;

    void deleteRoomByNumber(int numberOfRoom) throws Exception;

    void deleteFirstRoom(RoomType type) throws NotFoundException, WrongArgumentException;

    void deleteRooms(RoomType type) throws WrongArgumentException, NotFoundException;

    void deleteRooms();

    void addRooms(List<Room> rooms);

    void printTypes();

    RoomType getRoomType(int typeOfRoom);

    Room editRoom(int roomNUmber);

    void printRooms();

    void deleteRoom(int roomNumber);

    int getRoomId(Room room);

    void setRoom(int roomId, Room room);
}
