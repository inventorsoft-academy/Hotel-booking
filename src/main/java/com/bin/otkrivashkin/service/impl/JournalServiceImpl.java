package com.bin.otkrivashkin.service.impl;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.JournalService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class JournalServiceImpl implements JournalService {

    private Logger logger = Logger.getLogger(JournalServiceImpl.class.getName());

    private HotelService hotelService;

    public JournalServiceImpl(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public void getInfoAboutClient(String hotelName, String firstName) throws NotFoundException, WrongArgumentException, IOException {
        hotelService = new HotelServiceImpl();
        final Hotel hotelInfo = hotelService.getHotel(hotelName);
        Client client = hotelInfo.getClient(firstName);
        System.out.println("The client " + client.getFirstName() + " " + client.getLastName() + " has " + client.getCash() + "dollars.");
    }

    @Override
    public void getInfoAboutRoom(String hotelName, int roomNumber) throws NotFoundException, WrongNumberArgsException {
        final Hotel infoAboutHotel = getInfoAboutHotel(hotelName);
        Room room = infoAboutHotel.getRoom(roomNumber);
        System.out.println(room.getType() + "/" + room.getPrice() + "/" + room.getNumber());
    }

    @Override
    public Hotel getInfoAboutHotel(String hotelName) throws NotFoundException {
        return hotelService.getHotel(hotelName);
    }

    /* PRINT ALL ABOUT HOTEL
    * --------------------------------------------------------------------------------------------------
    * Room Number | Type      | Price | Available | Client          | Cash    | Start Date | End Date   |
    * ==================================================================================================
    * 1           | PRESIDENT | 5000  | false     | Igor Bondarenko | 17000   | 23.10.2017 | 26.10.2017 |
    * --------------------------------------------------------------------------------------------------
    * 2           | LUX       | 3000  | true      | -               | -       | -          | -          |
    * --------------------------------------------------------------------------------------------------
    * 3           | LUX       | 3000  | false     | Bob Bobrovich   | 120000  | 19.12.2017 | 24.12.2017 |
    * --------------------------------------------------------------------------------------------------
    * 4           | COUNTRY   | 1300  | true      | -               | -       | -          | -          |
    * --------------------------------------------------------------------------------------------------
    * */

    @Override
    public void printAll(String hotelName) throws NotFoundException {

        final Hotel hotelAsPrint = hotelService.getHotel(hotelName);
        Map<Room, Client> clientRoomMap = hotelAsPrint.getClientRoomMap();
        List<String> strings = new ArrayList<>();

        for (Map.Entry<Room, Client> entry : clientRoomMap.entrySet()) {
            Room room = entry.getKey();
            Client client = entry.getValue();
            strings.add("---------------------------------------------------------------------------------------------------");
            strings.add("|   Room Number   |  Type  |  Price  | Available |    Client    |  Cash  |  Start Date | End date |");
            strings.add(
                    room.getNumber()
                            + " | " +
                            room.getType()
                            + " | " +
                            room.getPrice()
                            + " | " +
                            client.getFirstName()
                            + " | " +
                            client.getCash()
                            + " | " +
                            client.getStartDate()
                            + " | " +
                            client.getEndDate()
            );
        }

        for (String s : strings) {
            System.out.println(s);
        }
    }
}
