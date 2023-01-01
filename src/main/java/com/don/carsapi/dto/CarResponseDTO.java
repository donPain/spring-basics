package com.don.carsapi.dto;


import com.don.carsapi.model.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@NoArgsConstructor
public class CarResponseDTO {

    @JsonProperty("class")
    private String carClass;
    private Long cylinders;
    private String fuelType;
    @JsonProperty("manufacturer")
    private String manufacturerName;


    public CarResponseDTO(Car car) {
        new ModelMapper().map(car, this);
    }


}
