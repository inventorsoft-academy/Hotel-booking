package com.bin.otkrivashkin.model.impl;

import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.model.Validator;

import java.util.HashMap;
import java.util.Map;

public class RoomImpl implements Validator {

    private RoomType type;
    private int number;
    private double price;
    private boolean available;

    public RoomImpl(RoomType type, int number, double price, boolean available) {
        this.type = type;
        this.number = number;
        this.price = price;
        this.available = available;
    }

    RoomImpl(RoomType type, int number) {
        this.type = type;
        this.number = number;
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
                number + "," +
                price + "," +
                available
                + "}"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomImpl room = (RoomImpl) o;

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

    @Override
    public Map<String, String> validate() {
        Map<String,String> res = new HashMap<>();

        if (type == null) res.put("type", "RoomImpl type is empty!");
        if (price <= 0) res.put("price", "The price is less than must to be");


        return res;
    }
}
