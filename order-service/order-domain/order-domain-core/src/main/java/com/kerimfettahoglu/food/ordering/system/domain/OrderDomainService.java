package com.kerimfettahoglu.food.ordering.system.domain;

import com.kerimfettahoglu.food.ordering.system.domain.entity.Order;
import com.kerimfettahoglu.food.ordering.system.domain.entity.Restaurant;
import com.kerimfettahoglu.food.ordering.system.domain.event.OrderCancelledEvent;
import com.kerimfettahoglu.food.ordering.system.domain.event.OrderCreatedEvent;
import com.kerimfettahoglu.food.ordering.system.domain.event.OrderPayedEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);
    OrderPayedEvent payOrder(Order order);
    void approveOrder(Order order);
    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);
    void cancelOrder(Order order, List<String> failureMessages);
}
