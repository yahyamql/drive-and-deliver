package com.carrefour.kata.services.impl;

import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.exceptions.CustomException;
import com.carrefour.kata.repositories.CustomerRepository;
import com.carrefour.kata.repositories.DeliveryRepository;
import com.carrefour.kata.services.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Mono<Void> createDelivery(DeliveryDto deliveryDto) {
        return customerRepository.findById(deliveryDto.getIdCustomer())
                .switchIfEmpty(Mono.error(new CustomException("Customer not found")))
                .flatMap(customer -> {
                    Delivery delivery = new Delivery();
                    delivery.setId(deliveryDto.getId());
                    delivery.setDeliveryMethod(deliveryDto.getDeliveryMethod().toString());
                    delivery.setDeliveryDateTime(deliveryDto.getDeliveryDateTime());
                    delivery.setCustomerId(customer.getId());
                    return deliveryRepository.save(delivery).then();
                })
                .onErrorMap(DuplicateKeyException.class, ex ->
                        new CustomException("Delivery id already exists : " + deliveryDto.getId()));
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> getCustomerById(long idCustomer) {
        return customerRepository.findById(idCustomer)
                .switchIfEmpty(Mono.error(new CustomException("Customer not found !")))
                .flatMap(customer ->
                    deliveryRepository.findByCustomerId(idCustomer)
                        .collectList()
                        .doOnNext(customer::setDeliveries)
                        .thenReturn(customer));
    }
}