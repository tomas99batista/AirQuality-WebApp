package com.tomas.ua.airquality.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomas.ua.airquality.models.Cities;
import com.tomas.ua.airquality.models.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class CitiesController {
    @Autowired
    CitiesRepository citiesRepository;

    @GetMapping("/cities")
    public List<Cities> getAllCities() {
        return citiesRepository.findAll();
    }

    @PostMapping("/cities")
    Cities newCity (@Valid @RequestBody Cities cities){
        return citiesRepository.save(cities);
    }

    @GetMapping("/cities/{idx}")
    Cities getCitiesById (@PathVariable(value = "idx") Long idx){
        // Se ñ tiver vai buscar à api do waqi, se tiver manda aq tem
        return citiesRepository.findTopByIdxOrderByIdgeratedDesc(idx);
    }

    // TODO:
    @GetMapping("/api/{city}")
    HashMap getCityFromApi(@PathVariable(value = "city") String city) throws JsonProcessingException {
        final String uri = "https://api.waqi.info/feed/"+city+"/?token=41b33a02bd2d16e5f587310917b819e826cdbb58";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //JSON from String to Object
        HashMap cities = mapper.readValue(result, HashMap.class);
        System.out.println(cities);
        return cities;
    }

    // Aqui tem de testar a cache hit ou miss, se tiver na bd vai buscar, se nao vai buscar ao site
}
