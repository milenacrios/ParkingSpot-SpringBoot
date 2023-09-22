package com.api.parkingcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class ParkingControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingControlApplication.class, args);
		System.out.println("Encode da senha: "+ new BCryptPasswordEncoder().encode("mila123"));
	}
	@GetMapping("/")
	public String index() {
		return "Olá world";
	}
}
