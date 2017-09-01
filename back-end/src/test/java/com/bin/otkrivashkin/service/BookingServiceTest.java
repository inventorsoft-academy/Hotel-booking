package com.bin.otkrivashkin.service;

import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.model.RoomType;
import com.bin.otkrivashkin.service.impl.BookingServiceImpl;
import com.bin.otkrivashkin.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BookingServiceTest {

    private BookingService bookingService;
    @Autowired
    private RoomService roomService;
    private ClientService clientService;

    private Client aClient;
    private Client bClient;
    private Client cClient;

    private Room lux;
    private Room cheap;
    private Room president;
    private Room country;

    @Before
    public void setUp() throws Exception {
        clientService = new ClientServiceImpl();

        bookingService = new BookingServiceImpl(roomService, clientService);
        aClient = new Client("First", "Firstovich", 44335.2);
        bClient = new Client("Second", "Secondovich", 44335.2);
        cClient = new Client("Third", "Thirdovich", 44335.2);

        lux = new Room(1,RoomType.LUX, 1444.5, true);
        cheap = new Room(1,RoomType.CHEAP, 132.2, true);
        president = new Room(1,RoomType.PRESIDENT, 500.2, true);
        country = new Room(1,RoomType.COUNTRY, 421.2, true);

        List<Room> roomList = Arrays.asList(
                lux,
                cheap,
                president,
                country
        );

        List<Client> clientList = Arrays.asList(
                aClient,
                bClient,
                cClient
        );

        roomService.addRooms(roomList);
        clientService.addClients(clientList);
    }

    @Test
    public void registerClient() throws Exception {

        bookingService.registerClient(cClient, lux, 5);
        int clientId = cClient.getClientId();
        Client registerClientById = bookingService.getRegisterClientById(clientId);
        assertEquals(cClient, registerClientById);


        bookingService.registerClient(aClient, cheap, 2);

        assertEquals(2, bookingService.getRegisterClients().size());


    }

    @Test
    public void getRegisterClients() throws Exception {

        bookingService.registerClient(aClient, lux, 2);
        bookingService.registerClient(bClient, cheap, 4);
        bookingService.registerClient(cClient, country, 1);

        Map<Room, Client> registerClients = bookingService.getRegisterClients();
        assertEquals(3, registerClients.size());
    }

    @Test
    public void unregisterClientByFirstName() throws Exception {

    }

    @Test
    public void unregisterClientById() throws Exception {
        bookingService.registerClient(aClient, lux, 3);

        assertEquals(1, bookingService.getRegisterClients().size());

        int clientId = aClient.getClientId();

        bookingService.unregisterClientById(clientId);

        assertEquals(0, bookingService.getRegisterClients().size());
    }

    @Test
    public void getRegisterClientById() throws NotFoundException, WrongArgumentException, NotEnoughMoneyException {


    }
}