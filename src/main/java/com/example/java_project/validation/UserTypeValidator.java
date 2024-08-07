package com.example.java_project.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.java_project.repo.UserRepo;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class UserTypeValidator implements ConstraintValidator<ValidUserType, String> {

//    @Autowired
//    private UserRepo userRepo;

    @Override
    public void initialize(ValidUserType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userType, ConstraintValidatorContext context) {
       
    	List<String> userTypes = Arrays.asList("Admin", "Normal");
    	
    	return userTypes.contains(userType);
    }
}
