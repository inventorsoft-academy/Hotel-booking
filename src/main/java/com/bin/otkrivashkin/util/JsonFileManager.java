package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileManager {

    private static final String ROOM_PATH = "src\\main\\java\\resources\\rooms.json";

    void fileToJson() {
        ObjectMapper mapper = new ObjectMapper();

        Room room = new Room(RoomType.LUX, 4431.2, true);

        try {
            mapper.writeValue(new File(ROOM_PATH), room);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
