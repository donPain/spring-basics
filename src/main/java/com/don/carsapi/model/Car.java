package com.don.carsapi.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "t_car")
public class Car implements BaseModel<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36, nullable = false, updatable = false)
    private UUID uuid;

    private String model;

    @Override
    public UUID getId() {
        return this.uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
