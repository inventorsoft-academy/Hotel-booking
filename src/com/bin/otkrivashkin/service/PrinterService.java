package com.bin.otkrivashkin.service;

import java.util.List;

/**
 * Created by otkrivashkin on 10.08.2017.
 */
public class PrinterService {

    public void print(List<String> options) {
        for (String option : options) {
            System.out.println(option);
        }
    }


    public void print(String message) {
        System.out.println(message);
    }
}
