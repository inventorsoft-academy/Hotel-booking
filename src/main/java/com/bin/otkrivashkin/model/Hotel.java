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
import java.util.logging.Logger;

public class Hotel implements Validator {

    private String name;

    public Hotel() {
    }

    public String getName() {
        return name;
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
