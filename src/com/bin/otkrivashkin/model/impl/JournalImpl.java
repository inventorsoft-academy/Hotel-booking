package com.bin.otkrivashkin.model.impl;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Journal;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.impl.HotelServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JournalImpl implements Journal {

    private HotelService hotelService;

    public JournalImpl(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public ClientImpl getInfoAboutClient(Hotel hotel, ClientImpl client) throws NotFoundException, WrongArgumentException {
        hotelService = new HotelServiceImpl();
        final Hotel hotelInfo = hotelService.getHotel(hotel);
        return hotelInfo.getClient(client);
    }

    @Override
    public RoomImpl getInfoAboutRoom(Hotel hotel, RoomImpl room) throws NotFoundException {
        final Hotel infoAboutHotel = getInfoAboutHotel(hotel);
        return infoAboutHotel.getRoom(room);
    }

    @Override
    public Hotel getInfoAboutHotel(Hotel hotel) throws NotFoundException {
        return hotelService.getHotel(hotel);
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

        final Hotel hotelAsPrint = hotelService.getHotel(hotel);
        Map<RoomImpl, ClientImpl> clientRoomMap = hotelAsPrint.getClientRoomMap();

        List<String> strings = Arrays.asList(
                "---------------------------------------------------------------------------------------------------\n",
                "|   Room Number   |  Type  |  Price  | Available |    Client    |  Cash  |  Start Date | End date |\n"
        );

        for (Map.Entry<RoomImpl, ClientImpl> entry : clientRoomMap.entrySet()) {
            RoomImpl room = entry.getKey();
            ClientImpl client = entry.getValue();
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
