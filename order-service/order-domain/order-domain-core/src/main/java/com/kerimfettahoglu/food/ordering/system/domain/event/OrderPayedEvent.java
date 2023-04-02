package com.kerimfettahoglu.food.ordering.system.domain.event;

import com.kerimfettahoglu.food.ordering.system.domain.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Getter
public class OrderPayedEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;
}
