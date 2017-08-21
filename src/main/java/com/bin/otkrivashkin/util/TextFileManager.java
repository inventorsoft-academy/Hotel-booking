package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.HotelService;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextFileManager {

    private HotelService hotelService;

    public TextFileManager(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    private static final String HOTEL_PATH = "src\\main\\java\\resources\\hotels\\";
    public static final String ROOM_PREFIX = "Room/";
    public static final String CLIENT_PREFIX = "Client/";
    public static final String BOOKING_PREFIX = "Booking/";

    public void saveHotel(Hotel hotel) {

        File file = new File(HOTEL_PATH + hotel.getName() + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {

            for (Room room : hotel.getRooms()) {
                writer.write( ROOM_PREFIX +
                        room.getType() + "," +
                        room.getNumber() + "," +
                        room.getPrice() + "," +
                        room.isAvailable());
                writer.newLine();
            }

            for (Client client : hotel.getClients()) {
                writer.write(CLIENT_PREFIX +
                        client.getFirstName() + "," +
                        client.getLastName() + "," +
                        client.getCash());
                writer.newLine();
            }

            for (Map.Entry<Room, Client> entry : hotel.getClientRoomMap().entrySet()) {
                Room room = entry.getKey();
                Client client = entry.getValue();
                writer.write(BOOKING_PREFIX +
                        room.getType() + "," +
                        room.getNumber() + "," +
                        room.getPrice() + "," +
                        room.isAvailable() + "," +

                        client.getFirstName() + "," +
                        client.getLastName() + "," +
                        client.getCash() + "," +
                        client.getStartDate() + "," +
                        client.getEndDate()
                );
                writer.newLine();
            }
            writer.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadHotel(String hotelName) throws WrongNumberArgsException {
        File file = new File(HOTEL_PATH + hotelName + ".txt");

        List<String> list = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        Map<Room, Client> roomViaClient = new HashMap<>();
        if(file.exists()){
            try {
                list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(list.isEmpty())
                return;
        }
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);

        for (String line : list) {
            if (line.startsWith(ROOM_PREFIX)) {
                String substring = line.substring(ROOM_PREFIX.length());
                String[] split = substring.split("[,/]");
                for (int i = 0; i < split.length; i+=4) {
                    RoomType type = Enum.valueOf(RoomType.class, split[i]);
                    int number = Integer.parseInt(split[i + 1]);
                    double price = Double.parseDouble(split[i + 2]);
                    boolean available = Boolean.parseBoolean(split[i + 3]);
                    rooms.add(new Room(type, number, price, available));
                }
            }
            if (line.startsWith(CLIENT_PREFIX)) {
                String substring = line.substring(CLIENT_PREFIX.length());
                String[] split = substring.split("[,/]");
                for (int i = 0; i < split.length; i+=3) {
                    String firstName = String.valueOf(split[i]);
                    String lastName = String.valueOf(split[i + 1]);
                    Double cash = Double.valueOf(split[i + 2]);
                    clients.add(new Client(firstName, lastName, cash));
                }
            }
            if (line.startsWith(BOOKING_PREFIX)) {
                String substring = line.substring(BOOKING_PREFIX.length());
                String[] split = substring.split("[,/]");
                for (int i = 0; i < split.length; i+=9) {

                    RoomType type = Enum.valueOf(RoomType.class, split[i]);
                    int number = Integer.parseInt(split[i + 1]);
                    double price = Double.parseDouble(split[i + 2]);
                    boolean available = Boolean.parseBoolean(split[i + 3]);

                    String firstName = String.valueOf(split[i + 4]);
                    String lastName = String.valueOf(split[i + 5]);
                    Double cash = Double.valueOf(split[i + 6]);

                    LocalDate startDate = LocalDate.parse(split[i + 7]);
                    LocalDate endDate = LocalDate.parse(split[i + 8]);
                    roomViaClient.put(
                            new Room(type, number, price, available),
                            new Client(firstName, lastName, cash, startDate, endDate)
                    );
                }
            }
        }
        hotel.addRooms(rooms);
        hotel.addClients(clients);
        hotel.addBooking(roomViaClient);

        try {
            hotelService.add(hotel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
