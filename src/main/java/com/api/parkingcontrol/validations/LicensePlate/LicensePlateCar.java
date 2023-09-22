package com.api.parkingcontrol.validations.LicensePlate;
import com.api.parkingcontrol.validations.LicensePlate.LicensePlateCarValidate;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

@Constraint(validatedBy = LicensePlateCarValidate.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LicensePlateCar {
    public String message() default "Conflict: License Plate car is already in use!";
    public HttpStatus statusCode() default HttpStatus.CONFLICT;
    String responseBody() default "Conflict: License Plate car is already in use!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
