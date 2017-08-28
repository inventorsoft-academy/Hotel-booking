package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {

    private Map<Room, Client> bookingList;
    private RoomService roomService;
    private ClientService clientService;

    public BookingServiceImpl(RoomService roomService, ClientService clientService) {
        this.bookingList = new HashMap<>();
        this.roomService = roomService;
        this.clientService = clientService;
    }

    @Override
    public boolean registerClient(Client client, Room room, long days) throws NotFoundException, WrongArgumentException, NotEnoughMoneyException {
        if (days < 1) return false;
        client.setStartDate();
        LocalDate end = LocalDate.now().plusDays(days);
        client.setEndDate(end);

        checkCurrentBalanceOfClient(client, room, days);

        register(client, room);
        return true;

    }

    private void checkCurrentBalanceOfClient(Client client, Room room, long days) throws NotEnoughMoneyException {
        double totalRoomPricePlusDays = room.getPrice() * days;
        if (client.getCash() < totalRoomPricePlusDays) {
            throw new NotEnoughMoneyException("You don't have enough money");
        } else {
            double endClientBalance = client.getCash() - totalRoomPricePlusDays;
            client.setCash(endClientBalance);
        }
    }

    @Override
    public Map<Room, Client> getRegisterClients() {
        return bookingList;
    }

    @Override
    public void unregisterClientByFirstName(String name) {

    }

    @Override
    public void unregisterClientById(int id) throws NotFoundException {

        Client registerClientById = getRegisterClientById(id);

        for (Map.Entry<Room, Client> entry : bookingList.entrySet()) {
            Client client = entry.getValue();
            Room room = entry.getKey();
            if (client.equals(registerClientById)) {

                roomService.getRoom(room).setAvailable(true);

                bookingList.remove(room, client);

            }
        }

    }

    @Override
    public Client getRegisterClientById(int id) throws NotFoundException {
        for (Map.Entry<Room, Client> entry : bookingList.entrySet()) {
            Client client = entry.getValue();
            if (client.getClientId() == id) {
                return client;
            }
        }
        throw new NotFoundException("Client not found");
    }

    @Override
    public void addMapOfClientsWithRooms(Map<Room, Client> roomViaClient) {

    }

    private void register(Client client, Room room) throws NotFoundException, WrongArgumentException {
        if (!client.validate().isEmpty()) {
            throw new WrongArgumentException(client.validate().values().stream().findAny().get());
        }

        if (!room.validate().isEmpty()) {
            throw new WrongArgumentException(room.validate().values().stream().findAny().get());
        }

        for (Room apartment : roomService.getRooms()) {
            if (room.getType().equals(apartment.getType()) && apartment.isAvailable()) {
                bookingList.put(apartment, client);
                room.setAvailable(false);
                roomService.editRoom(room);
                clientService.deleteClient(client);
                System.out.println(bookingList.toString());
                return;
            }
        }
        System.out.println("Room is not available, sorry.");
    }
}
