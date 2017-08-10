package com.bin.otkrivashkin.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String name;
    private List<Room> rooms;



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

    public void saveRoom(String type, double price) {
        Room room = new Room(type, price);

    }


    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
