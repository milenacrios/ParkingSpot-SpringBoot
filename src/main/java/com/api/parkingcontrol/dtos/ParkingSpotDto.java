package com.api.parkingcontrol.dtos;

import com.api.parkingcontrol.validations.Apartment.Apartment;
import com.api.parkingcontrol.validations.LicensePlate.LicensePlateCar;
import com.api.parkingcontrol.validations.SpotNumber.ParkingSpotNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ParkingSpotDto {

    @NotBlank
    @ParkingSpotNumber()
    private String parkingSpotNumber;
    @NotBlank
    @Size(max = 7)
    @LicensePlateCar()
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;
    @NotBlank
    private String responsibleName;
    @NotBlank
    @Apartment()
    private String apartment;


    private String block;
    //pode haver condominios de um único bloco, então ele pode ser opcional.
}
