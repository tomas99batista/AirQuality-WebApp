package com.tomas.ua.airquality.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tomas.ua.airquality.models.Cities;
import com.tomas.ua.airquality.models.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
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

    @GetMapping("/api/{city}")
    JsonObject getCityFromApi(@PathVariable(value = "city") String city)
    {
        final String uri = "https://api.waqi.info/feed/"+city+"/?token=41b33a02bd2d16e5f587310917b819e826cdbb58";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String result = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();

        return jsonObject;
    }

    // Aqui tem de testar a cache hit ou miss, se tiver na bd vai buscar, se nao vai buscar ao site
}
