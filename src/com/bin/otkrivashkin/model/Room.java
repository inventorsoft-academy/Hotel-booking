package com.bin.otkrivashkin.model;

public class Room {

    private RoomType type;
    private int number;
    private double price;
    private boolean available;

    public Room(RoomType type, double price, boolean available) {
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public Room(RoomType type, double price) {
        this.type = type;
        this.price = price;
        this.available = true;
        number = 1; // how to use number right cause i don't set it in the constructor
    }

    public Room(RoomType type) {
        this.type = type;
        this.price = setPrice(type);
        this.available = true;
    }

    private double setPrice(RoomType type) {
        switch (type) {
            case CHEAP: return 100;
            case LUX: return 200;
            case INDIAN: return 300;
            case COUNTRY: return 500;
            case PRESIDENT: return 1000;
            default:
                return 0;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        return "{" +
                type + "," +
                price + "," +
                available
                + "}";
    }
}
