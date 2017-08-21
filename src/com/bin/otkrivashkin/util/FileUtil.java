package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Hotel;

public interface FileUtil {

    void saveHotel(Hotel hotel);

    Hotel loadHotel(String hotelName);
}
