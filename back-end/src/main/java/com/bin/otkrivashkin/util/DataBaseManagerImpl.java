package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataBaseManagerImpl implements FileManager {

	private LogManager log = LogManager.getLogger(JsonFileManager.class);

	final URL CREATE_TABLE = DataBaseManagerImpl.class
			.getClassLoader().getResource("sql\\sql_init.sql");

	final String URL = "jdbc:postgresql://localhost:5432/hotel";
	final String USER = "postgres";
	final String PASSWORD = "postgres";

	public DataBaseManagerImpl() {

	}


	void dev() {


	}


	public void saveRoom(Room room) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			PreparedStatement statement = connection
					.prepareStatement("insert into rooms (type, price, available) values(?,?,?);");
			statement.setString(1, room.getType().toString());
			statement.setDouble(2, room.getPrice());
			statement.setBoolean(3, room.isAvailable());
			statement.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}


	public List<Room> getAllRooms() {
		List<Room> rooms = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM rooms");

			while (rs.next()) {
				rooms.add(new Room(RoomType.valueOf(rs.getString("type")),
						rs.getDouble("price"), rs.getBoolean("available")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}

	@Override
	public void save() {


	}

	@Override
	public void load() {

	}
}
