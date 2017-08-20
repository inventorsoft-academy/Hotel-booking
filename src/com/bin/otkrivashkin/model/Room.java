package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.ChooseAnotherOneException;
import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.impl.RoomImpl;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.util.List;

public interface Room {



    void addRoom(RoomImpl room);

    void addRoom(RoomType type);

    void addRoom(RoomType type, int number);

    void addRooms(int count, RoomType type) throws WrongNumberArgsException;

    RoomImpl getRoom(int numberOfRoom) throws Exception;

    RoomImpl getRoom(double price) throws NegativePriceException, NotFoundException;

    RoomImpl getRoom(RoomType type) throws WrongArgumentException, NotFoundException;

    RoomImpl getRoom(RoomImpl room) throws NotFoundException;

    List<RoomImpl> getRooms();

    List<RoomImpl> getAvailableRooms();



    void editRoom(RoomType oldType, RoomType newType) throws NotFoundException, WrongArgumentException;

    void editRoom(int oldNumberOfRoom, int newNumberOfRoom) throws NotFoundException, WrongNumberArgsException, ChooseAnotherOneException;

    void editRooms(RoomType oldType, RoomType newType) throws WrongArgumentException;

    void deleteFirstRoom(int numberOfRoom) throws NotFoundException, WrongNumberArgsException;

    void deleteFirstRoom(RoomType type) throws NotFoundException, WrongArgumentException;

    void deleteRooms(RoomType type) throws WrongArgumentException, NotFoundException;

    void deleteRooms();
}
