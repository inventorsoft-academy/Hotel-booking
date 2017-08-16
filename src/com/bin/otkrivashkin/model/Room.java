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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (number != room.number) return false;
        if (Double.compare(room.price, price) != 0) return false;
        if (available != room.available) return false;
        return type == room.type;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type != null ? type.hashCode() : 0;
        result = 31 * result + number;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (available ? 1 : 0);
        return result;
    }
}
