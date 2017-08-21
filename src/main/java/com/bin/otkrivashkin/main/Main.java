package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.JournalService;
import com.bin.otkrivashkin.service.impl.HotelServiceImpl;
import com.bin.otkrivashkin.service.impl.JournalServiceImpl;
import com.bin.otkrivashkin.util.Factory;
import com.bin.otkrivashkin.util.TextFileManager;
import com.bin.otkrivashkin.util.Printer;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        String hotelName = "";
        HotelServiceImpl hotelServiceImpl = new HotelServiceImpl();
        Printer printer = new Printer();
        TextFileManager fileManager = new TextFileManager(hotelServiceImpl);
        Scanner scanner = new Scanner(System.in);

        boolean inMain = true;
        while (inMain) {
            logger.info("main branch");
            int mainOption = 999;

            try {
                mainOption = scanner.nextInt();
            } catch (InputMismatchException e) {
                logger.info("wrong argument! Only Integer type.");
                scanner.next();
            }

            switch (mainOption) {
                case 0:
                    hotelServiceImpl.getMainOptions();
                    break;
                case 1:
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
                                printer.print(hotelServiceImpl.getHotelOptions());
                                break;
                            case 1:
                                hotelName = createHotel(hotelServiceImpl, printer);
                                break;
                            case 2:
                                hotelName = getHotel(hotelServiceImpl, printer);
                                break;
                            case 3:
                                editHotel(hotelServiceImpl, printer);
                                break;
                            case 4:
                                deleteHotel(hotelServiceImpl, printer);
                                break;
                            case 300:
                                inHotel = false;
                                break;
                            default:
                                //
                        }
                    }
                    break;
                case 2:
                    boolean inRoom = true;
                    while (inRoom) {
                        printer.print("room branch");
                        int roomOption = 999;
                        try {
                            roomOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logger.warning("wrong argument.");
                            scanner.next();
                        }

                        switch (roomOption) {
                            case 0:
                                printer.print(hotelServiceImpl.getRoomOptions());
                                break;
                            case 1:
                                addRooms(hotelName, hotelServiceImpl, printer);
                                printer.printSuccessMessage();
                                break;
                            case 2:
                                getRooms(hotelName, hotelServiceImpl, printer);
                                break;
                            case 3:
                                editRooms(hotelName, hotelServiceImpl, printer);
                                printer.printSuccessMessage();
                                break;
                            case 4:
                                deleteRooms(hotelName, hotelServiceImpl, printer);
                                printer.printSuccessMessage();
                                break;
                            case 300:
                                inRoom = false;
                                break;
                            default:
                                //
                        }
                    }
                    break;
                case 3:
                    boolean inClient = true;
                    while (inClient) {
                        printer.print("client branch");

                        int clientOption = 999;

                        try {
                            clientOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logger.warning("wrong argument");
                            scanner.next();
                        }

                        switch (clientOption) {
                            case 0:
                                printer.print(hotelServiceImpl.getClientOptions());
                                break;
                            case 1:
                                addClient(hotelName, hotelServiceImpl, printer);
                                break;
                            case 2:
                                getClients(hotelName, hotelServiceImpl, printer);
                                break;
                            case 3:
                                editClient(hotelName, hotelServiceImpl, printer);
                                break;
                            case 4:
                                deleteClient(hotelName, hotelServiceImpl, printer);
                                break;
                            case 300:
                                inClient = false;
                                break;
                            default:
                                //
                        }
                    }
                    break;
                case 4:
                    boolean inBooking = true;
                    while (inBooking) {
                        printer.print("booking branch");

                        int bookingOption = 999;

                        try {
                            bookingOption = printer.scanInt();
                        } catch (InputMismatchException e) {
                            logger.warning("wrong argument");
                            scanner.next();
                        }

                        switch (bookingOption) {
                            case 0:
                                printer.print(hotelServiceImpl.getBookingOptions());
                                break;
                            case 1:
                                bookClient(hotelName, hotelServiceImpl, printer);
                                break;
                            case 2:
                                getRoomWithClients(hotelName, hotelServiceImpl, printer);
                                break;
                            case 300:
                                inBooking = false;
                                break;
                            default:
                                //
                        }
                    }
                    break;
                case 5:
                    boolean inJournal = true;
                    while (inJournal) {
                        printer.print("journal branch");

                        int journalOption = 999;

                        try {
                            journalOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logger.warning("wrong argument");
                            scanner.next();
                        }

                        switch (journalOption) {
                            case 0:
                                printer.print(hotelServiceImpl.getJournalOptions());
                                break;
                            case 1:
                                JournalService journalService = new JournalServiceImpl(hotelServiceImpl);
                                try {
                                    journalService.printAll(hotelName);
                                } catch (NotFoundException e) {
                                    logger.info(e.getMessage());
                                }
                                break;
                            case 300:
                                inJournal = false;
                                break;
                            default:
                                //
                        }
                    }
                case 100: // save
                    saveTxtFile(hotelName, hotelServiceImpl, fileManager);
                    printer.printSuccessMessage();
                    break;
                case 200: // load
                    try {
                        hotelName = loadTxtFile(printer, fileManager);
                    } catch (WrongNumberArgsException e) {
                        logger.info(e.getMessage());
                    }
                    printer.printSuccessMessage();
                    break;
                case 300: // exit
                    inMain = false;
                    printer.print("Good bye");
                    break;
                case -1: // get default hotel
                    hotelName = initDefaultHotel(hotelServiceImpl);
                    break;
                default:
                    printer.print("Wrong argument");
            }
        }
    }

    private static void saveTxtFile(String hotelName, HotelServiceImpl hotelServiceImpl, TextFileManager fileManager) {
        try {
            fileManager.saveHotel(hotelServiceImpl.getHotel(hotelName));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void getRoomWithClients(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            printer.print(hotel.getClientRoomMap().toString());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void bookClient(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        printer.print("Enter a first name");
        String firstName = printer.scanString();
        printer.print("Choose type of the room");
        printer.printTypes();
        int typeAsInt = printer.scanInt();
        RoomType type = printer.getRoomType(typeAsInt);
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            hotel.bookClient(firstName, type);
        } catch (NotFoundException | WrongArgumentException | IOException e) {
            logger.info(e.getMessage());
        }
    }

    private static void deleteClient(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        String firstName;
        Hotel hotel;
        printer.print("Enter first name of the client");
        firstName = printer.scanString();
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            hotel.deleteClient(firstName);
        } catch (NotFoundException | IOException e) {
            logger.info(e.getMessage());
        }
    }

    private static void editClient(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        printer.print("Enter old first name");
        String oldFirstName = printer.scanString();
        printer.print("Enter new first name");
        String newFirstName = printer.scanString();
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            hotel.editClient(oldFirstName, newFirstName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (WrongArgumentException | NotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    private static void getClients(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            printer.print(hotel.getClients());
        } catch (NotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    private static void addClient(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        printer.print("Enter first name");
        String firstName = printer.scanString();
        printer.print("Enter last name");
        String lastName = printer.scanString();
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            hotel.addClient(new Client(firstName, lastName));
        } catch (IOException | NotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    private static void deleteRooms(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        printer.print("enter type of rooms.");
        printer.printTypes();
        int deleteTypeAsInt = printer.scanInt();
        RoomType deletedRoomType = printer.getRoomType(deleteTypeAsInt);
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            hotel.deleteRooms(deletedRoomType);
        } catch (NotFoundException | WrongArgumentException e) {
            logger.info(e.getMessage());
        }
    }

    private static void editRooms(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        printer.print("choose old type or rooms.");
        printer.printTypes();
        int oldTypeAsInt = printer.scanInt();
        printer.print("choose new type of rooms.");
        printer.printTypes();
        int newTypeAsInt = printer.scanInt();
        RoomType oldRoomType = printer.getRoomType(oldTypeAsInt);
        RoomType newRoomType = printer.getRoomType(newTypeAsInt);
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            hotel.editRoom(oldRoomType, newRoomType);
        } catch (NotFoundException | WrongArgumentException e) {
            logger.info(e.getMessage());
        }
    }

    private static void getRooms(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            printer.print(hotel.getRooms());
        } catch (NotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    private static void addRooms(String hotelName, HotelServiceImpl hotelServiceImpl, Printer printer) {
        Hotel hotel;
        printer.print("enter count of rooms.");
        int countOfRooms = printer.scanInt();
        printer.print("enter type for room(s).");
        printer.printTypes();
        int typeOfRoom = printer.scanInt();
        RoomType type = printer.getRoomType(typeOfRoom);
        try {
            hotel = hotelServiceImpl.getHotel(hotelName);
            hotel.addRooms(countOfRooms, type);
        } catch (NotFoundException | WrongNumberArgsException e) {
            logger.info(e.getMessage());
        }
    }

    private static void deleteHotel(HotelServiceImpl hotelServiceImpl, Printer printer) {
        printer.print("enter a name of the hotel");
        String hotelToDelete = printer.scanString();
        try {
            hotelServiceImpl.deleteHotel(hotelToDelete);
        } catch (NotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    private static String getHotel(HotelServiceImpl hotelServiceImpl, Printer printer) {
        String hotelName;
        printer.print("enter a name of the hotel");
        hotelName = printer.scanString();
        try {
            printer.print(hotelServiceImpl.getHotel(hotelName));
        } catch (NotFoundException e) {
            logger.info(e.getMessage());
        }
        return hotelName;
    }

    private static void editHotel(HotelServiceImpl hotelServiceImpl, Printer printer) {
        printer.print("enter old name of the hotel");
        String oldName = printer.scanString();
        printer.print("enter new name of the hotel");
        String newName = printer.scanString();
        try {
            hotelServiceImpl.updateHotel(oldName, newName);
        } catch (NotFoundException | WrongNumberArgsException e) {
            logger.info(e.getMessage());
        }
    }

    private static String createHotel(HotelServiceImpl hotelServiceImpl, Printer printer) {
        printer.print("enter a name of the hotel");
        String hotelName;
        Hotel hotel;
        hotelName = printer.scanString();
        hotel = new Hotel();
        try {
            hotel.setName(hotelName);
            hotelServiceImpl.add(hotel);
        } catch (IOException | WrongNumberArgsException e) {
            logger.info(e.getMessage());
        }
        return hotelName;
    }

    private static String initDefaultHotel(HotelServiceImpl hotelServiceImpl) {
        Hotel hotel;
        String hotelName = "";
        Factory factory = new Factory(hotelServiceImpl);
        try {
            factory.initHotel();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (WrongNumberArgsException e) {
            e.printStackTrace();
        }
        try {
            hotel = hotelServiceImpl.getHotel("testhotel");
            hotelName = hotel.getName();
        } catch (NotFoundException e) {
            logger.info(e.getMessage());
        }
        return hotelName;
    }

    private static String loadTxtFile(Printer printer, TextFileManager fileManager) throws WrongNumberArgsException {
        printer.print("enter the hotel name");
        String hotelName;
        hotelName = printer.scanString();
        fileManager.loadHotel(hotelName);
        return hotelName;
    }
}
