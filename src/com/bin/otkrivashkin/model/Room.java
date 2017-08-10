package com.bin.otkrivashkin.model;

public class Room {

    private RoomType type;
    private int number;
    private double price;
    private boolean available;

    public Room(RoomType type, double price) {
        this.type = type;
        this.price = price;
        this.available = true;
        number = 1; // how to use number right cause i don't set it in the constructor
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
