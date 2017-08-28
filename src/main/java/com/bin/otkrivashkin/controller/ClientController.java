package com.bin.otkrivashkin.controller;


import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public void addClient(@RequestBody Client client) throws IOException {
        clientService.addClient(client);
    }

    @PutMapping("/{id}")
    public void updateClient(@PathVariable int id, @RequestBody Client client) {
        clientService.editClientById(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable("id") int id) {
        clientService.deleteClientById(id);
    }



}
