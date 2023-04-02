package com.kerimfettahoglu.food.ordering.system.domain.entity;

import com.kerimfettahoglu.food.ordering.system.domain.valueobject.Money;
import com.kerimfettahoglu.food.ordering.system.domain.valueobject.OrderId;
import com.kerimfettahoglu.food.ordering.system.domain.valueobject.OrderItemId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }
}
