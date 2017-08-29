package com.bin.otkrivashkin.controller;


import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.util.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;
    private FileManager fileManager;

    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        if (clientService.getClients() != null) {
            return new ResponseEntity<>(clientService.getClients(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        if (clientService.getClientById(id) != null) {
            return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) throws IOException {
        if (clientService.addClient(client)) {
            fileManager.save();
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> editClient(@PathVariable int id, @RequestBody Client client) {
        if (clientService.editClientById(id, client)) {
            fileManager.save();
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClientById(@PathVariable("id") int id) {
        if (clientService.deleteClientById(id)) {
            fileManager.save();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
