package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.ChooseAnotherOneException;
import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.util.List;

public interface RoomInterface {

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

    void editRoom(int oldNumberOfRoom, int newNumberOfRoom) throws NotFoundException, WrongNumberArgsException, ChooseAnotherOneException;

    void editRooms(RoomType oldType, RoomType newType) throws WrongArgumentException;

    void deleteFirstRoom(int numberOfRoom) throws NotFoundException, WrongNumberArgsException;

    void deleteFirstRoom(RoomType type) throws NotFoundException, WrongArgumentException;

    void deleteRooms(RoomType type) throws WrongArgumentException, NotFoundException;

    void deleteRooms();
}
