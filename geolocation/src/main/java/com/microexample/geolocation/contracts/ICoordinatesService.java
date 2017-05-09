package com.microexample.geolocation.contracts;

import com.microexample.geolocation.models.dto.*;

/**
 * Coordinate service interface
 */
public interface ICoordinatesService extends IDisposable {
    /**
     * Save coordinate
     * @param Coordinate dto
     */
    void save(CoordinateDto dto);
}
