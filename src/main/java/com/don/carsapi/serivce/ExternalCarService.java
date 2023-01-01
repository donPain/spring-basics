package com.don.carsapi.serivce;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExternalCarService {

    @Value("${app.secrets.externalKey}")
    private String externalKey;

    public JsonNode externalModelSearch(String model) throws IOException {
        URL url = new URL(String.format("https://api.api-ninjas.com/v1/cars?model=%s", model));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-Api-Key", externalKey);
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(responseStream);
    }

}
