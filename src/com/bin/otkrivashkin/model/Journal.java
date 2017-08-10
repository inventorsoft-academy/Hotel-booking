package com.bin.otkrivashkin.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Journal implements JournalInterface {

    private String HOTEL_FILE_PATH = "src\\resources\\";
    private Hotel hotel;
    private int roomNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;



    public Journal(Hotel hotel, int roomNumber, LocalDateTime startDate, LocalDateTime endDate) {
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Journal() {}


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "hotel=" + hotel.getName() +
                ", roomNumber=" + roomNumber +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean saveRoom(Room room) {
        return false;
    }

    @Override
    public boolean loadRooms() {
        return false;
    }

    @Override
    public boolean attachClientToRoom(Client client, int numberOfRoom) {
        return false;
    }

    @Override
    public boolean detachClientFromRoom(Client client) {
        return false;
    }

    @Override
    public boolean saveHotel(Hotel hotel) {
        try {
            File file = new File(HOTEL_FILE_PATH + "hotel_" + hotel.getName() + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            StringBuilder builder = new StringBuilder();
            builder
                    .append(hotel.getName())
                    .append("|")
                    .append(hotel.getRooms());
            writer.append(builder.toString());
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean loadHotel(Hotel hotel) {
        return false;
    }


}
