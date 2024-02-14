package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Client;
import com.myBudget.myBudget.repositories.ClientRepository;
import com.myBudget.myBudget.security.JwtUtil;
import com.myBudget.myBudget.services.RegisterValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class AuthController {

    // J'injecte le ClientRepository pour accéder aux methods CRUD 
    @Autowired
    private ClientRepository clientRepository;

    // J'injecte le PasswordEncord pour accéder à la method de hachage
    @Autowired
    private PasswordEncoder passwordEncoder;

    // J'injecte mon fichier pour valider les données d'inscription
    @Autowired
    private RegisterValidationService registerValidationService;


    /*-----------------S'inscrire (Signin)-----------------*/
    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody Client newClient) {

        // Je check que l'email n'existe pas déjà
        Client client = clientRepository.findByEmail(newClient.getEmail());

        if (client != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Email already used");
        } 

        // Je check que les données reçues sont au format correct
        String validationError = registerValidationService.registrationValidation(
            newClient.getGender(),
            newClient.getFirstname(),
            newClient.getLastname(),
            newClient.getEmail(),
            newClient.getPassword()
        );

        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }
        
        // Je remplace le password brut du user par son password hachée
        newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
        
        // J'enregistre le nouveau user en BDD
        Client savedClient = clientRepository.save(newClient);

        // J'envoi un code 201 + les infos du nouveau user
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    /*------------------Se connecter (Login)-------------------*/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Client loggedClient) {

        // Je cherche un client qui a l'email correspondant
        Client client = clientRepository.findByEmail(loggedClient.getEmail());

        // Si email n'existe pas
        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("No user with this email");
        } 

        // Si password entré est différent de password haché stocké en BDD
        if (!passwordEncoder.matches(loggedClient.getPassword(), client.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Password is not correct");
        }  
                
        // Génération du JWT avec l'e-mail du client
        String jwtToken = JwtUtil.generateJwtToken(client.getEmail());
        System.out.println("JWT généré : " + jwtToken);

        // Je retourne la reponse => Jwt unique + infos du client
        return ResponseEntity.ok()
            .header("Authorization", "Bearer " + jwtToken)
            .body(client);
    }
}

