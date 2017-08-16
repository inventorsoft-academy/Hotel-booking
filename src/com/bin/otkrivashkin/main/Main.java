package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String hotelName = "";
        HotelService hotelService = new HotelService();
        PrinterService printerService = new PrinterService();

        FileManager fileManager = new FileManager(hotelService);

        Hotel hotel;
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
                    hotelName = printerService.scanString();
                    hotel = new Hotel();
                    hotel.setName(hotelName);
                    hotelService.add(hotel);
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
                    hotel = hotelService.getByName(hotelName);
                    hotel.addRooms(countOfRooms, type);
                    printerService.printSuccessMessage();
                    break;
                case 6:
                    hotel = hotelService.getByName(hotelName);
                    printerService.print(hotel.getRooms());
                    break;
                case 7:
                    printerService.print("choose new type of rooms.");
                    printerService.printTypes();
                    int newTypeAsInt = printerService.scanInt();
                    RoomType newRoomType = printerService.getRoomType(newTypeAsInt);
                    hotel = hotelService.getByName(hotelName);
                    hotel.editTypeOfRooms(newRoomType);
                    printerService.printSuccessMessage();
                    break;
                case 8:
                    printerService.print("enter type of rooms.");
                    printerService.printTypes();
                    int deleteTypeAsInt = printerService.scanInt();
                    RoomType deletedRoomType = printerService.getRoomType(deleteTypeAsInt);
                    hotel = hotelService.getByName(hotelName);
                    hotel.deleteRooms(deletedRoomType);
                    printerService.printSuccessMessage();
                    break;
                case 97:
                    printerService.print("enter the hotel name");
                    hotelName = printerService.scanString();
                    fileManager.loadHotel(hotelName);
                    printerService.printSuccessMessage();
                    break;
                case 98:
                    fileManager.saveHotel(hotelService.getByName(hotelName));
                    printerService.printSuccessMessage();
                    break;
                case 99:
                    hotelLevel = false;
                    printerService.print("Good bye");
                    break;
                default:
                    printerService.print("Wrong argument");
            }

        }
    }


}
