package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.dao.DataBaseManagerImpl;
import com.bin.otkrivashkin.util.LogManager;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

	private LogManager logManager = LogManager.getLogger(RoomServiceImpl.class);

	private final DataBaseManagerImpl fileManager;

	private List<Room> rooms;

	@Autowired
	public RoomServiceImpl(DataBaseManagerImpl fileManager) throws DataManagerException {
		this.fileManager = fileManager;
		this.rooms = fileManager.getAllRooms();
	}

	@Override
	public boolean addRoom(Room room) throws DataManagerException {
		if (room.validate().isEmpty()) {
			room.setRoomId(rooms.size());
			rooms.add(room);
			fileManager.saveRoom(room);
			return true;
		}
		return false;
	}

	@Override
	public Room getRoomById(int roomId) {
		return rooms.stream()
				.filter(r -> r.getRoomId() == roomId).findFirst().get();
	}

	@Override
	public boolean editRoom(Room room) {
		for (Room roomToUpdate : rooms) {
			if (roomToUpdate.getRoomId() == room.getRoomId()) {
				int indexOf = rooms.indexOf(roomToUpdate);
				room.setRoomId(roomToUpdate.getRoomId());
				rooms.set(indexOf, room);
				return true;
			}
		}
		return false;
	}

	@Override
	public Room getRoom(Room room) throws NotFoundException, DataManagerException {
		if (!room.validate().isEmpty()) throw new NotFoundException(room.validate().values().stream().findAny().get());
		return fileManager.getAllRooms().stream().filter(r -> r.equals(room)).findFirst().get();
	}

	@Override
	public Room getRoomByType(RoomType type) throws DataManagerException {
		return fileManager.getAllRooms().stream().filter(r -> r.getType().equals(type)).findFirst().get();
	}

	@Override
	public boolean editRoom(Room room, int id) throws DataManagerException {
		Room roomById = getRoomById(id);
			if (roomById != null) {
			fileManager.updateRoom(room, id);
			return true;
		}
		return false;

	}

	@Override
	public List<Room> getRooms() throws DataManagerException {
		return fileManager.getAllRooms();
	}

	@Override
	public boolean deleteRoomById(int roomId) throws Exception {
		return	fileManager.deleteRoom(roomId);
	}

	@Override
	public void addRooms(List<Room> rooms) {
		this.rooms.addAll(rooms);
	}

	@Override
	public void printTypes() {
		Arrays.stream(RoomType.values()).forEach(System.out::println);
	}

	@Override
	public void addRoomsByCount(int count, RoomType type) {
		for (int i = 0; i < count; i++) {
			rooms.add(new Room(type));
		}

	}

	@Override
	public RoomType getRoomType(int typeOfRoom) {
		RoomType type = RoomType.CHEAP;
		switch (typeOfRoom) {
			case 0:
				type = RoomType.LUX;
				break;
			case 1:
				type = RoomType.CHEAP;
				break;
			case 2:
				type = RoomType.PRESIDENT;
				break;
			case 3:
				type = RoomType.COUNTRY;
				break;
			case 4:
				type = RoomType.INDIAN;
				break;
			default:
				System.out.println("wrong argument!");
		}
		return type;
	}

	@Override
	public void printRooms() throws DataManagerException {
		System.out.println(String.format("%10s %15s %15s %10s", "ID", "TYPE", "PRICE/DAY", "BUSY?"));
		for (Room room : fileManager.getAllRooms()) {
			System.out.println(String.format("%10s %15s %15s %10s",
					room.getRoomId(),
					room.getType(),
					room.getPrice(),
					room.isAvailable() ? "NO" : "YES"
			));
		}
	}

	@Override
	public List<RoomType> getRoomTypes() {
		return Arrays.asList(RoomType.values());
	}


}
