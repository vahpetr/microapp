package com.microexample.geolocation.models;

import com.microexample.geolocation.models.dto.CoordinateDto;

public class Coordinate {

    public Coordinate(CoordinateDto dto) {
        deviceId = dto.deviceId;
        latitude = dto.latitude;
        longitude = dto.longitude;
        timestamp = System.currentTimeMillis();
    }

    public int deviceId;
    public float latitude;
    public float longitude;

    public long timestamp;
}
