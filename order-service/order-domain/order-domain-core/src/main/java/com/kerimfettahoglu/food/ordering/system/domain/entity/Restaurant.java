package com.kerimfettahoglu.food.ordering.system.domain.entity;

import com.kerimfettahoglu.food.ordering.system.domain.valueobject.RestaurantId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private boolean active;
}
