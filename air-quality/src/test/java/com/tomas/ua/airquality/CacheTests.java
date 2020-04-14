package com.tomas.ua.airquality;

import com.tomas.ua.airquality.cache.CacheManager;
import com.tomas.ua.airquality.models.Cities;
import com.tomas.ua.airquality.repository.CitiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CacheTests {
    @Autowired
    CitiesRepository citiesRepository;
    CacheManager cacheManager;
    Cities city;

    @BeforeEach
    void beforeEach(){
        System.out.println("---TESTS---");
        cacheManager = new CacheManager();
        city = new Cities((long) 8379, "Entrecampos, Lisboa, Portugal", "2020-04-10 12:00:00", 21.0, 18.0, 8.0, 20.8, 14.3, 0.4, 16.0, 1020.0, 100.0, 2.0);
        citiesRepository.save(city);
    }

    @Test
    public void assertMissesandHits(){
        cacheManager.incrementCacheHit();
        assertEquals(cacheManager.getCacheHit(), 1);
        cacheManager.incrementCacheMiss();
        assertEquals(cacheManager.getCacheMiss(), 1);
    }

    @Test
    public void assertSetandGetCache(){
        cacheManager.setCitiesCache(city);
        System.out.println(cacheManager);
        Long city_number = cacheManager.getCitiesCache().get((long) 8379).getIdx();
        assertEquals(city_number, city.getIdx());
        assertEquals(city, cacheManager.getCityCachedById((long) 8379));
    }


    @Test
    public void assertTLLTimes(){
        cacheManager.setCitiesCache(city);
        assertFalse(cacheManager.cachenotValid((long) 8379));
        assertFalse(cacheManager.cachenotValid((long) 8379));
    }
}
