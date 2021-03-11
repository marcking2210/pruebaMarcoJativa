package com.example.demo.service;

import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/api/client")
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/create")
    private Client create( @RequestBody Client client) {
        clientRepository.save(client);
        return client;
    }

    @GetMapping("/{client}")
    private Client read(@PathVariable(value = "client") Integer clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
    }

    @GetMapping("/all")
    private List<Client> all() {
        List<Client> clients = (List<Client>) clientRepository.findAll();
        return clients;
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Optional<Client> std = clientRepository.findById(client.getClientId());
        Client clientUpdated = std.get();
        clientUpdated.setClientName(client.getClientName());
        clientUpdated.setClientLastName(client.getClientLastName());
        Client cliUpdated = clientRepository.save(clientUpdated);
        return new ResponseEntity<>(cliUpdated, HttpStatus.OK);
    }
    
    @DeleteMapping("delete/{cliente}")
    private ResponseEntity<?> delete(@PathVariable(value = "cliente") Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
        clientRepository.delete(client);
        return ResponseEntity.ok().build();
    }

}
