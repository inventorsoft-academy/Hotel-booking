package com.bin.otkrivashkin.config;

import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.util.FileManager;
import com.bin.otkrivashkin.util.JsonFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfig {

    @Autowired
    private RoomService roomService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BookingService bookingService;

    @Bean
    public FileManager fileManager() {
        return new JsonFileManager(roomService, clientService, bookingService);
    }

}