package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.impl.Hotel;

public interface File {

    void saveHotel(Hotel hotel);

    Hotel loadHotel(String hotelName);
}
