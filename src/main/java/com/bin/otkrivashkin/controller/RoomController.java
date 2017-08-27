package com.bin.otkrivashkin.controller;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.util.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/json")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RoomController {

    private RoomService roomService;
    private FileManager fileManager;

    @GetMapping(value = "/rooms")
    public List<Room> getRooms() { return roomService.getRooms();
    }

    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable int id) throws NotFoundException {
        return roomService.getRoomById(id);
    }

    @PostMapping("/rooms")
    public void addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Boolean> deleteRoomById(@PathVariable("id")  int id) throws Exception {
        roomService.deleteRoomById(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    @PutMapping("/rooms/{id}")
    public void editRoom(@RequestBody Room room, @PathVariable int id) {
        roomService.editRoom(room, id);
    }

    @GetMapping("/rooms/roomTypes")
    public List<RoomType> getRoomTypes() {
        return roomService.getRoomTypes();
    }
}
