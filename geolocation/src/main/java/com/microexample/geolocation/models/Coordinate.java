package com.microexample.geolocation.models;

import com.microexample.geolocation.models.dto.CoordinateDto;

/**
 * Coordinate model
 */
public class Coordinate {

    /**
     * Coordinate model constructor
     * @param Coordinate dto
     */
    public Coordinate(CoordinateDto dto) {
        deviceId = dto.deviceId;
        latitude = dto.latitude;
        longitude = dto.longitude;

        // set current time
        timestamp = System.currentTimeMillis();
    }

    /**
     * Device id
     */
    public int deviceId;

    /**
     * Latitude
     */
    public float latitude;

    /**
     * Longitude
     */
    public float longitude;

    /**
     * Current time in utc
     */
    public long timestamp;
}
