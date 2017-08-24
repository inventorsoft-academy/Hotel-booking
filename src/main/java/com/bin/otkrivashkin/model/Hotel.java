package com.bin.otkrivashkin.model;

import com.bin.otkrivashkin.exception.*;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Hotel implements Validator {

    private static AtomicInteger uniqueId = new AtomicInteger();
    private int hotelId;
    private String name;
    private double totalAmountMoney;
    private List<Room> rooms;

    public Hotel() {
        hotelId = uniqueId.getAndIncrement();
    }

    public String getName() {
        return name;
    }

    public double getTotalAmountMoney() {
        return totalAmountMoney;
    }

    public void setTotalAmountMoney(double totalAmountMoney) {
        this.totalAmountMoney = totalAmountMoney;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setName(String name) throws WrongNumberArgsException {
        if (name.length() <= 3) throw new WrongNumberArgsException("The name of the hotel is too short, make it longer.");
        this.name = name;
    }

    @Override
    public Map<String, String> validate() {
        Map<String,String> res = new HashMap<>();

        if (name.length() < 3) res.put(name, "The hotel name is too short! Make it longer.");
        if (name.length() > 16) res.put(name, "The hotel name is too long! Maximum is 16 chars!");

        return res;
    }

}
