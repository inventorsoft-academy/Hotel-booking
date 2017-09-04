package com.bin.otkrivashkin.dao;

import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.util.ConnectionManager;
import com.bin.otkrivashkin.util.LogManager;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO {

	private LogManager log = LogManager.getLogger(ClientDAO.class);

	private ConnectionManager connection;

	public ClientDAO(ConnectionManager connection) {
		this.connection = connection;
	}

	public boolean addClient(Client client) throws DataManagerException {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("INSERT INTO clients (first_name, last_name, start_date, end_date, room_number) VALUES(?,?,?,?,?)");
			statement.setString(1, client.getFirstName());
			statement.setString(2, client.getLastName());
			statement.setString(3, client.getStartDate());
			statement.setString(4, client.getEndDate());
			statement.setInt(5, client.getRoomNumber());
			statement.execute();
			return true;
		} catch (SQLException e1) {
			throw new DataManagerException(e1.getMessage());
		}
	}

	public List<Client> getAllClients() throws DataManagerException {
		List<Client> clients = new ArrayList<>();
		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM clients");
			while (rs.next()) {
				clients.add(new Client(rs.getInt("client_id"), rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("start_date"), rs.getString("end_date"),
						rs.getInt("room_number")));
			}
		} catch (SQLException e) {
			throw new DataManagerException(e.getMessage());
		}
		return clients;
	}

	public boolean deleteClientById(int id) throws DataManagerException{
		try {
			Statement statement = connection.getConnection().createStatement();
			statement.executeUpdate("DELETE FROM clients WHERE client_id = " + id);
			return true;
		} catch (SQLException e) {
			throw new DataManagerException(e.getMessage());
		}
	}

	public Client getClientById(int id){
		Client client = null;
		try {
			Statement statement = connection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM clients WHERE client_id = "+id);
			while (rs.next()) {
				client = new Client(rs.getInt("client_id"), rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("start_date"), rs.getString("end_date"),
						rs.getInt("room_number"));
			}
		} catch (SQLException e) {
			throw new DataManagerException(e.getMessage());
		}
		if(client == null){
			throw new DataManagerException("can not found client");
		}else {
			return client;
		}
	}

}
