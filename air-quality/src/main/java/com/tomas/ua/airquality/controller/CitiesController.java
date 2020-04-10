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
        Cities pedido = citiesRepository.findTopByIdxOrderByIdgeratedDesc(idx);
        if (pedido == null){
            System.out.println("miss, nao ha na cache");
        } else {
            System.out.println("hit, esta em cache");
        }
        return citiesRepository.findTopByIdxOrderByIdgeratedDesc(idx);
    }

    // TODO:
    @GetMapping("/api/{city}")
    Cities getCityFromApi(@PathVariable(value = "city") String city) throws JsonProcessingException {
        final String uri = "https://api.waqi.info/feed/"+city+"/?token=41b33a02bd2d16e5f587310917b819e826cdbb58";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HashMap infos = mapper.readValue(result, HashMap.class);
        HashMap data = (HashMap) infos.get("data");

        //System.out.println(data);

        HashMap city_map = (HashMap) data.get("city");
        HashMap time_map = (HashMap) data.get("time");
        HashMap iaqi_map = (HashMap) data.get("iaqi");

        Long idx = Long.parseLong(data.get("idx").toString()); //Unique ID for the city monitoring station.
        String name = city_map.get("name").toString(); //Name of the monitoring station.
        String timestamp = time_map.get("s").toString(); //Tempo da leitura
        Double aqi = Double.parseDouble(data.get("aqi").toString()); //Real-time air quality information.

        HashMap pm25_map = (HashMap) iaqi_map.get("pm25");
        Double pm25 = Double.parseDouble(pm25_map.get("v").toString()); //PM 2.5

        HashMap pm10_map = (HashMap) iaqi_map.get("pm10");
        Double pm10 = Double.parseDouble(pm10_map.get("v").toString()); //PM 10

        HashMap o3_map = (HashMap) iaqi_map.get("o3");
        Double o3 = Double.parseDouble(o3_map.get("v").toString());   //Ozono

        HashMap no2_map = (HashMap) iaqi_map.get("no2");
        Double no2 = Double.parseDouble(no2_map.get("v").toString()); //Dióxido de nitrogénio

        HashMap so2_map = (HashMap) iaqi_map.get("so2");
        Double so2 = Double.parseDouble(so2_map.get("v").toString()); //Dióxido de enxofre

        HashMap t_map = (HashMap) iaqi_map.get("t");
        Double t = Double.parseDouble(t_map.get("v").toString());    //Temperature

        HashMap p_map = (HashMap) iaqi_map.get("p");
        Double p = Double.parseDouble(p_map.get("v").toString());

        HashMap h_map = (HashMap) iaqi_map.get("h");
        Double h= Double.parseDouble(h_map.get("v").toString());     //Humidity

        HashMap w_map = (HashMap) iaqi_map.get("w");
        Double w= Double.parseDouble(w_map.get("v").toString());

        //System.out.println("idx: "+idx+", name: " +  name +", timestamp: " +timestamp+", aqi: "+ aqi+", pm25: " +pm25+", pm10: "+ pm10+", o3: "+o3+", no2: " +no2+ ", so2"+ so2+ ", t"+ t+", p: "+ p+ ", h: "+ h+", w: " +w);

        Cities cities = new Cities(idx, name, timestamp, aqi, pm25, pm10, o3, no2, so2, t, p, h, w);
        citiesRepository.save(cities);

        System.out.println(cities);
        return cities;
    }

    // Aqui tem de testar a cache hit ou miss, se tiver na bd vai buscar, se nao vai buscar ao site
}
