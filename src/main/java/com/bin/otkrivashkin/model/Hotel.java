package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.*;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Hotel implements RoomService, Validator {

    private String name;
    private List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public Hotel(String name) {
        this.name = name;
        rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws WrongNumberArgsException {
        if (name.length() <= 3) throw new WrongNumberArgsException("The name of the hotel is too short, make it longer.");
        this.name = name;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms() {

        List<Room> temp = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                temp.add(room);
            }
        }
        return temp;
    }

    @Override
    public void addRoom(Room room) {

        if (room.validate().isEmpty()) {
            int roomNumber = rooms.size() + 1;
            room.setNumber(roomNumber);
            rooms.add(room);
        }

    }

    @Override
    public void addRoom(RoomType type) {

        if (type != null) {
            int roomNumber = rooms.size() + 1;
            rooms.add(new Room(type, roomNumber));
        }

    }

    @Override
    public void addRoom(RoomType type, int number) {

        if (type != null && number > 0) {
            int roomNumber = rooms.size() + 1;
            rooms.add(new Room(type, roomNumber));
        }
    }

    @Override
    public void addRooms(int countOfRooms, RoomType type) throws WrongNumberArgsException {

        if (countOfRooms <= 0) throw new WrongNumberArgsException("the count must be greater than zero!");
        if (type != null) {
            for (int i = 0; i < countOfRooms; i++) {
                int roomNumber = rooms.size() + 1;
                rooms.add(new Room(type, roomNumber));
            }
        }
    }

    @Override
    public Room getRoom(int numberOfRoom) throws WrongNumberArgsException, NotFoundException {

        if (numberOfRoom <= 0) throw new WrongNumberArgsException("the number must be greater than zero!");
        for (Room room : rooms) {
            if (room.getNumber() == numberOfRoom) {
                return room;
            }
        }
        throw new NotFoundException("room does not exist.");
    }

    @Override
    public Room getRoom(double price) throws NegativePriceException, NotFoundException {
        if (price <= 0) throw new NegativePriceException("The price must be greater than zero!");
        for (Room room : rooms) {
            if (room.getPrice() == price) {
                return room;
            }
        }
        throw new NotFoundException("Room with price " + price + "does not exist");
    }

    @Override
    public Room getRoom(RoomType type) throws WrongArgumentException, NotFoundException {

        if (type == null) throw new WrongArgumentException("The room type doesn't selected.");

        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                return room;
            }
        }
        throw new NotFoundException("Room with type " + type + "not found.");
    }

    @Override
    public Room getRoom(Room room) throws NotFoundException {
        if (room.validate().isEmpty()) {
            for (Room groom : rooms) {
                if (groom.equals(room)) {
                    return groom;
                }
            }
        }
        throw new NotFoundException(room.validate().values().stream().findAny().get());
    }

    @Override
    public void editRoom(RoomType oldType, RoomType newType) throws NotFoundException, WrongArgumentException {
        if (newType == null) throw new WrongArgumentException("New type is wrong!");
        getRoom(oldType).setType(newType);
    }

    @Override
    public void editRoom(int oldNumberOfRoom, int newNumberOfRoom) throws NotFoundException, WrongNumberArgsException, ChooseAnotherOneException {

        if (newNumberOfRoom <= 0 || oldNumberOfRoom <= 0) throw new WrongNumberArgsException("Error! Number of room must be greater than zero!");

        Room room = getRoom(newNumberOfRoom);

        if (room != null) throw new ChooseAnotherOneException("Room with number " + newNumberOfRoom + " is already exist, choose another one.");
        getRoom(oldNumberOfRoom).setNumber(newNumberOfRoom);
    }

    @Override
    public void editRooms(RoomType oldType, RoomType newType) throws WrongArgumentException {

        if (oldType == null || newType == null) throw new WrongArgumentException("Error! Wrong type!");

        for (Room room : rooms) {
            if (room.getType().equals(oldType)) {
                room.setType(newType);
            }
        }
    }

    @Override
    public void deleteFirstRoom(int numberOfRoom) throws NotFoundException, WrongNumberArgsException {

        boolean isRemoved = rooms.remove(getRoom(numberOfRoom));
    }

    @Override
    public void deleteFirstRoom(RoomType type) throws NotFoundException, WrongArgumentException {

        boolean isRemoved = rooms.remove(getRoom(type));
    }

    @Override
    public void deleteRooms(RoomType deletedRoomType) throws WrongArgumentException, NotFoundException {

        if (deletedRoomType == null) throw new WrongArgumentException("Error! Problem with type...");
        for (Room room : rooms) {
            if (room.getType().equals(deletedRoomType)) {
                rooms.remove(room);
            }
        }
    }

    @Override
    public void deleteRooms() {
            rooms.clear();
    }

    @Override
    public Map<String, String> validate() {
        Map<String,String> res = new HashMap<>();

        if (name.length() < 3) res.put(name, "The hotel name is too short! Make it longer.");
        if (name.length() > 16) res.put(name, "The hotel name is too long! Maximum is 16 chars!");

        return res;
    }

    public void addRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}
