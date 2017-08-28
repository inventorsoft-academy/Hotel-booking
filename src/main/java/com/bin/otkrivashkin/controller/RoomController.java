package com.bin.otkrivashkin.controller;

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
@RequestMapping(value = "/rooms")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RoomController {

    private RoomService roomService;
    private FileManager fileManager;

    @GetMapping
    public ResponseEntity<List<Room>> getRooms() {
        if (roomService.getRooms() != null) {
            return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable int id) {
        if (roomService.getRoomById(id) != null) {
            return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        if (roomService.addRoom(room)) {
            fileManager.save();
            return new ResponseEntity<>(room, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRoomById(@PathVariable  int id) throws Exception {
        if (roomService.deleteRoomById(id)) {
            fileManager.save();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> editRoom(@RequestBody Room room, @PathVariable int id) {
        if (roomService.editRoom(room, id)) {
            fileManager.save();
            return new ResponseEntity<>(room, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/roomTypes")
    public ResponseEntity<List<RoomType>> getRoomTypes() {
        if (roomService.getRoomTypes() != null) {
            return new ResponseEntity<>(roomService.getRoomTypes(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
