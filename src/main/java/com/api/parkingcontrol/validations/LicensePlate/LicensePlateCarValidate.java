package com.api.parkingcontrol.validations.LicensePlate;

import com.api.parkingcontrol.services.ParkingSpotService;
import com.api.parkingcontrol.validations.LicensePlate.LicensePlateCar;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

public class LicensePlateCarValidate implements ConstraintValidator<LicensePlateCar, String> {
    @Autowired
    ParkingSpotService parkingSpotService;
    private String responseBody;
    @Override
    public void initialize(LicensePlateCar licensePlateCar) {
        ConstraintValidator.super.initialize(licensePlateCar);
        this.responseBody = licensePlateCar.responseBody();
    }
    @Override
    public boolean isValid(String licensePlate, ConstraintValidatorContext constraintValidatorContext) {
        if (parkingSpotService.existsByLicensePlateCar(licensePlate)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(responseBody).addConstraintViolation();
            return false;
        }
        return true;
    }
}