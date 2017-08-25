package com.bin.otkrivashkin.controller;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.util.FileManager;
import com.bin.otkrivashkin.util.JsonFileManager;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/json")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private FileManager fileManager;

    @GetMapping(value = "/rooms")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping(value = "/rooms/{id}")
    public Room getRoomById(@PathVariable int id) throws NotFoundException {
        return roomService.getRoomById(id);
    }

    @PostMapping(value = "/rooms")
    public void addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        fileManager.save();
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
    public void deleteRoomById(@PathVariable int id) throws Exception {
        roomService.deleteRoomById(id);
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.PUT)
    public void editRoom(@ModelAttribute Room room) {
        roomService.editRoom(room);
    }

    @GetMapping(value = "/roomTypes")
    public List<RoomType> getRoomTypes() {
        return roomService.getRoomTypes();
    }









}
