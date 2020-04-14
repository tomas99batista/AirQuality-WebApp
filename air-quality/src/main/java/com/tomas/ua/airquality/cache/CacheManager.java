package com.tomas.ua.airquality.cache;

import com.tomas.ua.airquality.models.Cities;

import java.util.Calendar;
import java.util.HashMap;

public class CacheManager {
    private Integer cache_hit;
    private Integer cache_miss;
    private HashMap<Long, Calendar> TTL;
    private HashMap<Long, Cities> cities_cache;

    public CacheManager() {
        cache_miss = 0;
        cache_hit = 0;
        TTL = new HashMap<>();
        cities_cache = new HashMap<>();
    }

    public void incrementCache_hit() {
        cache_hit++;
    }

    public void incrementCache_miss() {
        cache_miss++;
    }

    public void setTTL(Long id) {
        Calendar current_time = Calendar.getInstance();
        current_time.add(Calendar.MINUTE, 1);
        TTL.put(id, current_time);
    }

    public void setCities_cache(Cities city) {
        cities_cache.put(city.getIdx(), city);
        setTTL(city.getIdx());
        //System.out.println("CITIES CACHE HASHMAP: " + cities_cache);
        System.out.println("CITI ADDED: " + cities_cache + "\nWITH TTL: " + TTL.get(city.getIdx()).getTime());
    }

    public HashMap<Long, Cities> getCities_cache() {
        return cities_cache;
    }

    public Cities getCityCachedById(Long idx){
        return cities_cache.get(idx);
    }

    public boolean cachenotValid(Long idx){
        Calendar current_time = Calendar.getInstance();
        //System.out.println("TTL HASHMAP: " + TTL.values());
        if (current_time.before(TTL.get(idx)) || current_time.equals(TTL.get(idx))){
            System.out.println("-- CACHE TTL VALID: " + cities_cache.get(idx).getName() + "\n\tTTL Value: " + TTL.get(idx).getTime());
            return false;
        } else if (current_time.after(TTL.get(idx))){
            System.out.println("-- CACHE TTL NOT VALID: " + cities_cache.get(idx).getName() + "\n\tTTL Value: " + TTL.get(idx).getTime());
            return true;
        } else {
            return true;
        }
    }

    public Integer getCache_hit() {
        return cache_hit;
    }

    public Integer getCache_miss() {
        return cache_miss;
    }

    @Override
    public String toString() {
        return "CacheManager{" +
                "\ncache_hit=" + cache_hit +
                ", \ncache_miss=" + cache_miss +
                ", \nTTL=" + TTL +
                ", \ncities_cache=" + cities_cache +
                '}';
    }
}
