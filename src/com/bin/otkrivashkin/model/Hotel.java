package com.bin.otkrivashkin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {

    private String name;
    private List<Room> rooms;
    private Map<Room, Client> roomClientMap;

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

    public Room getRoomByNumber(int number) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumber() == number) {
                return getRooms().get(i);
            }
        }
        return null;
    }


    public void addClient(Client client, int numberOfRoom) {
        Room room = getRoomByNumber(numberOfRoom);
        if (room.isAvailable()) {
            roomClientMap.put(room, client);
            System.out.println("Client was added to the hotel!");
        }
        else {
            System.out.println("Sorry, this room is not available, choose another one.");
        }

    }
}
