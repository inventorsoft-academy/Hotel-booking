package com.bin.otkrivashkin.main;

import com.bin.otkrivashkin.model.*;
import com.bin.otkrivashkin.service.HotelService;
import com.bin.otkrivashkin.service.PrinterService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        HotelService hotelService = new HotelService();
        PrinterService printerService = new PrinterService();
        System.out.println("0 - helpful options...");
        boolean hotelLevel = true;
        while (hotelLevel) {
            int hotelOption = scanner.nextInt();
            switch (hotelOption) {
                case 0:
                    printerService.print(hotelService.getOptions());
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
