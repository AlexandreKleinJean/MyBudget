package com.myBudget.myBudget.services;

import org.springframework.stereotype.Service;

@Service
public class AccountValidationService {

    public String accountCreationValidation(
        String name,
        String bank
    ) {
        if (name.isEmpty() || !name.matches("^[a-zA-Z ]+$")){
            return "Account name has to contain only letters and spaces";
        }
        if (bank.isEmpty() || !bank.matches("^[a-zA-Z ]+$")) {
            return "Account bank has to contain only letters and spaces";
        }
        return null;
    }
}
