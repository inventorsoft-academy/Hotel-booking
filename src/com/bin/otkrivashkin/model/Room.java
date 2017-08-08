package com.bin.otkrivashkin.model;

public class Room {

    private Type type;
    private int number;
    private double price;
    private boolean available;

    public Room(Type type, double price, int number) {
        this.type = type;
        this.price = price;
        this.available = true;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
                "type=" + type +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
