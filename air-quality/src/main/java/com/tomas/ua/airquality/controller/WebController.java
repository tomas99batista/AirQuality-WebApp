package com.tomas.ua.airquality.controller;

import com.tomas.ua.airquality.models.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class WebController {
    @Autowired
    CitiesRepository citiesRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home(Model model) {
        model.addAttribute("info", citiesRepository.findTopByIdxOrderByIdgeratedDesc(8379));
        return "lisbon";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/lisbon")
    public String index(Model model) {
        model.addAttribute("info", citiesRepository.findTopByIdxOrderByIdgeratedDesc(8379));
        return "lisbon";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/madrid")
    public String mapa(Model model) {
        model.addAttribute("info", citiesRepository.findTopByIdxOrderByIdgeratedDesc(5725));
        return "madrid";
    }

}

