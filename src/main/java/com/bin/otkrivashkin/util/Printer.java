package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.RoomType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by otkrivashkin on 10.08.2017.
 */
public class Printer {

    private Scanner scanner;

    public Printer() {
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






}
