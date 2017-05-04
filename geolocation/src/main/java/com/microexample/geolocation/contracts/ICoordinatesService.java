package com.microexample.geolocation.contracts;

import com.microexample.geolocation.models.dto.*;

public interface ICoordinatesService extends IDisposable {
    void save(CoordinateDto dto);
}
