package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.service.impl.*;
import com.bin.otkrivashkin.util.FileManager;
import com.bin.otkrivashkin.util.JsonFileManager;
import com.bin.otkrivashkin.util.LogManager;
import com.bin.otkrivashkin.util.TextFileManager;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static LogManager logManager = LogManager.getLogger(Main.class);


    private static String hotelName;
    private static HotelService hotelService = new HotelServiceImpl();
    private static RoomService roomService = new RoomServiceImpl();
    private static ClientService clientService = new ClientServiceImpl();
    private static BookingService bookingService = new BookingServiceImpl(roomService, clientService);

    private static FileManager fileManager = new TextFileManager(hotelService, clientService, bookingService, roomService);
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        boolean inMain = true;
        while (inMain) {
            print("main branch");

            int mainOption = 999;

            try {
                mainOption = scanner.nextInt();
            } catch (InputMismatchException e) {
                logManager.error("Wrong argument");
                scanner.next();
            }

            switch (mainOption) {
                case 0:
                    hotelService.getMainOptions();
                    break;
                case 1:
                    boolean inHotel = true;
                    while (inHotel) {
                        print("hotel branch");
                        int hotelOption = 999;
                        try {
                            hotelOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logManager.error("Wrong argument! Only int.");
                            scanner.next();
                        }

                        switch (hotelOption) {
                            case 0:
                                hotelService.getHotelOptions();
                                break;
                            case 1:
                                addHotel();
                                break;
                            case 2:
                                getHotelByName();
                                break;
                            case 3:
                                updateHotel();
                                break;
                            case 4:
                                deleteHotel();
                                break;
                            case 300:
                                inHotel = false;
                                break;
                            default:
                                //
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean inRoom = true;
                    while (inRoom) {
                        System.out.println("room branch");
                        int roomOption = 999;
                        try {
                            roomOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logManager.error("wrong argument.");
                            scanner.next();
                        }

                        switch (roomOption) {
                            case 0:
                                hotelService.getRoomOptions();
                                break;
                            case 1:
                                addRoom();
                                break;
                            case 2:
                                getRoom();
                                break;
                            case 3:
                                editRoom();
                                break;
                            case 4:
                                deleteRoom();
                                break;
                            case 300:
                                inRoom = false;
                                break;
                            default:
                                //
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean inClient = true;
                    while (inClient) {

                        System.out.println("client branch");
                        int clientOption = 999;

                        try {
                            clientOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logManager.error("wrong argument");
                            scanner.next();
                        }

                        switch (clientOption) {
                            case 0:
                                hotelService.getClientOptions();
                                break;
                            case 1:
                                addClient();
                                break;
                            case 2: // get
                                getClient();
                                break;
                            case 3: // edit
                                editClient();
                                break;
                            case 4: // delete
                                deleteClient();
                                break;
                            case 300:
                                inClient = false;
                                break;
                            default:
                                //
                                break;
                        }
                    }
                    break;
                case 4:
                    boolean inBooking = true;
                    while (inBooking) {
                        System.out.println("booking branch");
                        int bookingOption = 999;

                        try {
                            bookingOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logManager.error("wrong argument");
                            scanner.next();
                        }

                        switch (bookingOption) {
                            case 0:
                                hotelService.getBookingOptions();
                                break;
                            case 1:
                                registerClient();
                                break;
                            case 2:
                                unregisterClient();
                                break;
                            case 3:
                                getClients();
                                break;
                            case 300:
                                inBooking = false;
                                break;
                            default:
                                //
                                break;
                        }
                    }
                    break;
                case 5:

                    boolean inJournal = true;
                    while (inJournal) {
                        System.out.println("journal branch");

                        int journalOption = 999;

                        try {
                            journalOption = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            logManager.error("wrong argument");
                            scanner.next();
                        }

                        switch (journalOption) {
                            case 0:
                                hotelService.getJournalOptions();
                                break;
                            case 1:
                                // get clients
                                break;
                            case 2:
                                // get rooms
                                break;
                            case 3:
                                // get Available rooms
                                break;
                            case 4:
                                // get registered clients
                                break;
                            case 5:
                                inJournal = false;
                                break;
                            default:
                                //
                                break;
                        }
                    }
                case 100: // save
                    try {
                        fileManager.saveHotel(hotelService.getHotel(hotelName));
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 200: // load
                    System.out.println("Enter the hotel name");
                    hotelName = scanner.next();
                    try {
                        fileManager.loadHotel(hotelName);
                    } catch (WrongNumberArgsException e) {
                        e.printStackTrace();
                    }
                    break;
                case 300: // exit
                    inMain = false;
                    logManager.info("exit");
                    break;
                case -1:
                    JsonFileManager jsonManager = new JsonFileManager(roomService);
                    jsonManager.fileToJson();
                    break;
                default:
                    //
                    break;
            }
        }
    }

    private static void getClients() {
        bookingService.getRegisterClients();
    }

    private static void unregisterClient() {
        bookingService.getRegisterClients();
        System.out.println("Cancel reservation by\n 1 - first name or last name\n 2 - room number\n 3 - return to the previous menu");

        boolean inRegister = true;
        while (inRegister) {

            int registerOption = scanner.nextInt();
            switch (registerOption) {
                case 1:
                    bookCLient();
                    break;
                case 2:
                    unbookClient();
                    break;
                case 3:
                    inRegister = false;
                    break;
                default:
                    //
                    break;
            }
        }
    }

    private static void bookCLient() {
        System.out.println("enter the first name or the last name");
        String name = scanner.next();
        bookingService.unregisterClientByFirstName(name);
    }

    private static void unbookClient() {
        System.out.println("Enter id of the client");
        int clientId = scanner.nextInt();
        try {
            bookingService.unregisterClientById(clientId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void registerClient() {
        System.out.println("Which client to book?");
        clientService.printClients();
        System.out.println("Enter the first name or the last name of the client");
        String clientName = scanner.next();

        Client client = null;

        try {
            client = clientService.getClient(clientName);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Which room to book?");
        roomService.printRooms();
        System.out.println("Enter number of the room");
        int roomToBookOption = scanner.nextInt();

        Room room = null;

        try {
            room = roomService.getRoomById(roomToBookOption);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("How many days will you stay in the hotel?");

        int days = scanner.nextInt();

        try {
            bookingService.registerClient(client, room, days);
        } catch (NotFoundException | WrongArgumentException e) {
            e.printStackTrace();
        }
    }

    private static void deleteClient() {
        System.out.println("DELETE CLIENT BY\n 1 - FIRST NAME\n 2 - LAST NAME\n");
        int deleteOption = scanner.nextInt();
        switch (deleteOption) {
            case 1:
                System.out.println("Enter the first name of the client");
                String firstNameToDelete = scanner.next();
                try {
                    clientService.deleteClient(firstNameToDelete);
                } catch (IOException | NotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Enter the last name of the client");
                String lastNameToDelete = scanner.next();
                try {
                    clientService.deleteClient(lastNameToDelete);
                } catch (IOException | NotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                //
        }
    }

    private static void editClient() {
        System.out.println("select client to edit");
        clientService.printClients();
        System.out.println("Edit by\n 1 - first name\n 2 - last name");
        boolean inEditMode = true;
        while (inEditMode) {
            int editClientOption = scanner.nextInt();
            switch (editClientOption) {
                case 1:
                    System.out.println("Enter the first name");
                    String firstNameEditMode = scanner.next();

                    Client clientToEdit = null;

                    try {
                        clientToEdit = clientService.getClient(firstNameEditMode);
                    } catch (IOException | NotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1 - edit first name\n 2 - edit last name\n 3 - edit amount of money\n 4 - save & exit");
                    boolean editingClient = true;

                    String newFirstName = clientToEdit.getFirstName();
                    String newLastName = clientToEdit.getLastName();
                    double cash = clientToEdit.getCash();
                    while (editingClient) {

                        int inEditingClient = scanner.nextInt();
                        switch (inEditingClient) {
                            case 1:
                                System.out.println("Enter new first name");
                                newFirstName = scanner.next();
                                break;
                            case 2:
                                System.out.println("enter new last name");
                                newLastName = scanner.next();
                                break;
                            case 3:
                                System.out.println("enter new amount of money");
                                cash = scanner.nextDouble();
                                break;
                            case 4:
                                try {
                                    clientToEdit.setFirstName(newFirstName);
                                    clientToEdit.setLastName(newLastName);
                                    clientToEdit.setCash(cash);
                                } catch (WrongArgumentException e) {
                                    e.printStackTrace();
                                }

                                int clientId = clientService.getClientId(clientToEdit);
                                clientService.setClient(clientId, clientToEdit);
                                editingClient = false;
                                break;
                            default:
                                //
                                break;
                        }
                    }
                    break;
                case 2:
                    // same as by first name
                    break;
                case 4:
                    inEditMode = false;
                    break;
                default:
                    //
            }
        }
    }

    private static void getClient() {
        clientService.printClients();
    }

    private static void addClient() {
        System.out.println("Enter the first name");
        String firstName = scanner.next();
        System.out.println("Enter the last name");
        String lastName = scanner.next();
        System.out.println("Enter starting amount of money");
        double amountOfMoney = scanner.nextDouble();
        Client client = new Client(firstName, lastName, amountOfMoney);

        try {
            clientService.addClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteRoom() {
        System.out.println("Enter id of the room");
        int roomId = scanner.nextInt();
        try {
            roomService.deleteRoomById(roomId);
        } catch (Exception e) {
            logManager.error(e.getLocalizedMessage());
        }
    }

    private static void getRoom() {
        roomService.printRooms();
    }

    private static void editRoom() {
        System.out.println("Enter index of the room.");
        int roomIndex = scanner.nextInt();

        Room roomByIndex = null;
        try {
            roomByIndex = roomService.getRoomById(roomIndex);
        } catch (NotFoundException e) {
            print(e.getMessage());
        }

        boolean inEditMode = true;
        while (inEditMode) {
            System.out.println("1 - change type\n 2 - change price\n 3 - change status\n 4 - save & exit");
            int roomEditOption = scanner.nextInt();

            assert roomByIndex != null;
            boolean newStatus = roomByIndex.isAvailable();
            double newPrice = roomByIndex.getPrice();
            RoomType inTypeRoom = roomByIndex.getType();
            switch (roomEditOption) {
                case 1:
                    System.out.println("choose new type");
                    roomService.printTypes();
                    int editRoomOption = scanner.nextInt();
                    inTypeRoom = roomService.getRoomType(editRoomOption);
                    break;
                case 2:
                    System.out.println("set new price");
                    newPrice = scanner.nextDouble();
                    break;
                case 3:
                    System.out.println("set new status for the room");
                    newStatus = scanner.nextBoolean();
                    break;
                case 4:
                    roomByIndex.setAvailable(newStatus);
                    roomByIndex.setPrice(newPrice);
                    roomByIndex.setType(inTypeRoom);
                    roomService.editRoom(roomByIndex);
                    inEditMode = false;
                    break;
                default:
                    //
                    break;
            }
        }
    }

    private static void addRoom() {
        System.out.println("Enter count of rooms");
        int count = scanner.nextInt();
        System.out.println("Choose type of the room(s)");
        roomService.printTypes();
        int option = scanner.nextInt();
        RoomType outTypeRoom = roomService.getRoomType(option);
        roomService.addRoomsByCount(count, outTypeRoom);
    }

    private static void deleteHotel() {
        System.out.println("Enter new name of the hotel");
        hotelName = scanner.next();
        try {
            hotelService.deleteHotel(hotelName);
        } catch (NotFoundException e) {
            print(e.getMessage());
        }
    }

    private static void updateHotel() {
        System.out.println("Enter new name of the hotel");

        String newHotelName = scanner.next();
        try {
            hotelService.updateHotel(hotelName, newHotelName);
        } catch (NotFoundException | WrongNumberArgsException e) {
            print(e.getMessage());
        }
    }

    private static void getHotelByName() {
        System.out.println("Enter name of the hotel");
        hotelName = scanner.next();
        try {
            hotelService.getHotel(hotelName);
        } catch (NotFoundException e) {
            logManager.error(e.getMessage());
        }
    }

    private static void addHotel() {
        System.out.println("Enter name of the hotel");
        hotelName = scanner.next();
        Hotel hotel = new Hotel();
        try {
            hotel.setName(hotelName);
        } catch (WrongNumberArgsException e) {
            logManager.error(e.getMessage());
        }

        try {
            hotelService.add(hotel);
            print("hotel " + hotel.getName() + " added");
        } catch (IOException e) {
            logManager.error(e.getMessage());
        }
    }

    private static void print(String message) {
        logManager.setLevel(LogManager.IN_CONSOLE);
        logManager.info(message);
    }

}
