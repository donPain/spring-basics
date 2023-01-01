package com.don.carsapi.serivce;


import com.don.carsapi.model.Car;
import com.don.carsapi.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final ExternalCarService externalCarService;
    private final CarRepository repository;
    private final GoogleImageService googleImageService;


    public List<Car> findCarByModelOrManufacturer(String model, String manufacturer) throws IOException {
        var currentDb = repository.findByModelOrManufacturer(model, manufacturer);
        if (currentDb.isEmpty()) {
            saveNewModel(externalCarService.externalModelSearch(model));
            return repository.findByModelOrManufacturer(model, manufacturer);
        }else {
            return currentDb;
        }
    }

    public void saveNewModel(List<Car> carResponseList) {
        carResponseList.forEach(car -> {
            try {
                car.setImageUrl(googleImageService.getImage(car.getDescription()));
            } catch (Exception e) {
                car.setImageUrl(null);
            }
        });
        repository.saveAll(carResponseList);
    }


}
