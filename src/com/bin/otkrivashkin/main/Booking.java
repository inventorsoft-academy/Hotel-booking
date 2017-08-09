package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.service.HotelService;

import java.util.Scanner;

public class Booking {

    private HotelService hotelService;

    public void init() {
        Scanner scanner = new Scanner(System.in);
        /*=================================== MAIN LOOP ===================================*/
        System.out.println("Hello!");
        System.out.println("Use 'help' for info.");
        String command;
        do {
            command = scanner.nextLine();
            /* print all help commands */
            if (command.equalsIgnoreCase("help")) {
                // print main commands
                System.out.println("main commands.");
            }
            /* create client */
            else if (command.equalsIgnoreCase("create client")) {
                System.out.println("enter first name");
                String firstName = scanner.nextLine();
                System.out.println("enter last name");
                String lastName = scanner.nextLine();
                Client client = new Client(firstName, lastName);
                System.out.println("book client in hotel? yes or no");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase("yes")) {
                    System.out.println("enter the name of hotel");
                    command = scanner.nextLine();
                    Hotel hotel = hotelService.getHotelByName(command);

                }

            }

            /* create hotel */
            else if (command.equalsIgnoreCase("create hotel")) {
                System.out.println("enter a name of the hotel.");
                command = scanner.nextLine();
                Hotel hotel = new Hotel(command);
                /*===================== HOTEL LOOP ========================================*/
                System.out.println("Use 'help' for info.");
                do {
                    command = scanner.nextLine();
                    if (command.equalsIgnoreCase("help")) {
                        // print hotel commands
                        System.out.println("hotel commands.");
                    }
                    /* add single room */
                    else if (command.equalsIgnoreCase("add room")) {
                        System.out.println("Enter a type for the room");
                        String type = scanner.nextLine();

                        System.out.println("Enter a price for the room");
                        double price = scanner.nextDouble();

                        hotel.addRoom(new Room(type, price));
                        System.out.println("Room was added successfully!");
                    }
                    /* add rooms */
                    else if (command.equalsIgnoreCase("add rooms")) {
                        System.out.println("how many rooms do you want to add?");
                        int numOfRooms = scanner.nextInt();
                        System.out.println("set type for rooms");
                        String type = scanner.nextLine();
                        System.out.println("enter price for rooms");
                        double price = scanner.nextDouble();
                        for (int i = 0; i < numOfRooms; i++) {
                            hotel.addRoom(new Room(type, price));
                        }
                        System.out.println("Rooms were added!");
                    }
                    else {
                        System.out.println("Wrong command, use help for info.");
                    }
                } while (!command.equalsIgnoreCase("save"));
                /*======================END HOTEL LOOP ====================================*/
            }
            else {
                System.out.println("Wrong command, use help for info.");
            }
        }while (!command.equalsIgnoreCase("exit"));



        /*================================ END MAIN LOOP ==================================*/

    }

}
