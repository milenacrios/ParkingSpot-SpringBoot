package com.api.parkingcontrol.controllers;
import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
    @Autowired
    ParkingSpotService parkingSpotService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Register is already in use or register canot null!");
        }
        var parkingSpotModel = new ParkingSpotModel();
        //é a mesma coisa que ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        //conversão de DTO para model
        //seria o mesmo que:
        //var parkingSpotModel = new ParkingSpotModel(parkingSpotDto);
        //parkingSpotService.save(parkingSpotModel);
        parkingSpotModel.setRegistrationDate((LocalDateTime.now(ZoneId.of("UTC"))));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id")UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id")UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(parkingSpotModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully.");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        //primeira forma de put:
        //var parkingSpotModel = parkingSpotModelOptional.get();
        //parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        //parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        //parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
        //parkingSpotModel.setModelCar(parkingSpotModel.getModelCar());
        //parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
        //parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
        //parkingSpotModel.setApartment(parkingSpotDto.getApartment());
        //parkingSpotModel.setBlock(parkingSpotDto.getBlock());

        //segunda forma de put:
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }
}
