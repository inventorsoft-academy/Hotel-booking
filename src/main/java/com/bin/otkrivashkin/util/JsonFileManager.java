package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.RoomService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileManager {

    private static final String BASE_PATH = "src\\main\\resources\\json\\";
    private static final String ROOM_PATH = "rooms.json";
    private static final String CLIENT_PATH = "clients.json";
    private static final String REGISTER_PATH = "register.json";

    private RoomService roomService;

    public JsonFileManager(RoomService roomService) {
        this.roomService = roomService;
    }

    public void fileToJson() {

        List<Room> rooms = roomService.getRooms();

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(BASE_PATH + ROOM_PATH), rooms);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
