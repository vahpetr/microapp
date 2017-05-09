package com.microexample.geolocation.models.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

/**
 * Coordinate dto
 */
@Validated
public class CoordinateDto {

    /**
     * Device id
     */
    @Min(1)
    public int deviceId;

    /**
     * Latitude
     */
    @Range(min = -90, max = 90)
    public float latitude;

    /**
     * Longtitude
     */
    @Range(min = -180, max = 180)
    public float longitude;
}
