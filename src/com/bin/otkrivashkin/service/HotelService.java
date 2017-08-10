package com.bin.otkrivashkin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by otkrivashkin on 10.08.2017.
 */
public class HotelService {

    private List<String> listOfOptions;

    public HotelService() {

    }

    public List<String> getOptions() {
        List<String> stringList = new ArrayList<>();
        stringList.add("0 - create hotel.");
        stringList.add("1 - view hotel.");
        stringList.add("2 - edit hotel.");
        stringList.add("3 - delete hotel.");
        return stringList;
    }
}
