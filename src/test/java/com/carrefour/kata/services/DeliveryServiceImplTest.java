package com.carrefour.kata.services;

import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.entities.Delivery;
import com.carrefour.kata.enums.DeliveryMethod;
import com.carrefour.kata.exceptions.CustomException;
import com.carrefour.kata.repositories.CustomerRepository;
import com.carrefour.kata.repositories.DeliveryRepository;
import com.carrefour.kata.services.impl.DeliveryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    private Customer customer;
    private DeliveryDto deliveryDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        deliveryDto = new DeliveryDto();
        deliveryDto.setIdCustomer(1L);
        deliveryDto.setId(1L);
        deliveryDto.setDeliveryMethod(DeliveryMethod.DELIVERY_ASAP);
    }

    @Test
    void createDelivery_whenCutomerNotFound_ReturnException() {
        when(customerRepository.findById(anyLong())).thenReturn(Mono.empty());
        Mono<Void> result = deliveryService.createDelivery(deliveryDto);
        Assertions.assertThrows(CustomException.class, result::block);
    }

    @Test
    void createDelivery_whenCutomerIdAlreadyExists_ReturnException() {
        when(customerRepository.findById(anyLong())).thenReturn(Mono.just(customer));
        when(deliveryRepository.save(any(Delivery.class))).thenThrow(new DuplicateKeyException("Duplicate key"));

        Mono<Void> result = deliveryService.createDelivery(deliveryDto);
        Assertions.assertThrows(CustomException.class, result::block);
    }
}
