package com.bin.otkrivashkin.model;

import java.time.LocalDateTime;

public class Journal {
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

    public Journal(Hotel hotel, int roomNumber, LocalDateTime startDate) {
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
    }

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
                "hotel=" + hotel +
                ", roomNumber=" + roomNumber +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
