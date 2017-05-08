package com.microexample.geolocation.controllers;

import java.net.InetAddress;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String get() {
        String hostname  = null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception ex) {
            hostname =  "Unknown";
        }
        return "geolocation: " + hostname;
    }
}
