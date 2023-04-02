package com.kerimfettahoglu.food.ordering.system.domain.entity;

import com.kerimfettahoglu.food.ordering.system.domain.valueobject.Money;
import com.kerimfettahoglu.food.ordering.system.domain.valueobject.ProductId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;
}
