package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    private void saveRooms() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(ROOM_PATH), roomService.getRooms());
        } catch (JsonGenerationException e) {
            logManager.error(e.getMessage());
        } catch (JsonMappingException e) {
            logManager.error(e.getMessage());
        } catch (IOException e) {
            logManager.error(e.getMessage());
        }
    }

    private void saveClients() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(CLIENT_PATH), clientService.getClients());
        } catch (JsonGenerationException e) {
            logManager.error(e.getMessage());
        } catch (JsonMappingException e) {
            logManager.error(e.getMessage());
        } catch (IOException e) {
            logManager.error(e.getMessage());
        }
    }

    private void saveRegisterClients() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(REGISTER_PATH), bookingService.getRegisterClients());
        } catch (JsonGenerationException e) {
            logManager.error(e.getMessage());
        } catch (JsonMappingException e) {
            logManager.error(e.getMessage());
        } catch (IOException e) {
            logManager.error(e.getMessage());
        }
    }

    @Override
    public void save() {
        saveRooms();
        saveClients();
        saveRegisterClients();
    }

    @Override
    public void load() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            TypeReference<List<Room>> roomRef = new TypeReference<List<Room>>(){};
            List<Room> rooms = mapper.readValue(new File(
                    ROOM_PATH), roomRef);
            roomService.addRooms(rooms);
            System.out.println(rooms);

            TypeReference<List<Client>> clientRef = new TypeReference<List<Client>>(){};
            List<Client> clients = mapper.readValue(new File(
                    CLIENT_PATH), clientRef);
            clientService.addClients(clients);

            TypeReference<Map<Room, Client>> registerClientsRef = new TypeReference<Map<Room, Client>>(){};
            Map<Room, Client> registerClients = mapper.readValue(new File(
                    REGISTER_PATH), registerClientsRef);
            bookingService.addMapOfClientsWithRooms(registerClients);
        } catch (IOException e) {
            logManager.error(e.getMessage());
        }
    }
}

