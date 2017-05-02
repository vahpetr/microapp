package com.microexample.geolocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.microexample.geolocation.contracts.ICoordinatesRepository;
import com.microexample.geolocation.contracts.ICoordinatesService;
import com.microexample.geolocation.repositories.CoordinatesRepository;
import com.microexample.geolocation.services.CoordinatesService;

@SpringBootApplication
@Configuration
public class GeolocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeolocationApplication.class, args);
	}

    @Bean
    @Scope("singleton")
    public ICoordinatesService coordinatesService() {
        ICoordinatesRepository coordinatesRepository = coordinatesRepository();
        return new CoordinatesService(coordinatesRepository);
    }

    @Bean
    @Scope("singleton")
    public ICoordinatesRepository coordinatesRepository() {
        return new CoordinatesRepository();
    }
}
