package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by otkrivashkin on 10.08.2017.
 */
public class PrinterService {

    private Scanner scanner;

    public PrinterService() {
        scanner = new Scanner(System.in);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printFailMessage() {
        System.out.println("Fail...");
    }

    public void printSuccessMessage() {
        System.out.println("Success!");
    }

    public void print(Hotel hotel) {
        System.out.println(hotel.getName());
    }

    public String scanString() {
        return scanner.next().trim().toLowerCase();
    }

    public Integer scanInt() {
        return scanner.nextInt();
    }

    public double scanDouble() {
        return scanner.nextDouble();
    }

    public void printTypes() {
        List<RoomType> types = new ArrayList<>(Arrays.asList(RoomType.values()));
        for (int i = 0; i < types.size(); i++) {
            System.out.println(i + " " + types.get(i));
        }
    }

    public void print(List objects) {
        if (objects.isEmpty()) {
            System.out.println("There are no rooms!");
        }
        else {
            System.out.println(objects.toString());
        }
    }

    public RoomType getRoomType(int typeOfRoom) {
        RoomType type = RoomType.CHEAP;
        switch (typeOfRoom) {
            case 0:
                type = RoomType.LUX;
                break;
            case 1:
                type = RoomType.CHEAP;
                break;
            case 2:
                type = RoomType.PRESIDENT;
                break;
            case 3:
                type = RoomType.COUNTRY;
                break;
            case 4:
                type = RoomType.INDIAN;
                break;
            default:
                System.out.println("wrong argument!");
        }
        return type;
    }
}
