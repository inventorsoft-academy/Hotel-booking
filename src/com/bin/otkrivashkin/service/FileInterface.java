package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;

public interface FileInterface {

    void saveHotel(Hotel hotel);

    Hotel loadHotel(String hotelName);
}
