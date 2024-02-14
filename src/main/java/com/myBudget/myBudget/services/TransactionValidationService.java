package com.myBudget.myBudget.services;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class TransactionValidationService {

    public String transactionCreationValidation(
        String subject,
        String category,
        BigDecimal amount
    ) {
        if (subject == "" || !subject.matches("^[a-zA-Z ]+$")){
            return "Transaction subject has to contain only letters and spaces";
        }
        if (category == ""){
            return "Transaction category is mandatory";
        }
        if (amount.toString() == ""|| !amount.toString().matches("^[0-9]+$")) {
            return "Transaction amount has to contain numbers only";
        }
        return null;
    }
}


