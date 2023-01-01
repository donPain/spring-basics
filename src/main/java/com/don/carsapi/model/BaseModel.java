package com.don.carsapi.model;

import jakarta.persistence.Id;

import java.io.Serializable;

public interface BaseModel<I extends Serializable> extends Serializable {
    I getId();
}
