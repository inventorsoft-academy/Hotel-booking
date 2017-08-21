package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Hotel;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

public interface FileManager {

    void saveHotel(Hotel hotel);

    Hotel loadHotel(String hotelName) throws WrongNumberArgsException;
}
