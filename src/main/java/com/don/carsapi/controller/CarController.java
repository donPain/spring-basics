package com.don.carsapi.controller;

import com.don.carsapi.model.Car;
import com.don.carsapi.repository.CarRepository;
import com.don.carsapi.serivce.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cars")
public class CarController extends AbstractController<Car, UUID, CarRepository>  {

    private final CarService carService;

    @GetMapping("/external")
    public ResponseEntity<List<Car>> findByModel(@RequestParam("model") String  model, @RequestParam(value = "manufacturer", required = false) String manufacturer) throws IOException {
        return ResponseEntity.ok(carService.findCarByModelOrManufacturer(model, manufacturer));
    }




}
