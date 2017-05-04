package com.microexample.geolocation.contracts;

import java.util.List;

import com.microexample.geolocation.models.*;

public interface ICoordinatesRepository {
    void save(List<Coordinate> coordinate);
}
