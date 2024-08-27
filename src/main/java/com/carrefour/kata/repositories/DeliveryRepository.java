package com.carrefour.kata.repositories;

import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.enums.DeliveryMethod;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface DeliveryRepository extends ReactiveCrudRepository<Delivery, Long> {

    Flux<Delivery> findByDeliveryMethod(DeliveryMethod deliveryMethod);

    Flux<Delivery> findByCustomerId(Long customerId);
}
