package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Hotel;

public interface FileManager {

    void saveHotel(Hotel hotel);

    Hotel loadHotel(String hotelName);
}
