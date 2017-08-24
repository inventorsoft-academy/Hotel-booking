package com.bin.otkrivashkin.controller;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.service.RoomService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/json")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/rooms")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping(value = "/rooms/{id}")
    public Room getRoomById(@PathVariable int id) throws NotFoundException {
        return roomService.getRoomById(id);
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public Room addRoom(@ModelAttribute Room room) {
        roomService.addRoom(room);
        return room;
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
    public List<Room> deleteRoomById(@PathVariable int id) throws Exception {
        roomService.deleteRoomById(id);
        return roomService.getRooms();
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.PUT)
    public void editRoom(@ModelAttribute Room room) {
        roomService.editRoom(room);
    }










}
