package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class.getName());

        String hotelName = "";
        HotelService hotelService = new HotelService();
        PrinterService printerService = new PrinterService();
        FileManager fileManager = new FileManager(hotelService);
        Scanner scanner = new Scanner(System.in);

        boolean inMain = true;
        while (inMain) {
            logger.info("main branch");
            int mainOption = 999;

            try {
                mainOption = scanner.nextInt();
            } catch (InputMismatchException e) {
                logger.warning("wrong argument! Only Integer type.");
                scanner.next();
            }

            switch (mainOption) {
                case 0:
                    printerService.print(hotelService.getMainOptions());
                    break;
                case 1: // hotel options
                    boolean inHotel = true;
                    while (inHotel) {
                        logger.info("hotel branch");
                        int hotelOption = 999;

                        try {
                            hotelOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logger.warning("wrong argument! Only Integer type.");
                            scanner.next();
                        }

                        switch (hotelOption) {
                            case 0:
                                printerService.print(hotelService.getHotelOptions());
                                break;
                            case 1: // create hotel
                                hotelName = createHotel(hotelService, printerService);
                                break;
                            case 2: // get hotel
                                hotelName = getHotel(hotelService, printerService);
                                break;
                            case 3: // edit hotel
                                editHotel(hotelService, printerService);
                                break;
                            case 4: // delete hotel
                                deleteHotel(hotelService, printerService);
                                break;
                            case 300:
                                inHotel = false;
                                break;
                            default:
                                //
                        }
                    }
                    break;
                case 2: // room options
                    boolean inRoom = true;
                    while (inRoom) {
                        printerService.print("room branch");
                        int roomOption = 999;
                        try {
                            roomOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            // TODO add logger
                            scanner.next();
                        }

                        switch (roomOption) {
                            case 0:
                                printerService.print(hotelService.getRoomOptions());
                                break;
                            case 1: // add room-s
                                addRooms(hotelName, hotelService, printerService);
                                printerService.printSuccessMessage();
                                break;
                            case 2: // get room-s
                                getRooms(hotelName, hotelService, printerService);
                                break;
                            case 3: // edit room-s
                                editRooms(hotelName, hotelService, printerService);
                                printerService.printSuccessMessage();
                                break;
                            case 4: // delete room-s
                                deleteRooms(hotelName, hotelService, printerService);
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

                        int clientOption = 999;

                        try {
                            clientOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            // TODO add logger
                            scanner.next();
                        }

                        switch (clientOption) {
                            case 0:
                                printerService.print(hotelService.getClientOptions());
                                break;
                            case 1: // add client
                                addClient(hotelName, hotelService, printerService);
                                String firstName;
                                break;
                            case 2: // get client
                                getClients(hotelName, hotelService, printerService);
                                break;
                            case 3: // edit client
                                editClient(hotelName, hotelService, printerService);
                                printerService.printSuccessMessage();
                                break;
                            case 4: // delete client
                                deleteClient(hotelName, hotelService, printerService);
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

                        int bookingOption = 999;

                        try {
                            bookingOption = printerService.scanInt();
                        } catch (InputMismatchException e) {
                            // TODO add logger
                            scanner.next();
                        }

                        switch (bookingOption) {
                            case 0:
                                printerService.print(hotelService.getBookingOptions());
                                break;
                            case 1: // book client
                                bookClient(hotelName, hotelService, printerService);
                                printerService.printSuccessMessage();
                                break;
                            case 2: // unbook client
                                // impl
                                break;
                            case 3: // get rooms with clients
                                getRoomWithClients(hotelName, hotelService, printerService);
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

                        int journalOption = 999;

                        try {
                            journalOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            // TODO add logger
                            scanner.next();
                        }

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
                    saveTxtFile(hotelName, hotelService, fileManager);
                    printerService.printSuccessMessage();
                    break;
                case 200: // load
                    hotelName = loadTxtFile(printerService, fileManager);
                    printerService.printSuccessMessage();
                    break;
                case 300: // exit
                    inMain = false;
                    printerService.print("Good bye");
                    break;
                case -1: // get default hotel
                    hotelName = initDefaultHotel(hotelService, printerService);
                    break;
                case -2: // load default hotel
                    fileManager.loadHotel();
                    break;
                default:
                    printerService.print("Wrong argument");
            }
        }
    }

    private static void saveTxtFile(String hotelName, HotelService hotelService, FileManager fileManager) {
        fileManager.saveHotel(hotelService.getByName(hotelName));
    }

    private static void getRoomWithClients(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        hotel = hotelService.getByName(hotelName);
        printerService.print(hotel.getClientRoomMap().toString());
    }

    private static void bookClient(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        printerService.print("Enter a first name");
        String firstName = printerService.scanString();
        printerService.print("Choose type of the room");
        printerService.printTypes();
        int typeAsInt = printerService.scanInt();
        RoomType type = printerService.getRoomType(typeAsInt);
        hotel = hotelService.getByName(hotelName);
        try {
            hotel.bookClient(firstName, type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteClient(String hotelName, HotelService hotelService, PrinterService printerService) {
        String firstName;
        Hotel hotel;
        printerService.print("Enter first name of the client");
        firstName = printerService.scanString();
        hotel = hotelService.getByName(hotelName);
        try {
            hotel.deleteClient(firstName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void editClient(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        printerService.print("Enter old first name");
        String oldFirstName = printerService.scanString();
        printerService.print("Enter new first name");
        String newFirstName = printerService.scanString();
        hotel = hotelService.getByName(hotelName);
        try {
            hotel.editClient(oldFirstName, newFirstName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getClients(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        hotel = hotelService.getByName(hotelName);
        printerService.print(hotel.getClients());
    }

    private static void addClient(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
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
    }

    private static void deleteRooms(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        printerService.print("enter type of rooms.");
        printerService.printTypes();
        int deleteTypeAsInt = printerService.scanInt();
        RoomType deletedRoomType = printerService.getRoomType(deleteTypeAsInt);
        hotel = hotelService.getByName(hotelName);
        hotel.deleteRooms(deletedRoomType);
    }

    private static void editRooms(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
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
    }

    private static void getRooms(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        hotel = hotelService.getByName(hotelName);
        printerService.print(hotel.getRooms());
    }

    private static void addRooms(String hotelName, HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        printerService.print("enter count of rooms.");
        int countOfRooms = printerService.scanInt();
        printerService.print("enter type for room(s).");
        printerService.printTypes();
        int typeOfRoom = printerService.scanInt();
        RoomType type = printerService.getRoomType(typeOfRoom);
        hotel = hotelService.getByName(hotelName);
        hotel.addRooms(countOfRooms, type);
    }

    private static void deleteHotel(HotelService hotelService, PrinterService printerService) {
        printerService.print("enter a name of the hotel");
        String hotelToDelete = printerService.scanString();
        hotelService.deleteHotel(hotelToDelete);
    }

    private static String getHotel(HotelService hotelService, PrinterService printerService) {
        String hotelName;
        printerService.print("enter a name of the hotel");
        hotelName = printerService.scanString();
        printerService.print(hotelService.getByName(hotelName));
        return hotelName;
    }

    private static void editHotel(HotelService hotelService, PrinterService printerService) {
        printerService.print("enter old name of the hotel");
        String oldName = printerService.scanString();
        printerService.print("enter new name of the hotel");
        String newName = printerService.scanString();
        hotelService.updateHotel(oldName, newName);
    }

    private static String createHotel(HotelService hotelService, PrinterService printerService) {
        printerService.print("enter a name of the hotel");
        String hotelName;
        Hotel hotel;
        hotelName = printerService.scanString();
        hotel = new Hotel();
        hotel.setName(hotelName);
        try {
            hotelService.add(hotel);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return hotelName;
    }

    private static String initDefaultHotel(HotelService hotelService, PrinterService printerService) {
        Hotel hotel;
        String hotelName;
        Factory factory = new Factory(hotelService);
        try {
            factory.initHotel();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        printerService.printSuccessMessage();
        hotel = hotelService.getByName("test");
        hotelName = hotel.getName();
        return hotelName;
    }

    private static String loadTxtFile(PrinterService printerService, FileManager fileManager) {
        printerService.print("enter the hotel name");
        String hotelName;
        hotelName = printerService.scanString();
        try {
            fileManager.loadHotel(hotelName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return hotelName;
    }
}
