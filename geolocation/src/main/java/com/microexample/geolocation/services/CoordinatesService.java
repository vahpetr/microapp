package com.microexample.geolocation.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microexample.geolocation.contracts.*;
import com.microexample.geolocation.models.*;
import com.microexample.geolocation.models.dto.*;

/**
 * Coordinates service
 */
@Service
public class CoordinatesService implements ICoordinatesService {

    // TODO in production this value must be between 128-4096 selected by experience

    /**
     * Multiplexing factor
     */
    private final int _multiplexing = 10;

    /**
     * Temporary coordinates storage
     */
    private final List<Coordinate> _coordinates = new ArrayList<>();

    /**
     * Coordinate repository
     */
    private final ICoordinatesRepository _repository;

    /**
     * Coordinates service
     * @param Coordinate repository
     */
    @Autowired
    public CoordinatesService(ICoordinatesRepository repository) {
        _repository = repository;
    }

    /**
     * Save coordinate
     * @param Coordinate dto
     */
    public void save(CoordinateDto dto) {
        Coordinate coordinate = new Coordinate(dto);
        _coordinates.add(coordinate);

        if (_coordinates.size() != _multiplexing)
            return;

        _repository.save(_coordinates);
        _coordinates.clear();
    }

    /**
     * Dispose coordinate service
     * The process must terminate correctly so that the method is called
     */
    public void dispose() {
        if (_coordinates.size() == 0) return;

        _repository.save(_coordinates);
        _coordinates.clear();
    }
}
