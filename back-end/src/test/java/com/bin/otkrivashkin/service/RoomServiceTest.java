package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RoomServiceTest {
    @Autowired
    private RoomService roomService;
    private Room globalRoom;

    @Before
    public void setUp() throws Exception {
        globalRoom = new Room(1,RoomType.COUNTRY, 5543.2, true);
    }

    @Test
    public void addRoom() throws Exception {
        roomService.addRoom(globalRoom);
        Room room = roomService.getRoom(globalRoom);
        assertEquals(room, globalRoom);
    }

    @Test
    public void getRoom() throws Exception {
        roomService.addRoom(globalRoom);
        Room room = roomService.getRoom(globalRoom);
        assertEquals(globalRoom, room);
    }

    @Test
    public void getRoomById() throws Exception {
        int roomId = globalRoom.getRoomId();
        roomService.addRoom(globalRoom);
        Room room = roomService.getRoom(globalRoom);
        assertEquals(roomId, room.getRoomId());
    }

    @Test
    public void getRoomByType() throws Exception {

        roomService.addRoom(globalRoom);

        RoomType type = globalRoom.getType();

        assertEquals(globalRoom, roomService.getRoomByType(type));

    }

    @Test
    public void editRoom() throws Exception {
        addRoom();
        int roomId = globalRoom.getRoomId();

        Room room = roomService.getRoomById(roomId);

        room.setAvailable(false);
        room.setPrice(7777.7);
        room.setType(RoomType.LUX);

        assertEquals(true, roomService.addRoom(room));
    }

    @Test
    public void getRooms() throws Exception {
        addRoom();
        addRoom();
        addRoom();

        List<Room> rooms = roomService.getRooms();

        assertEquals(3, rooms.size());

    }

    @Test
    public void deleteRoomById() throws Exception {
        addRoom();
        int roomId = globalRoom.getRoomId();
        roomService.deleteRoomById(roomId);

        assertEquals(0, roomService.getRooms().size());

        addRoom();
        addRoom();

        assertEquals(2, roomService.getRooms().size());
    }

    @Test
    public void addRooms() {

    }

    @Test
    public void addRoomsByCount() throws WrongNumberArgsException, WrongArgumentException {

    }

    @Test
    public void editRoomById() throws NotFoundException {


    }


}