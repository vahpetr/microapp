package com.microexample.geolocation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microexample.geolocation.contracts.ICoordinatesRepository;
import com.microexample.geolocation.contracts.ICoordinatesService;
import com.microexample.geolocation.models.*;

@Service
public class CoordinatesService implements ICoordinatesService {

    private final ICoordinatesRepository _repository;

    @Autowired
    public CoordinatesService(ICoordinatesRepository repository) {
        _repository = repository;
    }

    public void save(Coordinate coordinate) {
        _repository.save(coordinate);
    }
}
