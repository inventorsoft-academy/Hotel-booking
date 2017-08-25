package com.bin.otkrivashkin.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Room implements Validator, Serializable {

    private static AtomicInteger uniqueId = new AtomicInteger();
    private int roomId;
    private RoomType type;
    private double price;
    private boolean available;

    public Room() {
        this.roomId = uniqueId.getAndIncrement();
    }

    public Room(RoomType type, double price, boolean available) {
        this.type = type;
        this.price = price;
        this.available = available;
        this.roomId = uniqueId.getAndIncrement();
    }

    public Room(RoomType type) {
        this.type = type;
        this.price = setTypeWithPrice(type);
        this.available = true;
        this.roomId = uniqueId.getAndIncrement();
    }

    private double setTypeWithPrice(RoomType type) {
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



    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
                type + "," +
                roomId + "," +
                price + "," +
                available
                + "}"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomId != room.roomId) return false;
        if (Double.compare(room.price, price) != 0) return false;
        if (available != room.available) return false;
        return type == room.type;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = roomId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (available ? 1 : 0);
        return result;
    }

    @Override
    public Map<String, String> validate() {
        Map<String,String> res = new HashMap<>();

        if (price < 1) res.put("price", "The price is less than zero");
        if (type == null) res.put("type", "Room type is empty!");

        return res;
    }
}
