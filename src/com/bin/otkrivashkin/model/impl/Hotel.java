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

public class Hotel implements Client, Room, Booking, Validator {

    private Logger logger = Logger.getLogger(Hotel.class.getName());

    private String name;
    private List<RoomImpl> rooms;
    private List<ClientImpl> clients;
    private Map<RoomImpl, ClientImpl> clientRoomMap;

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

    public Map<RoomImpl, ClientImpl> getClientRoomMap() {
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
    public List<RoomImpl> getRooms() {
        return rooms;
    }

    @Override
    public List<RoomImpl> getAvailableRooms() {

        List<RoomImpl> temp = new ArrayList<>();
        for (RoomImpl room : rooms) {
            if (room.isAvailable()) {
                temp.add(room);
            }
        }
        return temp;
    }

    @Override
    public void addRoom(RoomImpl room) {

        if (room.validate().keySet().isEmpty()) {
            int roomNumber = rooms.size() + 1;
            room.setNumber(roomNumber);
            rooms.add(room);
            logger.info("room was added.");
        }
        else {
            logger.info("Error! RoomImpl is not added!");
        }

    }

    @Override
    public void addRoom(RoomType type) {

        if (type != null) {
            int roomNumber = rooms.size() + 1;
            rooms.add(new RoomImpl(type, roomNumber));
            logger.info("room was added.");
        }
        else {
            logger.info("Error! RoomImpl is not added!");
        }

    }

    @Override
    public void addRoom(RoomType type, int number) {

        if (type != null && number > 0) {
            int roomNumber = rooms.size() + 1;
            rooms.add(new RoomImpl(type, roomNumber));
            logger.info("room was added.");
        }
        else {
            logger.info("Error! RoomImpl is not added!");
        }
    }

    @Override
    public void addRooms(int countOfRooms, RoomType type) throws WrongNumberArgsException {

        if (countOfRooms <= 0) throw new WrongNumberArgsException("the count must be greater than zero!");
        if (type != null) {
            for (int i = 0; i < countOfRooms; i++) {
                int roomNumber = rooms.size() + 1;
                rooms.add(new RoomImpl(type, roomNumber));
            }
            logger.info("room(s) was(were) added!");
        }
        else {
            logger.info("Error! RoomImpl(s) is(are) not added!");
        }
    }

    @Override
    public RoomImpl getRoom(int numberOfRoom) throws WrongNumberArgsException, NotFoundException {

        if (numberOfRoom <= 0) throw new WrongNumberArgsException("the number must be greater than zero!");
        for (RoomImpl room : rooms) {
            if (room.getNumber() == numberOfRoom) {
                logger.info("Success! RoomImpl was found.");
                return room;
            }
        }
        throw new NotFoundException("room does not exist.");
    }

    @Override
    public RoomImpl getRoom(double price) throws NegativePriceException, NotFoundException {
        if (price <= 0) throw new NegativePriceException("The price must be greater than zero!");
        for (RoomImpl room : rooms) {
            if (room.getPrice() == price) {
                logger.info("room with price " + price + " was found!");
                return room;
            }
        }
        throw new NotFoundException("RoomImpl with price " + price + "does not exist");
    }

    @Override
    public RoomImpl getRoom(RoomType type) throws WrongArgumentException, NotFoundException {

        if (type == null) throw new WrongArgumentException("The room type doesn't selected.");

        for (RoomImpl room : rooms) {
            if (room.getType().equals(type)) {
                logger.info("The room with type " + type + " returned.");
                return room;
            }
        }
        throw new NotFoundException("RoomImpl with type " + type + "not found.");
    }

    @Override
    public RoomImpl getRoom(RoomImpl room) throws NotFoundException {
        if (room.validate().keySet().isEmpty()) {
            for (RoomImpl groom : rooms) {
                if (groom.equals(room)) {
                    logger.info("Success! RoomImpl was returned.");
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
        logger.info("RoomImpl was updated!");
    }

    @Override
    public void editRoom(int oldNumberOfRoom, int newNumberOfRoom) throws NotFoundException, WrongNumberArgsException, ChooseAnotherOneException {

        if (newNumberOfRoom <= 0 || oldNumberOfRoom <= 0) throw new WrongNumberArgsException("Error! Number of room must be greater than zero!");

        RoomImpl room = getRoom(newNumberOfRoom);

        if (room != null) throw new ChooseAnotherOneException("RoomImpl with number " + newNumberOfRoom + " is already exist, choose another one.");
        getRoom(oldNumberOfRoom).setNumber(newNumberOfRoom);
        logger.info("room was updated");
    }

    @Override
    public void editRooms(RoomType oldType, RoomType newType) throws WrongArgumentException {

        if (oldType == null || newType == null) throw new WrongArgumentException("Error! Wrong type!");

        for (RoomImpl room : rooms) {
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
            logger.info("RoomImpl with number " + numberOfRoom + " does not exist.");
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
        for (RoomImpl room : rooms) {
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
    public void addClient(ClientImpl client) throws IOException {
        if (client.validate().keySet().isEmpty()) {
            clients.add(client);
        }
        else {
            throw new IOException(client.validate().values().stream().findAny().get());
        }
    }

    @Override
    public ClientImpl getClient(String firstName) throws IOException, NotFoundException {
        if (firstName == null || firstName.length() < 3) throw new IOException("The first name is too short!");

        for (ClientImpl client : clients) {
            if (client.getFirstName().equalsIgnoreCase(firstName)) {
                logger.info("ClientImpl " + firstName + " was found!");
                return client;
            }
        }
        throw new NotFoundException("The client is not exist");
    }

    @Override
    public ClientImpl getClient(ClientImpl client) throws WrongArgumentException, NotFoundException {
        if (client.validate().keySet().isEmpty()) {
            for (ClientImpl visitor : clients) {
                if (visitor.equals(client)) {
                    logger.info("ClientImpl " + client.getFirstName() + " was found.");
                    return visitor;
                }
            }
        }
        else {
            throw new WrongArgumentException(client.validate().values().stream().findAny().get());
        }
        throw new NotFoundException("ClientImpl with first name " + client.getFirstName() + " not found");
    }

    public List<ClientImpl> getClients() {
        logger.info("List of the clients.");
        return clients;
    }

    @Override
    public void editClient(String oldFirstName, String newFirstName) throws IOException, NotFoundException, WrongArgumentException {
        getClient(oldFirstName).setFirstName(newFirstName);
        logger.info("ClientImpl was updated.");
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
    public void bookClient(ClientImpl client) throws NotFoundException, WrongArgumentException {
        RoomImpl room = getRoom(RoomType.COUNTRY);
        booking(client, room);

    }

    @Override
    public void bookClient(ClientImpl client, RoomType type) throws NotFoundException, WrongArgumentException {
        RoomImpl room = getRoom(type);
        booking(client, room);
    }

    private void booking(ClientImpl client, RoomImpl room) throws NotFoundException, WrongArgumentException {
        if (!client.validate().keySet().isEmpty()) {
            throw new WrongArgumentException(client.validate().values().stream().findAny().get());
        }

        if (!room.validate().keySet().isEmpty()) {
            throw new WrongArgumentException(room.validate().values().stream().findAny().get());
        }

        for (RoomImpl apartment : rooms) {
            if (room.getType().equals(apartment.getType()) && apartment.isAvailable()) {
                clientRoomMap.put(apartment, getClient(client));
                room.setAvailable(false);
                clients.remove(client);
                logger.info("ClientImpl " + client.getFirstName() + " is in the room with number " + room.getNumber());
                return;
            }
            else {
                logger.info("RoomImpl with " + room.getType() + " type is not available. Sorry.");
            }
        }
        logger.info("ClientImpl not found.");
    }

    @Override
    public void bookClient(ClientImpl client, double price) throws WrongArgumentException, WrongNumberArgsException, NotFoundException, NegativePriceException {

        if (!client.validate().keySet().isEmpty()) {
            throw new WrongArgumentException(client.validate().values().stream().findAny().get());
        }

        if (price <= 0) throw new WrongNumberArgsException("price can`t be lower or equal to zero.");

        RoomImpl roomByPrice = getRoom(price);
        if (roomByPrice == null) throw new NotFoundException("RoomImpl with price " + price + " not found.");

        booking(client, roomByPrice);


    }

    @Override
    public void bookClient(String firstName) throws IOException, NotFoundException, WrongArgumentException {
        ClientImpl client = getClient(firstName);
        RoomImpl room = getRoom(RoomType.COUNTRY);
        booking(client, room);
    }

    @Override
    public void bookClient(ClientImpl client, RoomImpl room) throws NotFoundException, WrongArgumentException {
        booking(client, room);
    }

    @Override
    public void bookClient(String firstName, RoomType type) throws IOException, NotFoundException, WrongArgumentException {
        ClientImpl client = getClient(firstName);
        RoomImpl room = getRoom(type);
        booking(client, room);
    }

    @Override
    public void unBookClient(RoomImpl room, ClientImpl client) throws WrongArgumentException {

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
