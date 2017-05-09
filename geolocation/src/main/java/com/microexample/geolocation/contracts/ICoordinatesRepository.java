package com.microexample.geolocation.contracts;

import java.util.List;

import com.microexample.geolocation.models.*;

/**
 * Coordinate repository interface
 */
public interface ICoordinatesRepository {
    /**
     * Save coordinates array
     * @param Coordinates array
     */
    void save(List<Coordinate> coordinate);
}
