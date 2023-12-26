package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Client;
import com.myBudget.myBudget.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    /*-----------------Afficher tous les clients-------------------*/
    @GetMapping("/clients")
    public Iterable<Client> getAllClients() {
        Iterable<Client> clients = clientRepository.findAll();

        // ResponseEntity est géré automatiquement avec "findAll()"
        return clients;
    }

    /*----------------Afficher le user par son Id-----------------*/
    @GetMapping("/client/{id}")
    public Client getUserById(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        // ResponseEntity est géré automatiquement avec "findById()"
        return optionalClient.orElse(null);
    }    

    /*------------Créer un nouveau Client (inscription)--------------*/
    @PostMapping("/client")
    public ResponseEntity<Client> createUser(Client newClient) {

        // J'enregistre le nouveau Client en BDD
        Client savedClient = clientRepository.save(newClient);

        // J'envoi un statut 201 + les infos du nouveau client
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    /*------------Modifier les infos d'un client par son Id-----------*/
    @PatchMapping("/client/{id}")
    public Client updateClient(@PathVariable Integer id, Client updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        return optionalClient.map(client -> {
            // Je remplace les infos du Client existant par les infos du nouveau
            client.setGender(updatedClient.getGender());
            client.setFirstname(updatedClient.getFirstname());
            client.setLastname(updatedClient.getLastname());
            client.setEmail(updatedClient.getEmail());
            client.setPassword(updatedClient.getPassword());
    
            // J'enregistre les infos en BDD
            clientRepository.save(client);
    
            return client;
        }).orElse(null);
    }

    /*----------------Supprimer un client par son Id-----------------*/
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            clientRepository.delete(client);

            // Suppression ok => j'envoi un statut 204
            return ResponseEntity.noContent().build();
        } else {
            // client non trouvé => j'envoi un statut 404
            return ResponseEntity.notFound().build();
        }
    }
}
