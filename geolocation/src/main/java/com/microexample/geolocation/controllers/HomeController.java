package com.microexample.geolocation.controllers;

import java.net.InetAddress;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Home controller
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Info about current instance GET /
     * @return html/text name: containerId
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String get() {
        String hostname  = null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception ex) {
            hostname =  "unknown";
        }
        return "geolocation: " + hostname;
    }
}
