package com.order.order_service.service;

import com.order.order_service.client.PaymentClient;
import com.order.order_service.dto.OrderRequest;
import com.order.order_service.dto.PaymentRequest;
import com.order.order_service.dto.PaymentResponse;
import com.order.order_service.entity.Order;
import com.order.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class OrderService {




    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    private final PaymentClientService paymentClientService;

    public OrderService(OrderRepository orderRepository, PaymentClient paymentClient,PaymentClientService paymentClientService) {

        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;
        this.paymentClientService=paymentClientService;
    }



    public Order createOrder(OrderRequest orderRequest){

        Order order=new Order();
        order.setProductName(orderRequest.getProductName());
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(orderRequest.getPrice());
        order.setStatus("PENDING");


         order=orderRepository.save(order);


        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(orderRequest.getPrice());

        PaymentResponse paymentResponse =
                paymentClient.processPayment(paymentRequest);

        if ("SUCCESS".equals(paymentResponse.getStatus())) {
            order.setStatus("CONFIRMED");
        } else {
            order.setStatus("FAILED");
        }

        return orderRepository.save(order);



    }


    @CircuitBreaker(name = "paymentService",fallbackMethod = "paymentFallBack")
    public String placeOrder(Long orderId){

        // call payment service methodx
        String paymentResponse  =paymentClientService.callPaymentService(orderId);


        return  " ORDER CREATED "+paymentResponse;


    }



    // FallBack method must match signature
    public String paymentFallBack(Long orderId,Throwable ex){

        return "ORDER CREATED BUT PAYMENT SERVICE IS DOWN. ORDER SAVED IN PENDING STATE. OrderId: " + orderId;

    }







}
