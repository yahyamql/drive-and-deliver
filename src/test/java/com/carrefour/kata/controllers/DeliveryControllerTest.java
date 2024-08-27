package com.carrefour.kata.controllers;

import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.enums.DeliveryMethod;
import com.carrefour.kata.services.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(MockitoExtension.class)
class DeliveryControllerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(deliveryController).build();
    }

    @Test
    void getAllCustomers_when2CustomersExist_returnSize2() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        when(deliveryService.getAllCustomers()).thenReturn(Flux.just(customer1, customer2));

        webTestClient.get().uri("/api/v1/customers")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Customer.class)
                .hasSize(2);
    }

    @Test
    void getCustomerById_whenCustomerExist_return200() {
        Customer customer = new Customer();
        when(deliveryService.getCustomerById(anyLong())).thenReturn(Mono.just(customer));

        webTestClient.get().uri("/api/v1/customers/{id}", 1L)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class);
    }

    @Test
    void createDelivery_whenPost_return201() {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setIdCustomer(1L);
        deliveryDto.setId(2L);
        deliveryDto.setDeliveryMethod(DeliveryMethod.DELIVERY);

        when(deliveryService.createDelivery(any(DeliveryDto.class))).thenReturn(Mono.empty());

        webTestClient.post().uri("/api/v1/customers/{idCustomer}/deliveries", 1L)
                .contentType(APPLICATION_JSON)
                .bodyValue(deliveryDto)
                .exchange()
                .expectStatus().isCreated();
    }
}