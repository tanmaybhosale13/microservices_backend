package com.order.order_service.controller;

import com.order.order_service.dto.OrderRequest;
import com.order.order_service.entity.Order;
import com.order.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequest orderRequest){

      return  orderService.createOrder(orderRequest);

    }



    @GetMapping("/test")
    public  String test(){

        return "Order service is running";
    }



    @PostMapping("/{orderId}")
    public String placeOrder(@PathVariable Long orderId) {
        return orderService.placeOrder(orderId);
    }



}
