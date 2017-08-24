package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonFileManager implements FileManager {

    private LogManager logManager = LogManager.getLogger(JsonFileManager.class);
    private static final String BASE_PATH = "src\\main\\resources\\json\\";
    private static final String ROOM_PATH = "rooms.json";
    private static final String CLIENT_PATH = "clients.json";
    private static final String REGISTER_PATH = "register.json";

    private RoomService roomService;
    private ClientService clientService;
    private BookingService bookingService;
    private ObjectMapper mapper;

    public JsonFileManager(RoomService roomService, ClientService clientService, BookingService bookingService) {
        this.roomService = roomService;
        this.clientService = clientService;
        this.bookingService = bookingService;
    }


    @Override
    public void saveObjectsToJson() {

        mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(BASE_PATH + ROOM_PATH), roomService.getRooms());
            mapper.writeValue(new File(BASE_PATH + CLIENT_PATH), clientService.getClients());
            mapper.writeValue(new File(BASE_PATH + REGISTER_PATH), bookingService.getRegisterClients());

        } catch (JsonGenerationException e) {
            logManager.error(e.getMessage());
        } catch (JsonMappingException e) {
            logManager.error(e.getMessage());
        } catch (IOException e) {
            logManager.error(e.getMessage());
        }
    }

    @Override
    public void loadObjectsFromJson() {
        mapper = new ObjectMapper();

        try {
            TypeReference<List<Room>> roomTypeList = new TypeReference<List<Room>>(){};
            List<Room> roomList = mapper.readValue(BASE_PATH + ROOM_PATH, roomTypeList);
            roomService.addRooms(roomList);

            TypeReference<List<Client>> clientTypeList = new TypeReference<List<Client>>(){};
            List<Client> clientList = mapper.readValue(BASE_PATH + CLIENT_PATH, clientTypeList);
            clientService.addClients(clientList);

            TypeReference<Map<Room, Client>> clientRoomMapType = new TypeReference<Map<Room, Client>>(){};
            Map<Room, Client> roomClientMap = mapper.readValue(BASE_PATH + REGISTER_PATH, clientRoomMapType);
            bookingService.addMapOfClientsWithRooms(roomClientMap);

        } catch (IOException e) {
            logManager.error(e.getMessage());
        }

    }
}
