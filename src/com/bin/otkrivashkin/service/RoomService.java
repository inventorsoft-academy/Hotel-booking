package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomService implements RoomServiceInterface {

    private List<Room> rooms;

    public RoomService() {
        this.rooms = new ArrayList<>();
    }

    public List<RoomType> getTypes() {
        RoomType[] values = RoomType.values();
        return new ArrayList<>(Arrays.asList(values));
    }

    public void createRooms(int countOfRooms, RoomType type, double price) {
        for (int i = 0; i < countOfRooms; i++) {
            rooms.add(new Room(type, price));
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void editTypeOfRooms(RoomType oldType, RoomType newType) {
        List<Room> roomsByOldType = getRoomsByType(oldType);
        for (Room room: rooms){
            room.setType(newType);
        }
        rooms.retainAll(roomsByOldType);

    }

    public List<Room> getRoomsByType(RoomType type) {
        List<Room> roomsByType = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                roomsByType.add(room);
            }
        }
        return roomsByType;
    }

    public void deleteRooms(RoomType deletedRoomType) {
        List<Room> roomsByType = getRoomsByType(deletedRoomType);
        rooms.removeAll(roomsByType);
    }
}
