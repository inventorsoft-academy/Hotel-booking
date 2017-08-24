package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.ChooseAnotherOneException;
import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private List<Room> rooms;

    public RoomServiceImpl() {
        this.rooms = new ArrayList<>();
    }

    @Override
    public void addRoom(Room room) {
        if (room.validate().isEmpty()) {
            rooms.add(room);
        }
    }

    @Override
    public Room getRoomById(int roomId) throws NotFoundException {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        throw new NotFoundException("room does not exist.");

    }

    @Override
    public boolean editRoom(Room room) {
        for (Room roomToUpdate : rooms) {
            if (roomToUpdate.getRoomId() == room.getRoomId()) {
                int indexOf = rooms.indexOf(roomToUpdate);
                room.setRoomId(roomToUpdate.getRoomId());
                rooms.set(indexOf, room);
                return true;
            }
        }
        return false;
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
    public Room getRoomByType(RoomType type) {
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public void deleteRoomById(int roomId) throws Exception {
        rooms.remove(getRoomById(roomId));
    }

    @Override
    public void addRooms(List<Room> rooms) {
        this.rooms.addAll(rooms);
    }

    @Override
    public void printTypes() {
        List<RoomType> types = new ArrayList<>(Arrays.asList(RoomType.values()));
        for (int i = 0; i < types.size(); i++) {
            System.out.println(i + " " + types.get(i));
        }
    }

    @Override
    public void addRoomsByCount(int count, RoomType type) {
        for (int i = 0; i < count; i++) {
            rooms.add(new Room(type));
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
    public void printRooms() {

    }



}
