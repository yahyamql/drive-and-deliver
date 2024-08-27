package com.carrefour.kata.controllers;


import com.carrefour.kata.dtos.DeliveryDto;
import com.carrefour.kata.entities.Customer;
import com.carrefour.kata.services.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@RestController
@RequestMapping ("/api/v1/customers")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    @Operation(
            summary = "Retrieve all customers",
            description = "Fetches a list of all customers from the system."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved customers")
    public Flux<Customer> getAllCustomers() {
        return deliveryService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Retrieve a customer by ID",
            description = "Fetches details of a specific customer identified by their ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the customer"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public Mono<Customer> getCustomerById(@PathVariable long id) {
        return deliveryService.getCustomerById(id);
    }

    @PostMapping("/{idCustomer}/deliveries")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new delivery for a customer",
            description = "Creates a new delivery record for the customer specified by the ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Delivery created successfully"),
            @ApiResponse(responseCode = "400", description = "Delivery id already exists")
    })
    public Mono<Void> createDelivery(@PathVariable long idCustomer, @Valid @RequestBody DeliveryDto deliveryDto) {
        deliveryDto.setIdCustomer(idCustomer);
        return deliveryService.createDelivery(deliveryDto);
    }
}