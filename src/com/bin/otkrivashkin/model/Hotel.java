package com.bin.otkrivashkin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {

    private String name;
    private Map<Integer, Room> rooms;
    private List<Client> clients;

    public Hotel(String name, int numOfRooms) {
        this.name = name;
        this.rooms = fillHotel(numOfRooms);
        this.clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    public void setRooms(Map<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    private Map<Integer, Room> fillHotel(int numOfRooms) {
        Map<Integer, Room> rooms = new HashMap<>();
        for (int i = 1; i < numOfRooms;) {
            if (i % 2 == 0) {
                rooms.put(i, new Room(Type.CHEAP, (i + 2 * 2), i + 1));
                i++;
            }
            if (i % 3 == 0) {
                rooms.put(i, new Room(Type.MEDIUM, ((i + 2 * 4)), i + 2));
                i++;
            } else {
                rooms.put(i, new Room(Type.LUX, (i + 2 * 8), i + 3));
                i++;
            }
        }
        return rooms;
    }

    public void add(Client client, int numberOfRoom) {
        if (rooms.get(numberOfRoom).isAvailable()) {
            clients.add(client);
            rooms.get(numberOfRoom).setAvailable(false);
            System.out.println("Client is reserved.");
        }
        else {
            System.out.println("Sorry, this room is not available, choose another room.");
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ",\n rooms=" + rooms +
                ",\n clients=" + clients +
                '}';
    }
}
