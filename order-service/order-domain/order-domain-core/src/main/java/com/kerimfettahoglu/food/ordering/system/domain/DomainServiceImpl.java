package com.kerimfettahoglu.food.ordering.system.domain;

import com.kerimfettahoglu.food.ordering.system.domain.entity.Order;
import com.kerimfettahoglu.food.ordering.system.domain.entity.Restaurant;
import com.kerimfettahoglu.food.ordering.system.domain.event.OrderCancelledEvent;
import com.kerimfettahoglu.food.ordering.system.domain.event.OrderCreatedEvent;
import com.kerimfettahoglu.food.ordering.system.domain.event.OrderPayedEvent;

import java.util.List;

public class DomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        return null;
    }

    @Override
    public OrderPayedEvent payOrder(Order order) {
        return null;
    }

    @Override
    public void approveOrder(Order order) {

    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        return null;
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {

    }
}
