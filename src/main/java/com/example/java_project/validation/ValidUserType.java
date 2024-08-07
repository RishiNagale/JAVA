package com.example.java_project.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.validation.annotation.Validated;

import lombok.*;

@Documented
@Constraint(validatedBy = UserTypeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserType {
	
    public String message() default "Invalid user type: either admin or normal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}