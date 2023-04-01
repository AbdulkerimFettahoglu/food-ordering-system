package com.kerimfettahoglu.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * This class is not part of the architecture (domain driven design). It's just a common class about our business.
 */
public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID value) {
        super(value);
    }
}

