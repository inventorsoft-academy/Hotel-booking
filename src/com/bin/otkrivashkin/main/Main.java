package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.*;

public class Main {
    public static void main(String[] args) {
        String hotelName = "";
        HotelService hotelService = new HotelService();
        PrinterService printerService = new PrinterService();
        FileManager fileManager = new FileManager(hotelService);
        Hotel hotel;
        System.out.println("0 - helpful options...");
        boolean inMain = true;
        while (inMain) {
            int mainIOption = printerService.scanInt();
            switch (mainIOption) {
                case 0: // hotel options
                    boolean inHotel = true;
                    while (inHotel) {
                        int hotelOption = printerService.scanInt();
                        switch (hotelOption) {
                            case 1:
                                // create hotel
                                printerService.print("enter a name of the hotel");
                                hotelName = printerService.scanString();
                                hotel = new Hotel();
                                hotel.setName(hotelName);
                                hotelService.add(hotel);
                                printerService.printSuccessMessage();
                                break;
                            case 2:
                                // get hotel
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
                                // edit hotel
                                printerService.print("enter old name of the hotel");
                                String oldName = printerService.scanString();
                                printerService.print("enter new name of the hotel");
                                String newName = printerService.scanString();
                                hotelService.updateHotel(oldName, newName);
                                printerService.printSuccessMessage();
                                break;
                            case 4:
                                // delete hotel
                                printerService.print("enter a name of the hotel");
                                String hotelToDelete = printerService.scanString();
                                hotelService.deleteHotel(hotelToDelete);
                                printerService.printSuccessMessage();
                                break;
                            default:
                                inHotel = false;
                        }
                    }
                    break;

                case 2:
                    boolean inRoom = true;
                    while (inRoom) {
                        int roomOption = printerService.scanInt();
                        switch (roomOption) {
                            case 1:
                                // add room-s
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
                            case 2:
                                // get room-s
                                hotel = hotelService.getByName(hotelName);
                                printerService.print(hotel.getRooms());
                                break;
                            case 3:
                                // edit room-s
                                printerService.print("choose old type or rooms.");
                                printerService.printTypes();
                                int oldTypeAsInt = printerService.scanInt();
                                printerService.print("choose new type of rooms.");
                                printerService.printTypes();
                                int newTypeAsInt = printerService.scanInt();
                                RoomType oldRoomType = printerService.getRoomType(oldTypeAsInt);
                                RoomType newRoomType = printerService.getRoomType(newTypeAsInt);
                                hotel = hotelService.getByName(hotelName);
                                hotel.editRoom(oldRoomType, newRoomType);
                                printerService.printSuccessMessage();
                                break;
                            case 4:
                                // delete room-s
                                printerService.print("enter type of rooms.");
                                printerService.printTypes();
                                int deleteTypeAsInt = printerService.scanInt();
                                RoomType deletedRoomType = printerService.getRoomType(deleteTypeAsInt);
                                hotel = hotelService.getByName(hotelName);
                                hotel.deleteRooms(deletedRoomType);
                                printerService.printSuccessMessage();
                                break;
                            default:
                                inRoom = false;
                        }
                    }
                    break;
                case 3:
                    boolean inClient = true;
                    while (inClient) {
                        int clientOption = printerService.scanInt();
                        switch (clientOption) {
                            case 1: // add client
                                printerService.print("Enter first name");
                                String firstName = printerService.scanString();
                                printerService.print("Enter last name");
                                String lastName = printerService.scanString();
                                hotel = hotelService.getByName(hotelName);
                                hotel.addClient(new Client(firstName, lastName));
                                printerService.printSuccessMessage();
                                break;
                            case 2: // get client
                                printerService.print("Enter first name");
                                hotel = hotelService.getByName(hotelName);
                                firstName = printerService.scanString();
                                printerService.print(hotel.getClient(firstName).toString());
                                break;
                            case 3: // edit client
                                printerService.print("Enter old first name");
                                String oldFirstName = printerService.scanString();
                                printerService.print("Enter new first name");
                                String newFirstName = printerService.scanString();
                                hotel = hotelService.getByName(hotelName);
                                hotel.editClient(oldFirstName, newFirstName);
                                printerService.printSuccessMessage();
                                break;
                            case 4: // delete client
                                break;
                            default:
                                inClient = false;
                        }
                    }
                    break;
                case 4:
                    boolean inBooking = true;
                    while (inBooking) {
                        int boolingOption = printerService.scanInt();
                        switch (boolingOption) {
                            case 1:
                                // book client
                                break;
                            case 2:
                                // unbook client
                                break;
                            default:
                                inBooking = false;
                        }
                    }
                    break;
                case 5:
                    boolean inJournal = true;
                    while (inJournal) {
                        int journalOption = printerService.scanInt();
                        switch (journalOption) {
                            case 1:
                                // print client
                                break;
                            case 2:
                                // print rooms
                                break;
                            case 3:
                                // print available rooms
                                break;
                            default:
                                inJournal = false;
                        }
                    }
                case 97: // load
                    printerService.print("enter the hotel name");
                    hotelName = printerService.scanString();
                    fileManager.loadHotel(hotelName);
                    printerService.printSuccessMessage();
                    break;
                case 98: // save
                    fileManager.saveHotel(hotelService.getByName(hotelName));
                    printerService.printSuccessMessage();
                    break;
                case 99: // exit
                    inMain = false;
                    printerService.print("Good bye");
                    break;
                default:
                    printerService.print("Wrong argument");
            }
        }
    }
}
