package com.bin.otkrivashkin.view;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Journal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {


        Hotel hotel = new Hotel("Babaca", 3);

        Client client = new Client("Igor", "Bondarenko");

        hotel.add(client, 1);

        hotel.add(client, 1);

        Date currentDate = new Date();

        LocalDateTime startDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime endDate = startDate.plusDays(4);

        Journal journal = new Journal(hotel, 1, startDate, endDate);

        System.out.println(journal.toString());
    }
}
