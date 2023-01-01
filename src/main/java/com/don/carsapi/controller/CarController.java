package com.don.carsapi.controller;

import com.don.carsapi.model.Car;
import com.don.carsapi.repository.CarRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping
public class CarController extends AbstractController<Car, UUID, CarRepository>  {




}
