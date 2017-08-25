package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.JournalService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JournalServiceImpl implements JournalService {

    private BookingService bookingService;

    public JournalServiceImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void printAll() throws NotFoundException {

        Map<Room, Client> clientRoomMap = bookingService.getRegisterClients();

        List<Room> rooms = new ArrayList<>(clientRoomMap.keySet());
        List<Client> clients = new ArrayList<>(clientRoomMap.values());
        System.out.println(String.format("| %6s | %10s | %7s | %7s | %8s | %10s | %8s | %10s | %10s |",
                "number",
                "type",
                "price",
                "is Available",
                "firstName",
                "lastName",
                "cash",
                "startDate",
                "endDate"
        ));

        for (int row = 0; row < rooms.size(); row++) {
            int number = rooms.get(row).getId();
            RoomType type = rooms.get(row).getType();
            double price = rooms.get(row).getPrice();
            String available = rooms.get(row).isAvailable() ? "YES" : "NO";
            String firstName = clients.get(row).getFirstName();
            String lastName = clients.get(row).getLastName();
            double cash = clients.get(row).getCash();
            LocalDate startDate = clients.get(row).getStartDate();
            LocalDate endDate = clients.get(row).getEndDate();

            System.out.println(String.format("| %6s | %10s | %7s | %12s | %9s | %10s | %8s | %10s | %10s |",
                    number,
                    type,
                    price,
                    available,
                    firstName,
                    lastName,
                    cash,
                    startDate,
                    endDate
                    ));
        }
    }

    @Override
    public String getInfo(Client client) {
        return null;
    }

    @Override
    public String getInfo(Room room) {
        return null;
    }
}
