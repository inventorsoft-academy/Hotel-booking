package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by otkrivashkin on 08.08.2017.
 */
public class RoomService {

    private static int id;
    private Map<Integer, Room> rooms;

    public RoomService() {
        this.rooms = new HashMap<>();
    }

    public void create(Room room) {
        id++;
        rooms.put(id, room);
    }

    public Room getRoomById(int id) {
        return rooms.get(id);
    }

    public void update(Room room, int id) {
        rooms.replace(id, room);
    }

    public void delete(int id) {
        rooms.remove(id);
    }


}
