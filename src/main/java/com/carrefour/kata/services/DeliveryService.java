package com.carrefour.kata.services;

import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.exceptions.CustomException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeliveryService {
//    Flux<Delivery> getAvailableTimeSlots(DeliveryMethod deliveryMethod);

    Mono<Void> createDelivery(DeliveryDto deliveryDto) throws CustomException;

    Flux<Customer> getAllCustomers();

    Mono<Customer> getCustomerById(long id);


}
