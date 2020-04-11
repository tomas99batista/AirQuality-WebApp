package com.tomas.ua.airquality.cache;

import com.tomas.ua.airquality.models.Cities;
import org.thymeleaf.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CacheManager {
    private Integer cache_hit = 0;
    private Integer cache_miss = 0;
    private Calendar TTL;
    Cities last_city;

    public CacheManager() {
    }

    public Integer getCache_hit() {
        return cache_hit;
    }

    public void incrementCache_hit() {
        cache_hit++;
    }

    public Integer getCache_miss() {
        return cache_miss;
    }

    public void incrementCache_miss() {
        cache_miss++;
    }

    public Calendar getTTL() {
        return TTL;
    }

    public void setTTL() {
        Calendar current_time = Calendar.getInstance();
        current_time.add(Calendar.MINUTE, 1);
        TTL = current_time;
    }
    

    public void setLast_city(Cities last_city) {
        this.last_city = last_city;
        setTTL();
        System.out.println("CITI ADDED: " + last_city + "\nWITH TTL: " + TTL.getTime());
    }

    public Cities getLast_city() {
        return last_city;
    }

    public boolean cachenotValid(){
        Calendar current_time = Calendar.getInstance();
        if (current_time.before(TTL) || current_time.equals(TTL)){
            System.out.println("CACHE TTL VALID");
            return false;
        } else {
            System.out.println("CACHE TTL NOT VALID");
            return true;
        }
    }


}
