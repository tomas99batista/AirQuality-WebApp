package com.tomas.ua.airquality.repository;

import com.tomas.ua.airquality.models.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Long> {
    Cities findByIdx(long idx);
    Cities findTopByIdxOrderByIdgeratedDesc(long idx);
}
