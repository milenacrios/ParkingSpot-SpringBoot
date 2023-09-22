package com.api.parkingcontrol.validations.SpotNumber;

import com.api.parkingcontrol.services.ParkingSpotService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingSpotNumberValidate implements ConstraintValidator<ParkingSpotNumber, String>{
    @Autowired
    ParkingSpotService parkingSpotService;
    private String responseBody;
    @Override
    public void initialize(ParkingSpotNumber parkingSpotNumber) {
        ConstraintValidator.super.initialize(parkingSpotNumber);
        this.responseBody = parkingSpotNumber.responseBody();
    }
    @Override
    public boolean isValid(String parkingSpotNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotNumber)){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(responseBody).addConstraintViolation();
            return false;
        }
        return true;
    }
}
