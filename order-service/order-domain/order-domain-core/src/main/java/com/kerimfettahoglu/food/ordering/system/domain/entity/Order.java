package com.kerimfettahoglu.food.ordering.system.domain.entity;

import com.kerimfettahoglu.food.ordering.system.domain.exception.OrderDomainException;
import com.kerimfettahoglu.food.ordering.system.domain.valueobject.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Builder
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;
    /**
     * This field will be specified in runtime. So we can't mark it as final.
     */
    private TrackingId trackingId;
    /**
     * This field will be specified in runtime. So we can't mark it as final.
     */
    private OrderStatus orderStatus;
    /**
     * This field will be specified in runtime. So we can't mark it as final.
     */
    private List<String> failureMessages;

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;

    }

    private void initializeOrderItems() {
        long itemId = 1;
        for (OrderItem orderItem : items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in correct state for initialization!");
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero!");
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotal = items.stream().map(orderItem -> {
            validateItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!price.equals(orderItemsTotal)) {
            throw new OrderDomainException("Total price: " + price.getAmount()
                    + " is not equal to Order items total: " + orderItemsTotal.getAmount() + "!");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount() +
                    " is not valid for product " + orderItem.getProduct().getId().getValue());
        }
    }

    /**
     * This method is responsible for the change of the status of the Order.
     * You can see the state machine at "order-state-transitions.png".
     */
    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not correct state for pay operation");
        }
        orderStatus = OrderStatus.PAYED;
    }

    /**
     * This method is responsible for the change of the status of the Order.
     * You can see the state machine at "order-state-transitions.png".
     */
    public void approve() {
        if (orderStatus != OrderStatus.PAYED) {
            throw new OrderDomainException("Order is not correct state for approve operation");
        }
        orderStatus = OrderStatus.APPROVED;
    }

    /**
     * This method is responsible for the change of the status of the Order.
     * You can see the state machine at "order-state-transitions.png".
     */
    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAYED) {
            throw new OrderDomainException("Order is not correct state for initCancel operation");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    /**
     * This method is responsible for the change of the status of the Order.
     * You can see the state machine at "order-state-transitions.png".
     */
    public void cancel(List<String> failureMessages) {
        if (orderStatus == OrderStatus.PENDING || orderStatus == OrderStatus.CANCELLING) {
            throw new OrderDomainException("Order is not correct state for cancel operation");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (Optional.ofNullable(this.failureMessages).isPresent() && Optional.ofNullable(failureMessages).isPresent()) {
            this.failureMessages.addAll(failureMessages.stream().filter(String::isEmpty).toList());
        }
        ;
        if (Optional.ofNullable(this.failureMessages).isEmpty()) {
            this.failureMessages = failureMessages;
        }
    }
}
