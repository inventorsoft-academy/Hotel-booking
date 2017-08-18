package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.model.Validator;
import com.bin.otkrivashkin.service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String hotelName = "";
        HotelService hotelService = new HotelService();
        PrinterService printerService = new PrinterService();
        FileManager fileManager = new FileManager(hotelService);
        Hotel hotel;
        boolean inMain = true;
        while (inMain) {
            printerService.print("main branch");
            int mainOption = printerService.scanInt();
            //TODO how to check is it a number?
            switch (mainOption) {
                case 0:
                    printerService.print(hotelService.getMainOptions());
                    break;
                case 1: // hotel options
                    boolean inHotel = true;
                    while (inHotel) {
                        printerService.print("hotel branch");
                        int hotelOption = printerService.scanInt();
                        switch (hotelOption) {
                            case 0:
                                printerService.print(hotelService.getHotelOptions());
                                break;
                            case 1: // create hotel
                                printerService.print("enter a name of the hotel");
                                hotelName = printerService.scanString();
                                hotel = new Hotel();
                                hotel.setName(hotelName);
                                hotelService.add(hotel);
                                printerService.printSuccessMessage();
                                break;
                            case 2: // get hotel
                                if (hotelService.getHotels().size() == 1) {
                                    printerService.print(hotelService.getHotels());
                                    printerService.printSuccessMessage();
                                    break;
                                }
                                printerService.print("enter a name of the hotel");
                                String hotelByName = printerService.scanString();
                                printerService.print(hotelService.getByName(hotelByName));
                                break;
                            case 3: // edit hotel
                                printerService.print("enter old name of the hotel");
                                String oldName = printerService.scanString();
                                printerService.print("enter new name of the hotel");
                                String newName = printerService.scanString();
                                hotelService.updateHotel(oldName, newName);
                                printerService.printSuccessMessage();
                                break;
                            case 4: // delete hotel
                                printerService.print("enter a name of the hotel");
                                String hotelToDelete = printerService.scanString();
                                hotelService.deleteHotel(hotelToDelete);
                                printerService.printSuccessMessage();
                                break;
                            case 300:
                                inHotel = false;
                                break;
                            default:
                                printerService.print("Wrong argument!");
                        }
                    }
                    break;

                case 2: // room options
                    boolean inRoom = true;
                    while (inRoom) {
                        printerService.print("room branch");
                        int roomOption = printerService.scanInt();
                        switch (roomOption) {
                            case 0:
                                printerService.print(hotelService.getRoomOptions());
                                break;
                            case 1: // add room-s
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
                            case 2: // get room-s
                                hotel = hotelService.getByName(hotelName);
                                printerService.print(hotel.getRooms());
                                break;
                            case 3: // edit room-s
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
                            case 4: // delete room-s
                                printerService.print("enter type of rooms.");
                                printerService.printTypes();
                                int deleteTypeAsInt = printerService.scanInt();
                                RoomType deletedRoomType = printerService.getRoomType(deleteTypeAsInt);
                                hotel = hotelService.getByName(hotelName);
                                hotel.deleteRooms(deletedRoomType);
                                printerService.printSuccessMessage();
                                break;
                            case 300:
                                inRoom = false;
                                break;
                            default:
                                printerService.print("Wrong argument!");
                        }
                    }
                    break;
                case 3: // client options
                    boolean inClient = true;
                    while (inClient) {
                        printerService.print("client branch");
                        int clientOption = printerService.scanInt();
                        switch (clientOption) {
                            case 0:
                                printerService.print(hotelService.getClientOptions());
                                break;
                            case 1: // add client
                                printerService.print("Enter first name");
                                String firstName = printerService.scanString();
                                printerService.print("Enter last name");
                                String lastName = printerService.scanString();
                                hotel = hotelService.getByName(hotelName);
                                try {
                                    hotel.addClient(new Client(firstName, lastName));
                                    printerService.printSuccessMessage();
                                } catch (IOException e) {
                                    String message = e.getMessage();
                                    System.out.println(message);
                                }
                                break;
                            case 2: // get client
                                hotel = hotelService.getByName(hotelName);
                                printerService.print(hotel.getClients());
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
                                printerService.print("Enter first name of the client");
                                firstName = printerService.scanString();
                                hotel = hotelService.getByName(hotelName);
                                hotel.deleteClient(firstName);
                                printerService.printSuccessMessage();
                                break;
                            case 300:
                                inClient = false;
                                break;
                            default:
                                printerService.print("Wrong argument!");
                        }
                    }
                    break;
                case 4: // booking options
                    boolean inBooking = true;
                    while (inBooking) {
                        printerService.print("booking branch");
                        int boolingOption = printerService.scanInt();
                        switch (boolingOption) {
                            case 0:
                                printerService.print(hotelService.getBookingOptions());
                                break;
                            case 1: // book client
                                printerService.print("Enter a first name");
                                String firstName = printerService.scanString();
                                printerService.print("Choose type of the room");
                                printerService.printTypes();
                                int typeAsInt = printerService.scanInt();
                                RoomType type = printerService.getRoomType(typeAsInt);
                                hotel = hotelService.getByName(hotelName);
                                hotel.bookClient(firstName, type);
                                printerService.printSuccessMessage();
                                break;
                            case 2: // unbook client
                                // impl
                                break;
                            case 3:
                                hotel = hotelService.getByName(hotelName);
                                printerService.print(hotel.getClientRoomMap().toString());
                                break;
                            case 300:
                                inBooking = false;
                                break;
                            default:
                                printerService.print("Wrong argument!");
                        }
                    }
                    break;
                case 5: // journal options
                    boolean inJournal = true;
                    while (inJournal) {
                        printerService.print("journal branch");
                        int journalOption = printerService.scanInt();
                        switch (journalOption) {
                            case 0:
                                printerService.print(hotelService.getJournalOptions());
                                break;
                            case 1: // print client

                                break;
                            case 2: // print rooms

                                break;
                            case 3: // print available rooms

                                break;
                            case 300:
                                inJournal = false;
                                break;
                            default:
                                printerService.print("Wrong argument!");
                        }
                    }
                case 100: // save
                    fileManager.saveHotel(hotelService.getByName(hotelName));
                    printerService.printSuccessMessage();
                    break;
                case 200: // load
                    printerService.print("enter the hotel name");
                    hotelName = printerService.scanString();
                    fileManager.loadHotel(hotelName);
                    printerService.printSuccessMessage();
                    break;
                case 300: // exit
                    inMain = false;
                    printerService.print("Good bye");
                    break;
                case -1:
                    Factory factory = new Factory(hotelService);
                    factory.initHotel();
                    printerService.printSuccessMessage();
                    hotel = hotelService.getByName("test");
                    hotelName = hotel.getName();
                    break;
                case -2:
                    fileManager.loadHotel();
                    break;
                default:
                    printerService.print("Wrong argument");
            }
        }
    }
}
