package com.bin.otkrivashkin.controller;


import com.bin.otkrivashkin.exception.DataManagerException;
import com.bin.otkrivashkin.model.Client;
import com.bin.otkrivashkin.service.ClientService;
import com.bin.otkrivashkin.util.LogManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,
		RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ClientController {

//	private LogManager log = LogManager.getLogger(ClientController.class);

	private ClientService clientService;


	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "INTERNAL SERVER ERROR")
	@ExceptionHandler(DataManagerException.class)
	public void badResponse(Exception e) {
		//log.error(e.getMessage());
	}

	@GetMapping
	public ResponseEntity<List<Client>> getClients() throws DataManagerException {
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
	public ResponseEntity<Client> addClient(@RequestBody Client client) throws DataManagerException{
		if (clientService.addClient(client)) {
			return new ResponseEntity<>(client, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> editClient(@PathVariable int id, @RequestBody Client client) {
		if (clientService.editClientById(id, client)) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteClientById(@PathVariable("id") int id) {
		if (clientService.deleteClientById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
