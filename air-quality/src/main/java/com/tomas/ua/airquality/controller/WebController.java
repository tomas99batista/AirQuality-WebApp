package com.tomas.ua.airquality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class WebController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mapa")
    public String mapa() {
        return "mapa";
    }

}

