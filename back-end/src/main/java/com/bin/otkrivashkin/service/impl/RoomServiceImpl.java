package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.dao.RoomDAO;
import com.bin.otkrivashkin.util.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

	private LogManager logManager = LogManager.getLogger(RoomServiceImpl.class);

	private final RoomDAO fileManager;



	@Autowired
	public RoomServiceImpl(RoomDAO fileManager) throws DataManagerException {
		this.fileManager = fileManager;
	}

	public List<Room> getAvailableRooms(){
		return fileManager.getAvailableRooms();
	}

	@Override
	public boolean addRoom(Room room) throws DataManagerException {
		if (room.validate().isEmpty()) {
			fileManager.saveRoom(room);
			return true;
		}
		return false;
	}

	@Override
	public Room getRoomById(int roomId) {
		return fileManager.getRoomById(roomId);
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
	public List<RoomType> getRoomTypes() {
		return Arrays.asList(RoomType.values());
	}


}
