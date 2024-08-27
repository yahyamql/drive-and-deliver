package com.carrefour.kata.repositories;

import com.carrefour.kata.entities.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}