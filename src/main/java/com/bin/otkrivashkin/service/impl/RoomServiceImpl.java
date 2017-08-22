package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.ChooseAnotherOneException;
import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private List<Room> rooms;

    public RoomServiceImpl() {
        this.rooms = new ArrayList<>();
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
    public void addRooms(int count, RoomType type) throws WrongNumberArgsException {
        if (count <= 0) throw new WrongNumberArgsException("the count must be greater than zero!");
        if (type != null) {
            for (int i = 0; i < count; i++) {
                int roomNumber = rooms.size() + 1;
                rooms.add(new Room(type, roomNumber));
            }
        }

    }

    @Override
    public Room getRoom(int numberOfRoom) throws Exception {

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
    public void editRoom(RoomType oldType, RoomType newType) throws NotFoundException, WrongArgumentException {
        if (newType == null) throw new WrongArgumentException("New type is wrong!");
        getRoom(oldType).setType(newType);

    }

    @Override
    public void editRoom(int oldNumberOfRoom, int newNumberOfRoom) throws Exception {

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
    public void deleteRoomByNumber(int numberOfRoom) throws Exception {
        boolean isRemoved = rooms.remove(getRoom(numberOfRoom));

    }

    @Override
    public void deleteFirstRoom(RoomType type) throws NotFoundException, WrongArgumentException {
        boolean isRemoved = rooms.remove(getRoom(type));
    }

    @Override
    public void deleteRooms(RoomType type) throws WrongArgumentException, NotFoundException {

        if (type == null) throw new WrongArgumentException("Error! Problem with type...");
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                rooms.remove(room);
            }
        }

    }

    @Override
    public void deleteRooms() {
        rooms.clear();
    }

    @Override
    public void addRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void printTypes() {
        List<RoomType> types = new ArrayList<>(Arrays.asList(RoomType.values()));
        for (int i = 0; i < types.size(); i++) {
            System.out.println(i + " " + types.get(i));
        }
    }

    @Override
    public RoomType getRoomType(int typeOfRoom) {
        RoomType type = RoomType.CHEAP;
        switch (typeOfRoom) {
            case 0:
                type = RoomType.LUX;
                break;
            case 1:
                type = RoomType.CHEAP;
                break;
            case 2:
                type = RoomType.PRESIDENT;
                break;
            case 3:
                type = RoomType.COUNTRY;
                break;
            case 4:
                type = RoomType.INDIAN;
                break;
            default:
                System.out.println("wrong argument!");
        }
        return type;
    }

    @Override
    public Room editRoom(int roomNUmber) {

        return null;
    }

    @Override
    public void printRooms() {
        for (Room room : rooms) {
            System.out.println(room.toString());
        }
    }

    @Override
    public void deleteRoom(int roomNumber) {

    }

    @Override
    public int getRoomId(Room room) {
        return 0;
    }

    @Override
    public void setRoom(int roomId, Room room) {

    }

    ;

}
