package com.bin.otkrivashkin.dao;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.util.ConnectionManager;
import com.bin.otkrivashkin.util.DataManager;
import com.bin.otkrivashkin.util.JsonDataManager;
import com.bin.otkrivashkin.util.LogManager;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataBaseManagerImpl implements DataManager {

	private LogManager log = LogManager.getLogger(JsonDataManager.class);

	private ConnectionManager connection;

	public DataBaseManagerImpl(ConnectionManager connection) {
		this.connection = connection;
	}

	public void saveRoom(Room room) throws DataManagerException {

		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("INSERT INTO rooms (type, price, available) VALUES(?,?,?);");
			statement.setString(1, room.getType().toString());
			statement.setDouble(2, room.getPrice());
			statement.setBoolean(3, room.isAvailable());
			statement.execute();
		} catch (SQLException e1) {

			throw new DataManagerException(e1.getMessage());
		}
	}


	public List<Room> getAllRooms() throws DataManagerException {
		List<Room> rooms = new ArrayList<>();
		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM rooms");

			while (rs.next()) {
				rooms.add(new Room(rs.getInt("room_id"), RoomType.valueOf(rs.getString("type")),
						rs.getDouble("price"), rs.getBoolean("available")));
			}
		} catch (SQLException e) {
			throw new DataManagerException(e.getMessage());
		}
		return rooms;
	}

	public boolean deleteRoom(int id) throws DataManagerException {
		try {
			Statement statement = connection.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM rooms WHERE room_id = " + id);
			return true;
		} catch (SQLException e) {
			throw new DataManagerException(e.getMessage());
		}
	}


	public void updateRoom(Room room, int id) throws DataManagerException {
		try {
			PreparedStatement preparedStatement = connection.getConnection().prepareStatement("UPDATE rooms SET type = ?, price =?, available = ? WHERE room_id = ?");
			preparedStatement.setString(1, room.getType().toString());
			preparedStatement.setDouble(2, room.getPrice());
			preparedStatement.setBoolean(3, room.isAvailable());
			preparedStatement.setInt(4, room.getRoomId());
			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DataManagerException(e.getMessage());
		}
	}

	@Override
	public void save() {


	}

	@Override
	public void load() {

	}
}
