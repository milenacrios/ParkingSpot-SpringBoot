package com.api.parkingcontrol.validations.SpotNumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ParkingSpotNumberValidate.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParkingSpotNumber {
    public String message() default "Conflict: Parking Spot is already in use!";
    public HttpStatus statusCode() default HttpStatus.CONFLICT;
    String responseBody() default "Conflict: Parking Spot is already in use!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
