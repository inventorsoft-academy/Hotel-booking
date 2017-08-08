package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.RoomService;

import java.util.Scanner;

/**
 * Created by otkrivashkin on 08.08.2017.
 */
public class Booking {

    public static void init() {
        HotelService hotelService = new HotelService();
        RoomService roomService = new RoomService();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.next().equals("exit")) {

            System.out.println("Enter Hotel name: \n");

            String hotelName = scanner.nextLine();
            hotelService.create(new Hotel(hotelName));
            System.out.println("Enter a type of room");
            String type = scanner.nextLine();
            System.out.println("Enter number of rooms with type " + type.toUpperCase());
            int numberOfRooms = scanner.nextInt();

        }
    }

}
