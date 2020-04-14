package com.tomas.ua.airquality;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tomas.ua.airquality.controller.CitiesController;
import com.tomas.ua.airquality.models.Cities;
import com.tomas.ua.airquality.repository.CitiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tomas.ua.airquality.cache.CacheManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CitiesControllerTest {

    @Autowired
    CitiesController citiesController;

    @Autowired
    CitiesRepository citiesRepository;

    CacheManager cacheManager;

    @BeforeEach
    void beforeEach(){
        System.out.println("---TESTS---");
        cacheManager = new CacheManager();
    }

    @Test
    public void apiStats(){
        CitiesController citiesController1 = new CitiesController();
        citiesController1.incrementApiCount();
        String string = "Calls to Api on this session: " + CitiesController.ApiStats;
        String api_stats = citiesController1.getApiStats();
        assertEquals(string, api_stats);
    }

    @Test
    public void cacheStats(){
        assertEquals(cacheManager.toString(), citiesController.returnCache());
    }

    @Test
    public void getApiExtern() throws JsonProcessingException {
        assertEquals(citiesController.getCityFromApi("Lisboa").getIdgerated(), citiesRepository.findTopByIdxOrderByIdgeratedDesc(8379).getIdgerated());
    }

    @Test
    public void testGetCitiesByID() throws JsonProcessingException {
        Cities city4 = citiesController.getCitiesById((long) 5725);
        Cities city = new Cities((long) 8379, "Entrecampos, Lisboa, Portugal", "2020-04-10 12:00:00", 21.0, 18.0, 8.0, 20.8, 14.3, 0.4, 16.0, 1020.0, 100.0, 2.0);
        citiesRepository.save(city);
        Cities city2 = citiesController.getCitiesById((long) 8379);
        Cities city3 = citiesController.getCitiesById((long) 8379);
        assertNotEquals(city.getIdgerated(), city2.getIdgerated());
        assertNotEquals(city.getIdgerated(), city3.getIdgerated());
        assertNotEquals(city.getIdgerated(), city4.getIdgerated());
    }

    @Test
    public void getAllCities(){
            List<Cities> citiesList = citiesController.getAllCities();
            assertEquals(citiesList.size(), citiesRepository.findAll().size());
    }

    @Test
    public void newCity(){
        Cities city2 = new Cities((long) 8379, "Entrecampos, Lisboa, Portugal", "2020-04-10 12:00:00", 21.0, 18.0, 8.0, 20.8, 14.3, 0.4, 16.0, 1020.0, 100.0, 2.0);
        Cities city_Saved = citiesRepository.save(city2);
        citiesController.newCity(city2);
        Long idGerated = citiesRepository.findTopByIdxOrderByIdgeratedDesc(8379).getIdgerated();
        assertEquals(city_Saved.getIdgerated(), idGerated);
    }
}
