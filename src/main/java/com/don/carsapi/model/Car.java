package com.don.carsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_car")
public class Car implements BaseModel<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 36, nullable = false, updatable = false)
    private UUID uuid;

    private String model;
    private String manufacturer;
    private String year;
    private String imageUrl;
    private Date createdAt = Date.from(Instant.now());
    private String hashCode;

    @Override
    @JsonIgnore
    public UUID getId() {
        return this.uuid;
    }


    public Car(JsonNode externalCarObj) {
        this.model = externalCarObj.get("model").asText();
        this.year = externalCarObj.get("year").asText();
        this.manufacturer = externalCarObj.get("make").asText();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return new EqualsBuilder().append(model, car.model).append(manufacturer, car.manufacturer).append(year, car.year).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(model).append(manufacturer).append(year).toHashCode();
    }

    public String getDescription() {
        return this.getManufacturer().concat(" ") + this.getModel().concat(" ") + this.getYear();

    }

}
