package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.PrinterService;
import com.bin.otkrivashkin.service.RoomService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        HotelService hotelService = new HotelService();
        PrinterService printerService = new PrinterService();
        RoomService roomService = new RoomService();
        System.out.println("0 - helpful options...");
        boolean hotelLevel = true;
        while (hotelLevel) {
            int hotelOption = printerService.scanInt();
            switch (hotelOption) {
                case 0:
                    printerService.print(hotelService.getOptions());
                    break;
                case 1:
                    printerService.print("enter a name of the hotel");
                    String hotelName = printerService.scanString();
                    hotelService.create(new Hotel(hotelName));
                    printerService.printSuccessMessage();
                    break;
                case 2:
                    if (hotelService.getHotels().size() == 1) {
                        printerService.print(hotelService.getHotels());
                        printerService.printSuccessMessage();
                        break;
                    }
                    printerService.print("enter a name of the hotel");
                    String hotelByName = printerService.scanString();
                    printerService.print(hotelService.getByName(hotelByName));
                    break;
                case 3:
                    printerService.print("enter old name of the hotel");
                    String oldName = printerService.scanString();
                    printerService.print("enter new name of the hotel");
                    String newName = printerService.scanString();
                    hotelService.updateHotel(oldName, newName);
                    printerService.printSuccessMessage();
                    break;
                case 4:
                    printerService.print("enter a name of the hotel");
                    String hotelToDelete = printerService.scanString();
                    hotelService.deleteHotel(hotelToDelete);
                    printerService.printSuccessMessage();
                    break;
                case 5:
                    printerService.print("enter count of rooms.");
                    int countOfRooms = printerService.scanInt();
                    printerService.print("enter type for room(s).");
                    printerService.printTypes();
                    int typeOfRoom = printerService.scanInt();
                    RoomType type = printerService.getRoomType(typeOfRoom);
                    printerService.print("enter the price for room(s).");
                    double price = printerService.scanDouble();
                    roomService.createRooms(countOfRooms, type, price);
                    hotelService.setRooms(roomService.getRooms());
                    printerService.printSuccessMessage();
                    break;
                case 6:
                    printerService.print(hotelService.getRooms());
                    break;
                case 7:
                    printerService.print("choose old type of rooms.");
                    printerService.printTypes();
                    int oldTypeAsInt = printerService.scanInt();
                    RoomType oldRoomType = printerService.getRoomType(oldTypeAsInt);
                    printerService.print("choose new type of rooms.");
                    printerService.printTypes();
                    int newTypeAsInt = printerService.scanInt();
                    RoomType newRoomType = printerService.getRoomType(newTypeAsInt);
                    roomService.editTypeOfRooms(oldRoomType, newRoomType);
                    printerService.printSuccessMessage();
                    break;
                case 8:
                    printerService.print("enter type of rooms.");
                    printerService.printTypes();
                    int deleteTypeAsInt = printerService.scanInt();
                    RoomType deletedRoomType = printerService.getRoomType(deleteTypeAsInt);
                    roomService.deleteRooms(deletedRoomType);
                    printerService.printSuccessMessage();
                    break;
                case 99:
                    hotelLevel = false;
                    break;
                default:
                    printerService.print("Wrong argument");
            }

        }
    }


}
