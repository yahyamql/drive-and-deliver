package com.carrefour.kata.dtos;

import com.carrefour.kata.enums.DeliveryMethod;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryDto {

    @NotNull
    private Long id;

    @Future(message = "Date must be in the future !")
    private LocalDateTime deliveryDateTime;

    private Long idCustomer;

    @NotNull
    private DeliveryMethod deliveryMethod;
}
