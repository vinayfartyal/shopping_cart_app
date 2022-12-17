package com.example.OrderService.service;

import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
