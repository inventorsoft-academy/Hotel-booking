package com.bin.otkrivashkin.model.impl;

import com.bin.otkrivashkin.exception.*;
import com.bin.otkrivashkin.model.*;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Hotel implements com.bin.otkrivashkin.model.Client, com.bin.otkrivashkin.model.Room, Booking, Validator {

    private Logger logger = Logger.getLogger(Hotel.class.getName());

    private String name;
    private List<Room> rooms;
    private List<Client> clients;
    private Map<Integer, Client> clientRoomMap;

    public Hotel() {
        rooms = new ArrayList<>();
        clients = new ArrayList<>();
        clientRoomMap = new HashMap<>();
        logger.info(Hotel.class.getName() + " was created.");
    }

    public Hotel(String name) {
        this.name = name;
        rooms = new ArrayList<>();
        clients = new ArrayList<>();
        clientRoomMap = new HashMap<>();
        logger.info(Hotel.class.getName() + " was created.");
    }

    public Map<Integer, Client> getClientRoomMap() {
        logger.info("map of rooms via clients.");
        return clientRoomMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws WrongNumberArgsException {
        if (name.length() <= 3) throw new WrongNumberArgsException("The name of the hotel is too short, make it longer.");
        this.name = name;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms() {

        List<Room> temp = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                temp.add(room);
            }
        }
        return temp;
    }

    @Override
    public void addRoom(Room room) {

        if (room.validate().keySet().isEmpty()) {
            int roomNumber = rooms.size() + 1;
            room.setNumber(roomNumber);
            rooms.add(room);
            logger.info("room was added.");
        }
        else {
            logger.info("Error! Room is not added!");
        }

    }

    @Override
    public void addRoom(RoomType type) {

        if (type != null) {
            int roomNumber = rooms.size() + 1;
            rooms.add(new Room(type, roomNumber));
            logger.info("room was added.");
        }
        else {
            logger.info("Error! Room is not added!");
        }

    }

    @Override
    public void addRoom(RoomType type, int number) {

        if (type != null && number > 0) {
            int roomNumber = rooms.size() + 1;
            rooms.add(new Room(type, roomNumber));
            logger.info("room was added.");
        }
        else {
            logger.info("Error! Room is not added!");
        }
    }

    @Override
    public void addRooms(int countOfRooms, RoomType type) throws WrongNumberArgsException {

        if (countOfRooms <= 0) throw new WrongNumberArgsException("the count must be greater than zero!");
        if (type != null) {
            for (int i = 0; i < countOfRooms; i++) {
                int roomNumber = rooms.size() + 1;
                rooms.add(new Room(type, roomNumber));
            }
            logger.info("room(s) was(were) added!");
        }
        else {
            logger.info("Error! Room(s) is(are) not added!");
        }
    }

    @Override
    public Room getRoom(int numberOfRoom) throws WrongNumberArgsException, NotFoundException {

        if (numberOfRoom <= 0) throw new WrongNumberArgsException("the number must be greater than zero!");
        for (Room room : rooms) {
            if (room.getNumber() == numberOfRoom) {
                logger.info("Success! Room was found.");
                return room;
            }
        }
        throw new NotFoundException("room does not exist.");
    }

    @Override
    public Room getRoom(double price) throws NegativePriceException, NotFoundException {
        if (price <= 0) throw new NegativePriceException("The price must be greater than zero!");
        for (Room room : rooms) {
            if (room.getPrice() == price) {
                logger.info("room with price " + price + " was found!");
                return room;
            }
        }
        throw new NotFoundException("Room with price " + price + "does not exist");
    }

    @Override
    public Room getRoom(RoomType type) throws WrongArgumentException, NotFoundException {

        if (type == null) throw new WrongArgumentException("The room type doesn't selected.");

        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                logger.info("The room with type " + type + " returned.");
                return room;
            }
        }
        throw new NotFoundException("Room with type " + type + "not found.");
    }

    @Override
    public Room getRoom(Room room) throws NotFoundException {
        if (room.validate().keySet().isEmpty()) {
            for (Room groom : rooms) {
                if (groom.equals(room)) {
                    logger.info("Success! Room was returned.");
                    return groom;
                }
            }
        }
        throw new NotFoundException(room.validate().values().stream().findAny().get());
    }

    @Override
    public void editRoom(RoomType oldType, RoomType newType) throws NotFoundException, WrongArgumentException {
        if (newType == null) throw new WrongArgumentException("New type is wrong!");
        getRoom(oldType).setType(newType);
        logger.info("Room was updated!");
    }

    @Override
    public void editRoom(int oldNumberOfRoom, int newNumberOfRoom) throws NotFoundException, WrongNumberArgsException, ChooseAnotherOneException {

        if (newNumberOfRoom <= 0 || oldNumberOfRoom <= 0) throw new WrongNumberArgsException("Error! Number of room must be greater than zero!");

        Room room = getRoom(newNumberOfRoom);

        if (room != null) throw new ChooseAnotherOneException("Room with number " + newNumberOfRoom + " is already exist, choose another one.");
        getRoom(oldNumberOfRoom).setNumber(newNumberOfRoom);
        logger.info("room was updated");
    }

    @Override
    public void editRooms(RoomType oldType, RoomType newType) throws WrongArgumentException {

        if (oldType == null || newType == null) throw new WrongArgumentException("Error! Wrong type!");

        for (Room room : rooms) {
            if (room.getType().equals(oldType)) {
                room.setType(newType);
                logger.info("rooms was updated.");
            }
            else {
               logger.info("rooms not found.");
            }
        }
    }

    @Override
    public void deleteFirstRoom(int numberOfRoom) throws NotFoundException, WrongNumberArgsException {

        boolean isRemoved = rooms.remove(getRoom(numberOfRoom));
        if (isRemoved) {
            logger.info("room with number " + numberOfRoom + " was deleted.");
        }
        else {
            logger.info("Room with number " + numberOfRoom + " does not exist.");
        }
    }

    @Override
    public void deleteFirstRoom(RoomType type) throws NotFoundException, WrongArgumentException {

        boolean isRemoved = rooms.remove(getRoom(type));
        if (isRemoved) {
            logger.info("room with type " + type + " were deleted.");
        }
        else {
            logger.info("rooms with type " + type + " not found.");
        }
    }

    @Override
    public void deleteRooms(RoomType deletedRoomType) throws WrongArgumentException, NotFoundException {

        if (deletedRoomType == null) throw new WrongArgumentException("Error! Problem with type...");
        int count = 0;
        for (Room room : rooms) {
            if (room.getType().equals(deletedRoomType)) {
                rooms.remove(room);
                count++;
            }
        }
        if (count > 0) {
            logger.info(count + " room(s) deleted!");
        }
        else {
            throw new NotFoundException("Rooms with type " + deletedRoomType + " not found.");
        }
    }

    @Override
    public void deleteRooms() {
        if (rooms.isEmpty()) {
            logger.info("There no rooms in the hotel!");
        }
        else {
            rooms.clear();
            logger.info("Rooms deleted!");
        }


    }

    @Override
    public void addClient(Client cLient) throws IOException {
        if (cLient.validate().keySet().isEmpty()) {
            clients.add(cLient);
        }
        else {
            throw new IOException(cLient.validate().values().stream().findAny().get());
        }
    }

    @Override
    public Client getClient(String firstName) throws IOException, NotFoundException {
        if (firstName == null || firstName.length() < 3) throw new IOException("The first name is too short!");

        for (Client client : clients) {
            if (client.getFirstName().equalsIgnoreCase(firstName)) {
                logger.info("Client " + firstName + " was found!");
                return client;
            }
        }
        throw new NotFoundException("The client is not exist");
    }

    @Override
    public Client getClient(Client client) throws WrongArgumentException, NotFoundException {
        if (client.validate().keySet().isEmpty()) {
            for (Client visitor : clients) {
                if (visitor.equals(client)) {
                    logger.info("Client " + client.getFirstName() + " was found.");
                    return visitor;
                }
            }
        }
        else {
            throw new WrongArgumentException(client.validate().values().stream().findAny().get());
        }
        throw new NotFoundException("Client with first name " + client.getFirstName() + " not found");
    }

    public List<Client> getClients() {
        logger.info("List of the clients.");
        return clients;
    }

    @Override
    public void editClient(String oldFirstName, String newFirstName) throws IOException, NotFoundException, WrongArgumentException {
        getClient(oldFirstName).setFirstName(newFirstName);
        logger.info("Client was updated.");
    }

    @Override
    public void deleteClient(String firstName) throws IOException, NotFoundException {

        boolean isRemoved = clients.remove(getClient(firstName));
        if (isRemoved) {
            logger.info("Client was deleted.");
        }
        else {
            logger.info("Client does not exist.");
        }

    }


    @Override
    public void bookClient(Client client) throws NotFoundException, WrongArgumentException {
        Room room = getRoom(RoomType.COUNTRY);
        booking(client, room);

    }

    @Override
    public void bookClient(Client client, RoomType type) throws NotFoundException, WrongArgumentException {
        Room room = getRoom(type);
        booking(client, room);
    }

    private void booking(Client client, Room room) throws NotFoundException, WrongArgumentException {
        if (!client.validate().keySet().isEmpty()) {
            throw new WrongArgumentException(client.validate().values().stream().findAny().get());
        }

        if (!room.validate().keySet().isEmpty()) {
            throw new WrongArgumentException(room.validate().values().stream().findAny().get());
        }

        for (Room apartment : rooms) {
            if (room.getType().equals(apartment.getType()) && apartment.isAvailable()) {
                clientRoomMap.put(apartment.getNumber(), getClient(client));
                room.setAvailable(false);
                clients.remove(client);
                logger.info("Client " + client.getFirstName() + " is in the room with number " + room.getNumber());
                return;
            }
            else {
                logger.info("Room with " + room.getType() + " type is not available. Sorry.");
            }
        }
        logger.info("Client not found.");
    }

    @Override
    public void bookClient(Client client, double price) throws WrongArgumentException, WrongNumberArgsException, NotFoundException, NegativePriceException {

        if (!client.validate().keySet().isEmpty()) {
            throw new WrongArgumentException(client.validate().values().stream().findAny().get());
        }

        if (price <= 0) throw new WrongNumberArgsException("price can`t be lower or equal to zero.");

        Room roomByPrice = getRoom(price);
        if (roomByPrice == null) throw new NotFoundException("Room with price " + price + " not found.");

        booking(client, roomByPrice);


    }

    @Override
    public void bookClient(String firstName) throws IOException, NotFoundException, WrongArgumentException {
        Client client = getClient(firstName);
        Room room = getRoom(RoomType.COUNTRY);
        booking(client, room);
    }

    @Override
    public void bookClient(Client client, Room room) throws NotFoundException, WrongArgumentException {
        booking(client, room);
    }

    @Override
    public void bookClient(String firstName, RoomType type) throws IOException, NotFoundException, WrongArgumentException {
        Client client = getClient(firstName);
        Room room = getRoom(type);
        booking(client, room);
    }

    @Override
    public void unBookClient(Room room, Client client) throws WrongArgumentException {

        if (room.validate().keySet().isEmpty()) {
            throw new WrongArgumentException("Problem with room.");
        }

        if (client.validate().keySet().isEmpty()) {
            throw new WrongArgumentException("Problem with client.");
        }
        clientRoomMap.remove(room.getNumber(), client);
    }

    @Override
    public String toString() {
        return    rooms + "/"
                + clients + "/"
                + clientRoomMap
                ;
    }

    @Override
    public Map<String, String> validate() {
        Map<String,String> res = new HashMap<>();

        if (name.length() < 3) res.put(name, "The hotel name is too short! Make it longer.");
        if (name.length() > 8) res.put(name, "The hotel name is too long! Maximum is eight chars!");

        return res;
    }
}
