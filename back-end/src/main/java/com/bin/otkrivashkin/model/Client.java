package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.WrongArgumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class Client implements Validator {

	private int clientId;
	private String firstName;
	private String lastName;
	private String startDate;
	private String endDate;
	private int roomNumber;

	public Client() {
	}

	public Client(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Client(String firstName, String lastName, String startDate, String endDate) {
		this(firstName, lastName);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getClientId() {
		return clientId;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String date) {
		this.endDate = date;
	}

	public void setStartDate() {
		this.startDate = LocalDate.now().toString();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setFirstName(String firstName) throws WrongArgumentException {
		if (firstName.length() < 3) throw new WrongArgumentException("First name is too short!");
		this.firstName = firstName;
	}

	public void setLastName(String lastName) throws WrongArgumentException {
		if (firstName.length() < 3) throw new WrongArgumentException("Last name is too short!");
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Client{" +
				firstName + ',' +
				lastName + ',' +
				startDate + ',' +
				endDate  + ',' +
				roomNumber +
				'}';
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Client)) return false;

		Client client = (Client) o;

		if (getClientId() != client.getClientId()) return false;
		if (!getFirstName().equals(client.getFirstName())) return false;
		if (!getLastName().equals(client.getLastName())) return false;
		if (getStartDate() != null ? !getStartDate().equals(client.getStartDate()) : client.getStartDate() != null)
			return false;
		return getEndDate() != null ? getEndDate().equals(client.getEndDate()) : client.getEndDate() == null;
	}

	@Override
	public Map<String, String> validate() {
		Map<String, String> res = new HashMap<>();

		if (firstName.length() < 4) res.put(firstName, "The first name must be longer than 4 chars!");
		if (firstName.length() > 15) res.put(firstName, "The first name is too long! Maximum is 14!");
		if (lastName.length() < 4) res.put(lastName, "The last name is too short! Minimum length is 4!");
		if (firstName.length() > 15) res.put(firstName, "The first name is too long! Maximum is 14!");

		return res;
	}
}
