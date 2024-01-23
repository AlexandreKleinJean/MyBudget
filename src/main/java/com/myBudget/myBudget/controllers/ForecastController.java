package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Forecast;
import com.myBudget.myBudget.models.Client;
import com.myBudget.myBudget.repositories.ForecastRepository;
import com.myBudget.myBudget.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
public class ForecastController {

    @Autowired
    private ForecastRepository forecastRepository;
    @Autowired
    private ClientRepository clientRepository;

    /*-------------Afficher le forecast par son Id-------------*/
    @GetMapping("/forecast/{forecastId}")
    // La method retourne => Type Forecast
    public Forecast getForecastById(@PathVariable Integer forecastId) {
        Optional<Forecast> optionalForecast = forecastRepository.findById(forecastId);

        // ResponseEntity est géré automatiquement avec "findById()"
        return optionalForecast.orElse(null);
    }

    /*-----------------Créer un forecast-----------------*/
    @PostMapping("/{clientId}/forecast")
    // La method retourne => Type ReponseEntity
    public ResponseEntity<?> createForecast(@PathVariable Integer clientId, @RequestBody Forecast newForecast) {
        Forecast savedForecast;

        // ma logique
        try {
            // j'enregistre le nouveau forecast en BDD + stockage dans variable
            savedForecast = forecastRepository.save(newForecast);
            System.out.println(savedForecast);

            // je cherche le client (ayant créé le forecast) par son Id
            Optional<Client> optionalClient = clientRepository.findById(clientId);
            System.out.println(optionalClient);

            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                // je mets à jour sa clé étrangère "forecast_id"
                client.setForecastId(savedForecast.getId());
                // j'enregistre l'update en BDD
                clientRepository.save(client);

                // ResponseEntity => envoi d'un statut 201 + variable
                return ResponseEntity.status(HttpStatus.CREATED).body(savedForecast);

            } else {
                // ResponseEntity => statut 404 + message d'erreur
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Client with id " + clientId);
            }

        } catch(Exception e){

            // ResponseEntity pris en charge par SpringBoot => statut 500
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transaction creation)");
        }
    }

    /*----------Modifier un forecast par son Id---------*/
    /*@PatchMapping("/forecast/{id}")
    // La method retourne => Type ReponseEntity
    public ResponseEntity<?> updateForecast(@PathVariable Integer id, Forecast newForecast) {
        Optional<Forecast> optionalForecast;
        
        // ma logique
        try{
            // je récupère le forecast ("optional" retourne un boolean)
            optionalForecast = forecastRepository.findById(id);

            if (optionalForecast.isPresent()) {
                // je récupère le forecast à mettre à jour
                Forecast forecastOnUpdate = optionalForecast.get();
                
                // je remplace ses infos par les infos de la nouvelle
                forecastOnUpdate.setSalary(newForecast.getSalary());
                forecastOnUpdate.setFoodRate(newForecast.getFoodRate());
                forecastOnUpdate.setTransportRate(newForecast.getTransportRate());
                forecastOnUpdate.setSportRate(newForecast.getSportRate());
                forecastOnUpdate.setInvoiceRate(newForecast.getInvoiceRate());
                forecastOnUpdate.setShoppingRate(newForecast.getShoppingRate());
                forecastOnUpdate.setLeisureRate(newForecast.getLeisureRate());
                forecastOnUpdate.setRealEstateRate(newForecast.getRealEstateRate());
        
                // j'enregistre les infos en BDD + stockage dans variable
                Forecast updatedForecast = forecastRepository.save(forecastOnUpdate);
    
                // ResponseEntity => envoi d'un statut 201 + variable
                return ResponseEntity.ok(updatedForecast);

            } else {
                // ResponseEntity => statut 404 + message d'erreur
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Forecast with id " + id);
            }

        } catch (Exception e){

            // ResponseEntity pris en charge par SpringBoot => statut 500
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (forecast edit)");
        }
    }*/
}