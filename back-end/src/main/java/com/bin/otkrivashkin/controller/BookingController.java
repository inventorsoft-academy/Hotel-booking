package com.bin.otkrivashkin.controller;

import com.bin.otkrivashkin.exception.NotEnoughMoneyException;
import com.bin.otkrivashkin.exception.NotFoundException;
import com.bin.otkrivashkin.exception.WrongArgumentException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.model.Room;
import com.bin.otkrivashkin.service.BookingService;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.service.RoomService;
import com.bin.otkrivashkin.util.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by otkrivashkin on 28.08.2017.
 */

@AllArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;
    private RoomService roomService;
    private ClientService clientService;

    private FileManager fileManager;

    @PostMapping("/{clientId}/{roomId}/{days}")
    public ResponseEntity<Boolean> registerClient(@PathVariable("clientId") int clientId,
                                         @PathVariable("roomId") int roomId,
                                         @PathVariable("days") int days) throws NotFoundException, WrongArgumentException, NotEnoughMoneyException {

        Client clientById = clientService.getClientById(clientId);
        Room roomById = roomService.getRoomById(roomId);
        if (bookingService.registerClient(clientById, roomById, days)) {
            fileManager.save();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Map<Room, Client>> getRegisterDetails() {
        if (bookingService.getRegisterClients() != null) {
            return new ResponseEntity<>(bookingService.getRegisterClients(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
