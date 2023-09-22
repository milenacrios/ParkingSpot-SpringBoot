package com.api.parkingcontrol.validations.Apartment;

import com.api.parkingcontrol.services.ParkingSpotService;
import com.api.parkingcontrol.validations.LicensePlate.LicensePlateCar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ApartmentValidate implements ConstraintValidator<Apartment, String> {
    @Autowired
    ParkingSpotService parkingSpotService;
    private String responseBody;
    @Override
    public void initialize(Apartment apartment) {
        ConstraintValidator.super.initialize(apartment);
        this.responseBody = apartment.responseBody();
    }

    @Override
    public boolean isValid(String apartment, ConstraintValidatorContext constraintValidatorContext) {
        if (parkingSpotService.existsByApartment(apartment)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(responseBody).addConstraintViolation();
            return false;
        }
        return true;
    }
}
