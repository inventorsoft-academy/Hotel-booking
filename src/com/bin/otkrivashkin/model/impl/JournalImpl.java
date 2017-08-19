package com.bin.otkrivashkin.model.impl;

import java.time.LocalDateTime;

public class JournalImpl {

    private String HOTEL_FILE_PATH = "src\\resources\\";
    private Hotel hotel;
    private int roomNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public JournalImpl(Hotel hotel, int roomNumber, LocalDateTime endDate) {
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.startDate = LocalDateTime.now();
        this.endDate = endDate;
    }

    public JournalImpl() {}

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
        return "JournalImpl{" +
                "hotel=" + hotel.getName() +
                ", roomNumber=" + roomNumber +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }



}
