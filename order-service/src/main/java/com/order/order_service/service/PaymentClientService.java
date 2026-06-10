package com.order.order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentClientService {

    private final RestTemplate restTemplate;

    public PaymentClientService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }



    public String callPaymentService(Long orderId){

        String url = "http://PAYMENT-SERVICE/api/payment/process/" + orderId;
        return restTemplate.getForObject(url, String.class);



    }

}
