package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.User;
import com.myBudget.myBudget.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /*-----------------Afficher tous les users-------------------*/
    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();

        // ResponseEntity est géré automatiquement avec "findAll()"
        return users;
    }

    /*----------------Afficher le user par son Id-----------------*/
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        // ResponseEntity est géré automatiquement avec "findById()"
        return optionalUser.orElse(null);
    }    

    /*------------Créer un nouveau User (inscription)--------------*/
    @PostMapping("/user")
    public ResponseEntity<User> createUser(User newUser) {

        // J'enregistre le nouveau User en BDD
        User savedUser = userRepository.save(newUser);

        // J'envoi un statut 201 + les infos du nouveau user
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /*------------Modifier les infos d'un user par son Id-----------*/
    @PatchMapping("/user/{id}")
    public User updateUser(@PathVariable Integer id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> {
            // Je remplace les infos du User existant par les infos du nouveau
            user.setGender(updatedUser.getGender());
            user.setFirstname(updatedUser.getFirstname());
            user.setLastname(updatedUser.getLastname());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
    
            // J'enregistre les infos en BDD
            userRepository.save(user);
    
            return user;
        }).orElse(null);
    }

    /*----------------Supprimer un user par son Id-----------------*/
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);

            // Suppression ok => j'envoi un statut 204
            return ResponseEntity.noContent().build();
        } else {
            // user non trouvé => j'envoi un statut 404
            return ResponseEntity.notFound().build();
        }
    }
}
