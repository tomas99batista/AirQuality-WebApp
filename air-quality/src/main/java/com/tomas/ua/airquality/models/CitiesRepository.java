package com.tomas.ua.airquality.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Long> {
    Cities findByIdx(long idx);
    Cities findTopByIdxOrderByIdgeratedDesc(long idx);
}
