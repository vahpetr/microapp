package com.microexample.geolocation.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.microexample.geolocation.contracts.ICoordinatesService;
import com.microexample.geolocation.models.dto.CoordinateDto;;

@Controller
@RequestMapping("/coordinates")
public class CoordinatesController {

    private final ICoordinatesService _coordinatesService;

    @Autowired
    public CoordinatesController(ICoordinatesService coordinatesService) {
        _coordinatesService = coordinatesService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody void post(@Valid @RequestBody CoordinateDto coordinate) {
        _coordinatesService.save(coordinate);
    }
}
