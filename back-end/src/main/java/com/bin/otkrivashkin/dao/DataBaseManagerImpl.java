package com.bin.otkrivashkin.dao;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.util.DataManager;
import com.bin.otkrivashkin.util.JsonDataManager;
import com.bin.otkrivashkin.util.LogManager;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataBaseManagerImpl implements DataManager {

	private LogManager log = LogManager.getLogger(JsonDataManager.class);

	final String URL = "jdbc:postgresql://localhost:5432/hotel";
	final String USER = "postgres";
	final String PASSWORD = "postgres";

	private Connection connection;

	@PostConstruct
	private void init() throws DataManagerException {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e.getMessage());
			throw new DataManagerException(e.getMessage());
		}

	}
	public void saveRoom(Room room) throws DataManagerException {

		try {
			PreparedStatement statement = connection
					.prepareStatement("insert into rooms (type, price, available) values(?,?,?);");
			statement.setString(1, room.getType().toString());
			statement.setDouble(2, room.getPrice());
			statement.setBoolean(3, room.isAvailable());
			statement.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DataManagerException(e1.getMessage());
		}
	}


	public List<Room> getAllRooms() throws DataManagerException {
		List<Room> rooms = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM rooms");

			while (rs.next()) {
				rooms.add(new Room(rs.getInt("room_id") ,RoomType.valueOf(rs.getString("type")),
						rs.getDouble("price"), rs.getBoolean("available")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataManagerException(e.getMessage());
		}
		return rooms;
	}

	public boolean deleteRoom(int id) throws DataManagerException {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM rooms WHERE room_id = "+id);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataManagerException(e.getMessage());
		}
	}


	public void updateRoom (JSONObject object, int id) throws DataManagerException {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE rooms SET type = \'"+object.get("type").toString().toUpperCase()
					+"\', price = "+object.get("price")+", available = "+object.get("available")+" WHERE room_id = "+id);
		} catch (SQLException e) {
			e.printStackTrace();
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
