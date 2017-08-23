package com.bin.otkrivashkin.controller;


import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/booking")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ModelAndView showRooms(ModelAndView modelAndView) {
        modelAndView.addObject("rooms", roomService.getRooms());
        modelAndView.setViewName("rooms");
        return modelAndView;
    }

    @GetMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Room> getRooms() {

//        List<Room> rooms = Arrays.asList(
//                new Room(RoomType.LUX, 5544.3, true),
//                new Room(RoomType.COUNTRY, 5544.3, true),
//                new Room(RoomType.LUX, 5544.3, true),
//                new Room(RoomType.LUX, 5544.3, true),
//                new Room(RoomType.LUX, 5544.3, true)
//        );
//        roomService.addRooms(rooms);

        return roomService.getRooms();
    }


    @GetMapping("/rooms/{id}")
    @ResponseBody
    public Room getRoomById(@PathVariable int id) throws NotFoundException {
        return roomService.getRoomById(id);
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.PUT)
    public @ResponseBody Room addRoom(@ModelAttribute Room room) {
        roomService.addRoom(room);
        return room;
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<Room> deleteRoomById(@PathVariable int id) throws Exception {
        roomService.deleteRoomById(id);

        return roomService.getRooms();
    }










}
