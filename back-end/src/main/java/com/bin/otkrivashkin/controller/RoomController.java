package com.bin.otkrivashkin.controller;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.util.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/rooms")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RoomController {

	private LogManager log = LogManager.getLogger(RoomController.class);

	@Autowired
	private RoomService roomService;

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "INTERNAL SERVER ERROR")
	@ExceptionHandler(DataManagerException.class)
	public void badResponse(Exception e) {
		log.error(e.getMessage());
	}

	@GetMapping
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
	public ResponseEntity addRoom(@RequestBody Room room) throws DataManagerException {
		if (roomService.addRoom(room)) {
			return new ResponseEntity(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteRoomById(@PathVariable int id) throws Exception {
		if (roomService.deleteRoomById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Room> editRoom(@RequestBody Room room, @PathVariable int id) throws DataManagerException {
		room.setRoomId(id);
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

	@GetMapping("/available")
	public ResponseEntity<List<Room>> getAvailableRooms() {
		if (roomService.getAvailableRooms() != null) {
			return new ResponseEntity<>(roomService.getAvailableRooms(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
