package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Client;
import com.myBudget.myBudget.repositories.ClientRepository;
import com.myBudget.myBudget.security.JwtUtil;
import com.myBudget.myBudget.services.AuthValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class AuthController {

    // ClientRepository => accéder aux methods CRUD 
    @Autowired
    private ClientRepository clientRepository;

    // PasswordEncord => accéder à la method de hachage
    @Autowired
    private PasswordEncoder passwordEncoder;

    // RegisterValidationService => valider les données d'inscription
    @Autowired
    private AuthValidationService authValidationService;


    /*-----------------S'inscrire (Signin)-----------------*/
    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody Client newClient) {

        // Je check que les données reçues sont au format correct
        String validationError = authValidationService.registrationValidation(
            newClient.getGender(),
            newClient.getFirstname(),
            newClient.getLastname(),
            newClient.getEmail(),
            newClient.getPassword()
        );

        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        // Je check que l'email n'existe pas déjà
        Client client = clientRepository.findByEmail(newClient.getEmail());

        if (client != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Email already used");
        } 
        
        // Je remplace le password brut du user par son password hachée
        newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
        
        // J'enregistre le nouveau user en BDD
        Client savedClient = clientRepository.save(newClient);

        // J'envoi un code 201 + infos du nouveau client
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    /*------------------Se connecter (Login)-------------------*/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Client loggedClient) {

        // Je check que les données reçues sont au format correct
        String validationError = authValidationService.loginValidation(
            loggedClient.getEmail(),
            loggedClient.getPassword()
        );

        if (validationError != null) {
            return ResponseEntity.badRequest().body(validationError);
        }

        // Je check que l'email correspond à un client
        Client client = clientRepository.findByEmail(loggedClient.getEmail());

        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("No user with this email");
        } 

        //  Je check que => password entré = password haché stocké en BDD
        if (!passwordEncoder.matches(loggedClient.getPassword(), client.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Password is not correct");
        }  
                
        // Je génère un token JWT associé à l'email du client
        String jwtToken = JwtUtil.generateJwtToken(client.getEmail());
        System.out.println("JWT généré : " + jwtToken);

        // Je retourne la reponse => Jwt unique + infos du client
        return ResponseEntity.ok()
            .header("Authorization", "Bearer " + jwtToken)
            .body(client);
    }
}

