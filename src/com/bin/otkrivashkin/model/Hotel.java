package com.bin.otkrivashkin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel implements ClientInterface, RoomInterface, BookingInterface {

    private String name;
    private List<Room> rooms;
    private List<Client> clients;
    private Map<Room, Client> clientRoomMap;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public Hotel(String name) {
        this.name = name;
        rooms = new ArrayList<>();
        clients = new ArrayList<>();
        clientRoomMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
        int roomNumber = rooms.size() + 1;
        room.setNumber(roomNumber);
        rooms.add(room);
    }

    @Override
    public void addRoom(RoomType type) {
        int roomNumber = rooms.size() + 1;
        rooms.add(new Room(type, roomNumber));
    }

    @Override
    public void addRoom(RoomType type, int number) {
        int roomNumber = rooms.size() + 1;
        rooms.add(new Room(type, roomNumber));
    }

    @Override
    public void addRooms(int countOfRooms, RoomType type) {
        for (int i = 0; i < countOfRooms; i++) {
            int roomNumber = rooms.size() + 1;
            rooms.add(new Room(type, roomNumber));
        }
    }

    @Override
    public Room getRoom(int numberOfRoom) {
        for (Room room : rooms) {
            if (room.getNumber() == numberOfRoom) {
                return room;
            }
        }
        return null;
    }

    @Override
    public Room getRoom(double price) {
        for (Room room : rooms) {
            if (room.getPrice() == price) {
                return room;
            }
        }
        return null;
    }

    @Override
    public Room getRoom(RoomType type) {
        for (Room room : rooms) {
            if (room.getType().equals(type)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public Room getRoom(Room room) {
        for (Room groom : rooms) {
            if (groom.equals(room)) {
                return groom;
            }
        }
        return null;
    }

    @Override
    public void editRoom(RoomType oldType, RoomType newType) {
        getRoom(oldType).setType(newType);
    }

    @Override
    public void editRoom(int oldNumberOfRoom, int newNumberOfRoom) {
        getRoom(oldNumberOfRoom).setNumber(newNumberOfRoom);
    }

    @Override
    public void editRooms(RoomType oldType, RoomType newType) {
        for (Room room : rooms) {
            if (room.getType().equals(oldType)) {
                room.setType(newType);
            }
        }
    }

    @Override
    public void deleteRoom(int numberOfRoom) {
        rooms.remove(getRoom(numberOfRoom));
    }

    @Override
    public void deleteRoom(RoomType type) {
        rooms.remove(getRoom(type));
    }

    @Override
    public void deleteRooms(RoomType deletedRoomType) {
        for (Room room : rooms) {
            if (room.getType().equals(deletedRoomType)) {
                rooms.remove(room);
            }
        }
    }

    @Override
    public void deleteRooms() {
        rooms.clear();
    }

    @Override
    public void addClient(Client cLient) {
        clients.add(cLient);
    }

    @Override
    public Client getClient(String firstName) {
        for (Client client : clients) {
            if (client.getFirstName().equals(firstName)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client getClient(Client client) {
        for (Client visitor : clients) {
            if (visitor.equals(client)) {
                return visitor;
            }
        }
        return null;
    }

    @Override
    public void editClient(String oldFirstName, String newFirstName) {
        getClient(oldFirstName).setFirstName(newFirstName);
    }

    @Override
    public void deleteClient(String firstName) {
        clients.remove(getClient(firstName));
    }


    @Override
    public void bookClient(Client client) {
        Room room = getRoom(RoomType.COUNTRY);
        room.setAvailable(false);
        clientRoomMap.put(room, getClient(client));
    }

    @Override
    public void bookClient(Client client, RoomType type) {
        Room room = getRoom(type);
        room.setAvailable(false);
        clientRoomMap.put(room, getClient(client));
    }

    @Override
    public void bookClient(Client client, double price) {
        //TODO
    }

    @Override
    public void bookClient(String firstName) {
        Client client = getClient(firstName);
        Room room = getRoom(RoomType.COUNTRY);
        clientRoomMap.put(room, client);
    }

    @Override
    public void bookClient(String firstName, RoomType type) {
        //TODO
    }

    @Override
    public void unBookClient(Client client) {
        //TODO
    }

    @Override
    public String toString() {
        return "" +
                rooms;
    }
}
