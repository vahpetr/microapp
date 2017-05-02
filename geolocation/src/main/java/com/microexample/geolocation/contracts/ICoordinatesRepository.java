package com.microexample.geolocation.contracts;

import com.microexample.geolocation.models.*;

public interface ICoordinatesRepository {
    void save(Coordinate coordinate);
}
