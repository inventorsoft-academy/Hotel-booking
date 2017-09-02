package com.bin.otkrivashkin.controller;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/rooms")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping
	@ExceptionHandler(DataManagerException.class)
	public ResponseEntity<List<Room>> getRooms() throws DataManagerException {
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
	@ExceptionHandler(DataManagerException.class)
	public ResponseEntity addRoom(@RequestBody Room room) throws DataManagerException {
		if (roomService.addRoom(room)) {
			return new ResponseEntity(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Boolean> deleteRoomById(@PathVariable int id) throws Exception {
		if (roomService.deleteRoomById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(DataManagerException.class)
	public ResponseEntity<JSONObject> editRoom(@RequestBody JSONObject room, @PathVariable int id) throws DataManagerException {
		if (roomService.editRoom(room, id)) {
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
