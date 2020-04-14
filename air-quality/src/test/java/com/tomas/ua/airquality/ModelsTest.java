package com.tomas.ua.airquality;

import com.tomas.ua.airquality.models.Cities;
import com.tomas.ua.airquality.repository.CitiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModelsTest {

    @Autowired
    private CitiesRepository citiesRepository;

    Cities city;

    @BeforeEach
    void beforeEach(){
        System.out.println("---TESTS---");
        city = new Cities((long) 8379, "Entrecampos, Lisboa, Portugal", "2020-04-10 12:00:00", 21.0, 18.0, 8.0, 20.8, 14.3, 0.4, 16.0, 1020.0, 100.0, 2.0);
        citiesRepository.save(city);
    }

    @Test
    public void assertNotNull(){
        Cities city_found = citiesRepository.findTopByIdxOrderByIdgeratedDesc(8379);
        System.out.println(city_found);
        assertThat(city_found).isNotNull();
    }

    @Test
    public void getMethods(){
        assertThat(citiesRepository.findTopByIdxOrderByIdgeratedDesc(8379)).isEqualToComparingFieldByField(city);
    }


    @Test
    public void myTest() throws Exception {
        citiesRepository.save(city);
    }

}
