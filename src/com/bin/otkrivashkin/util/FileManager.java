package com.bin.otkrivashkin.util;

import com.bin.otkrivashkin.model.impl.Hotel;
import com.bin.otkrivashkin.model.impl.RoomImpl;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.impl.HotelServiceImpl;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private HotelServiceImpl hotelServiceImpl;

    public FileManager(HotelServiceImpl hotelServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;
    }

    private List<String> rooms = new ArrayList<>();
    private static final String HOTEL_PATH = "src\\resources\\hotels\\";

    public void saveHotel(Hotel hotel) {

        File file = new File(HOTEL_PATH + hotel.getName() + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            StringBuilder builder = new StringBuilder();
            builder
                    .append(hotel.getRooms()).append("/")
                    .append(hotel.getClients()).append("/")
                    .append(hotel.getClientRoomMap());
            writer.append(builder.toString());
            writer.newLine();
            writer.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadHotel() {
        File file = new File(HOTEL_PATH + "test.txt");
        List<String> list = new ArrayList<>();
        List<String> rooms = new ArrayList<>();
        List<String> clients = new ArrayList<>();
        List<String> roomViaClient = new ArrayList<>();
        if(file.exists()){
            try {
                list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(list.isEmpty())
                return;
        }
        for(String line : list){
            String [] res = line.split("/");
            rooms.add(res[0]);
            clients.add(res[1]);
            roomViaClient.add(res[2]);
        }
    }

    public void loadHotel(String hotelName) throws IOException {
        String[] currentLine;
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

            RoomType type = Enum.valueOf(RoomType.class, rooms.get(i));
            int number = Integer.parseInt(rooms.get(i + 1));
            double price = Double.parseDouble(rooms.get(i + 2));
            boolean available = Boolean.parseBoolean(rooms.get(i + 3));
            hotel.addRoom(new RoomImpl(
                    type,
                    number,
                    price,
                    available));
        }
        hotelServiceImpl.add(hotel);
    }
}
