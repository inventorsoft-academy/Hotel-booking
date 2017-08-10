package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;

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

    public void print(List<String> options) {
        for (String option : options) {
            System.out.println(option);
        }
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

    public void printList(List<Hotel> list) {
        for (Hotel hotel : list) {
            System.out.println(hotel.getName());
        }
    }

    public String scanString() {
        return scanner.next().trim().toLowerCase();
    }

    public Integer scanInt() {
        return scanner.nextInt();
    }
}
