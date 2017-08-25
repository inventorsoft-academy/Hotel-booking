package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class JsonFileManager implements FileManager {

    private LogManager logManager = LogManager.getLogger(JsonFileManager.class);
    private static final String ROOM_PATH = "src\\main\\resources\\json\\rooms.json";
    private static final String CLIENT_PATH = "src\\main\\resources\\json\\clients.json";
    private static final String REGISTER_PATH = "src\\main\\resources\\json\\register.json";

    private RoomService roomService;
    private ClientService clientService;
    private BookingService bookingService;

    public JsonFileManager(RoomService roomService, ClientService clientService, BookingService bookingService) {
        this.roomService = roomService;
        this.clientService = clientService;
        this.bookingService = bookingService;
    }


    @Override
    public void save() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            // Convert object to JSON string and save into a file directly
            mapper.writeValue(new File(ROOM_PATH), roomService.getRooms());

            System.out.println();
            // Convert object to JSON string and pretty print
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(roomService.getRooms());
            System.out.println(jsonInString);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            TypeReference<List<Room>> typeRef = new TypeReference<List<Room>>() {
            };
            List<Room> rooms = mapper.readValue(new File(
                    ROOM_PATH), typeRef);
            roomService.addRooms(rooms);
            System.out.println(rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

