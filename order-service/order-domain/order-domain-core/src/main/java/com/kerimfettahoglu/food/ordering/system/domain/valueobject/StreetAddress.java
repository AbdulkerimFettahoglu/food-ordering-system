package com.kerimfettahoglu.food.ordering.system.domain.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class StreetAddress {
    @EqualsAndHashCode.Exclude
    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;
}
