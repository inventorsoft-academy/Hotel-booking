package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.JournalService;
import com.bin.otkrivashkin.util.FileManager;
import com.bin.otkrivashkin.util.TextFileManager;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class JournalServiceImpl {

    private HotelService hotelService;

    public JournalServiceImpl(HotelService hotelService) {
        this.hotelService = hotelService;
    }



    public void printAll(String hotelName) throws NotFoundException {

        Hotel hotelAsPrint = hotelService.getHotel(hotelName);
        Map<Room, Client> clientRoomMap = hotelAsPrint.getClientRoomMap();

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
            int number = rooms.get(row).getNumber();
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
}
