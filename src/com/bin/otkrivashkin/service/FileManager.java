package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.model.Hotel;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private HotelService hotelService;

    public FileManager(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    private List<String> rooms = new ArrayList<>();
    private static final String HOTEL_PATH = "src\\resources\\hotels\\";

    public void saveHotel(Hotel hotel) {

        File file = new File(HOTEL_PATH + hotel.getName() + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            StringBuilder builder = new StringBuilder();
            builder
                    .append(hotel.getRooms()
                    );
            writer.append(builder.toString());
            writer.newLine();
            writer.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadHotel(String hotelName) {
        String[] currentLine = new String[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(HOTEL_PATH + hotelName + ".txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                currentLine = line.split("[[]{},]]"); // Как это у меня получилось?!!!
                for (int i = 0; i < currentLine.length;i++) {
                    if (!currentLine[i].startsWith("[")
                            && !currentLine[i].isEmpty()
                            && !currentLine[i].equals(" ")) { // хотя последнюю закорючку не смог убрать с помощью regexp
                        rooms.add(currentLine[i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Hotel hotel = new Hotel(hotelName);
        for (int i = 0; i < rooms.size(); i+=4) {
            hotel.addRoom(new Room(Enum.valueOf(RoomType.class, rooms.get(i)), Integer.parseInt(rooms.get(i+1)), Double.parseDouble(rooms.get(i+2)), Boolean.parseBoolean(rooms.get(i+3))));
        }
        hotelService.add(hotel);
    }

}
