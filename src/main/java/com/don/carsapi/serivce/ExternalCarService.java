package com.don.carsapi.serivce;

import com.don.carsapi.model.Car;
import com.don.carsapi.repository.CarRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalCarService {

    @Value("${app.secrets.externalKey}")
    private String externalKey;

    private final CarRepository carRepository;

    public List<Car> externalModelSearch(String model) throws IOException {
        URL url = new URL(String.format("https://api.api-ninjas.com/v1/cars?model=%s", model));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-Api-Key", externalKey);
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.readTree(responseStream);
        return trasnforExternalResponse(json);
    }


    public List<Car> trasnforExternalResponse(JsonNode jsonNode) {
        var carList = new ArrayList<Car>();
        if (jsonNode instanceof ArrayNode) {
            jsonNode.forEach(externalObj -> {
                ObjectNode object = (ObjectNode) externalObj;
                object.remove("transmission");
                var carModel = new Car(externalObj);
                carModel.setHashCode(String.valueOf(carModel.hashCode()));
                if(!carRepository.existsByHashCode(carModel.getHashCode())) carList.add(carModel);
            });
            return carList;
        }
        return carList;
    }
}
