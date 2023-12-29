/*package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Client;
import com.myBudget.myBudget.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

@RestController
public class AuthController {

    // J'implémente le ClientRepository pour accéder aux methods CRUD 
    @Autowired
    private ClientRepository clientRepository;

    // J'implémente le PasswordEncord pour accéder à la method de hachage
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*-----------------S'inscrire (Signin)-----------------*/
    /*@PostMapping("/signup")
    public ResponseEntity<Client> signUp(@RequestBody Client newClient) {
        
        // Je remplace le password brut du user par son password hachée
        newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
        
        // J'enregistre le nouveau user en BDD
        Client savedClient = clientRepository.save(newClient);

        // J'envoi un code 201 + les infos du nouveau user
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    /*------------------Se connecter (Login)-------------------*/
    /*@PostMapping("/login")
    public ResponseEntity<Client> login(Client loggedClient) {

        // Je cherche un client qui a l'email correspondant
        Client client = clientRepository.findByEmail(loggedClient.getEmail());

        // Le client existe
        if (client != null) {

            // Le password correspond
            if (BCrypt.checkpw(loggedClient.getPassword(), client.getPassword())) {
                return ResponseEntity.ok(client);
            
            // Le password ne correspond pas
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

        // Le client n'existe pas
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}*/

