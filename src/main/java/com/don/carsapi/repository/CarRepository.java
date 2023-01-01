package com.don.carsapi.repository;


import com.don.carsapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    @Query("select (count(c) > 0) from Car c where c.hashCode = ?1")
    boolean existsByHashCode(String hashCode);


    @Query("select c from Car c where (upper(c.model) like UPPER(CONCAT('%',?1, '%')))")
    List<Car> findByModelOrManufacturer(String model, String manufacturer);








}
