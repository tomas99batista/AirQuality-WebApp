package com.tomas.ua.airquality.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomas.ua.airquality.models.Cities;
import com.tomas.ua.airquality.models.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
class WebController {

    @Autowired
    CitiesController citiesController = new CitiesController();

    // lisbon page
    @RequestMapping(method = RequestMethod.GET, value = "/")
    String home(Model model) throws JsonProcessingException {
        Cities pedido = citiesController.getCitiesById((long) 8379);
        model.addAttribute("info", pedido);
        citiesController.incrementApiCount();
        return "lisbon";
    }

    // madrid page
    @RequestMapping(method = RequestMethod.GET, value = "/madrid")
    String mapa(Model model) throws JsonProcessingException {
        Cities pedido = citiesController.getCitiesById((long) 5725);
        model.addAttribute("info", pedido);
        citiesController.incrementApiCount();
        return "madrid";
    }
}

