package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NegativePriceException;
import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    public void registerClient(Client client, Room room, long days) throws NotFoundException, WrongArgumentException {
        client.setStartDate();
        LocalDate end = LocalDate.now().plusDays(days);
        client.setEndDate(end);
        booking(client, room);

    }

    @Override
    public Map<Room, Client> getBooking() {
        return null;
    }

    @Override
    public void addBooking(Map<Room, Client> roomViaClient) {

    }

    @Override
    public void registrationClients() {

    }

    @Override
    public void cancelRegistration(String name) {

    }

    @Override
    public void cancelRegistration(int roomNumber) {

    }

    private void booking(Client client, Room room) throws NotFoundException, WrongArgumentException {
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
                clientService.deleteClient(client);
                return;
            }
        }
        System.out.println("Room is not available, sorry.");
    }
}
