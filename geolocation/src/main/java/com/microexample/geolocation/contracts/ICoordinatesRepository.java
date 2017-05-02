package com.microexample.geolocation.contracts;

import com.microexample.geolocation.models.*;

public interface ICoordinatesRepository {
    public void save(Coordinate coordinate);
}
