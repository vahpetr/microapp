package com.microexample.geolocation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/coordinates")
public class CoordinatesController {

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String get() {
        return "Hello world";
    }

}
