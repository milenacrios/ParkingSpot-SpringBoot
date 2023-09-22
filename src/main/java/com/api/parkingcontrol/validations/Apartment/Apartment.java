package com.api.parkingcontrol.validations.Apartment;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ApartmentValidate.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Apartment {
    public String message() default "Conflict: Apartment number is already in use!";
    public HttpStatus statusCode() default HttpStatus.CONFLICT;
    String responseBody() default "Conflict: Apartment number is already in use!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
