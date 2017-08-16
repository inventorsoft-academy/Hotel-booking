package com.bin.otkrivashkin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hotel {

    private String name;
    private List<Room> rooms;
    private List<Client> clients;

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

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void saveRoom(RoomType type, double price) {
        Room room = new Room(type, price);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }

    public void addRooms(int countOfRooms, RoomType type) {
        for (int i = 0; i < countOfRooms; i++) {
            rooms.add(new Room(type));
        }
    }

    public void editTypeOfRooms(RoomType newRoomType) {
        for (Room room : rooms) {
            room.setType(newRoomType);
        }
    }

    public void deleteRooms(RoomType deletedRoomType) {
        for (Room room : rooms) {
            if (room.getType().equals(deletedRoomType)) {
                rooms.remove(room);
            }
        }
    }


}
