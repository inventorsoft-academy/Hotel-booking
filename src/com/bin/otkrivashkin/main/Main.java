package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.PrinterService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        HotelService hotelService = new HotelService();
        PrinterService printerService = new PrinterService();
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
                        printerService.printList(hotelService.getHotels());
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
                case 99:
                    hotelLevel = false;
                    break;
                default:
                    printerService.print("Wrong argument");
            }

        }
    }
}
