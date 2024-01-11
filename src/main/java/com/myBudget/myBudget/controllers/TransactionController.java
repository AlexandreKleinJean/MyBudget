package com.myBudget.myBudget.controllers;

import com.myBudget.myBudget.models.Transaction;
import com.myBudget.myBudget.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import org.springframework.http.ResponseEntity;



@RestController
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    /*-------------Afficher les transactions par compte-------------*/
    @GetMapping("/{accountId}/transactions")
    public Iterable<Transaction> getAllTransactions(@PathVariable Integer accountId) {
        // je déclare la variable transactions
        Iterable<Transaction> transactions;

        // logique de récupération des données
        try {
            // récupération des transactions par accountId
            transactions = transactionRepository.findByAccountId(accountId);
        } catch (Exception e) {
            // sinon je lance une exception => fail de la manip'
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transactions by accountId fetch)");
        }

        // je retourne la réponse => liste des transactions ou "null"
        return transactions;
    }

    /*------------Afficher une transaction par son Id-------------*/
    @GetMapping("/transaction/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> optionalTransaction;
        Transaction transaction = null;

        try {
            // récupération de la transaction (optional renvoie un boolean)
            optionalTransaction = transactionRepository.findById(id);

            // si j'obtient true
            if(optionalTransaction.isPresent()){
                // je stocke la transaction dans ma variable
                transaction = optionalTransaction.get();
            } // sinon ma variable reste "null"

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (one transaction by Id fetch)");
        }

        // je retourne la réponse => liste des transactions ou "null"
        return transaction;
    }    

    /*-----------------Créer une nouvelle transaction-----------------*/
    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction newTransaction) {
        Transaction savedTransaction;

        try {
        // J'enregistre la nouvelle transaction en BDD
        savedTransaction = transactionRepository.save(newTransaction);

        // ResponseEntity => envoi d'un statut 201 + infos de la nouvelle transaction
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);

        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transaction creation)");
        }
    }

    /*----------Modifier les infos d'une transaction par son Id---------*/
    @PatchMapping("/transaction/{id}")
    public Transaction updateTransaction(@PathVariable Integer id, Transaction newTransaction) {
        Optional<Transaction> optionalTransaction;
        
        try{
            optionalTransaction = transactionRepository.findById(id);

            if (optionalTransaction.isPresent()) {
                // Je récupère la transaction à mettre à jour
                Transaction transactionOnUpdate = optionalTransaction.get();
                
                // Je remplace ses infos par les infos de la nouvelle
                transactionOnUpdate.setSubject(newTransaction.getSubject());
                transactionOnUpdate.setNote(newTransaction.getNote());
                transactionOnUpdate.setIcon(newTransaction.getIcon());
                transactionOnUpdate.setDate(newTransaction.getDate());
                transactionOnUpdate.setCategory(newTransaction.getCategory());
                transactionOnUpdate.setAmount(newTransaction.getAmount());
                transactionOnUpdate.setAccountId(newTransaction.getAccountId());
        
                // J'enregistre les infos en BDD
                Transaction updatedTransaction = transactionRepository.save(transactionOnUpdate);
    
                // Renvoie la transaction mise à jour
                return ResponseEntity.ok(updatedTransaction);
            } else {

            }
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server failed (transaction edit)");
        }
    }

    /*--------------Supprimer une transaction par son Id---------------*/
    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Integer id) {
        Optional<Transaction> optionalTransaction;
        
        try{
            optionalTransaction = transactionRepository.findById(id);

        return optionalTransaction.delete) {
            Transaction transaction = optionalTransaction.get();
            transactionRepository.delete(transaction);

            // Suppression ok => j'envoi un statut 204
            return ResponseEntity.noContent().build();
        } else {
            // Transaction non trouvée => j'envoi un statut 404
            return ResponseEntity.notFound().build();
        }
    }
}
