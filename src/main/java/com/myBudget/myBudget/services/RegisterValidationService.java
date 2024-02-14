package com.myBudget.myBudget.services;

import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class RegisterValidationService {

    public String registrationValidation(
        String gender,
        String firstname,
        String lastname,
        String email,
        String password
    ) {
        if (gender == ""){
            return "Gender has to be Male, Female or Other";
        }
        if (firstname == "" || !firstname.matches("^[a-zA-Z ]+$")) {
            return "Firstname has to contain only letters and spaces";
        }
        if (lastname == "" || !lastname.matches("^[a-zA-Z ]+$")) {
            return "Lastname has to contain only letters and spaces";
        }
        if (email == "" || !EmailValidator.getInstance().isValid(email)) {
            return "Email format is not correct";
        }
        if (password == "") {
            return "Password is mandatory";
        }
        return null;
    }

    public String loginValidation(
        String email,
        String password
    ) {
        if (email == "") {
            return "Email mandatory";
        }
        if (password == "") {
            return "Password is mandatory";
        }
        return null;
    }
}
