package com.microexample.geolocation.models;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@Validated
public class Coordinate {

    @Min(1)
    public int deviceId;

    @Range(min = -90, max = 90)
    public float latitude;

    @Range(min = -180, max = 180)
    public float longitude;
}
