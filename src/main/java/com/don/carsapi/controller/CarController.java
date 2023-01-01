package com.don.carsapi.controller;

import com.don.carsapi.model.Car;
import com.don.carsapi.repository.CarRepository;
import com.don.carsapi.serivce.ExternalCarService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cars")
public class CarController extends AbstractController<Car, UUID, CarRepository>  {

    private final ExternalCarService externalCarService;

    @GetMapping("/external")
    public JsonNode findByModel(@RequestParam("model") String  model) throws IOException {
        return externalCarService.externalModelSearch(model);
    }



}
