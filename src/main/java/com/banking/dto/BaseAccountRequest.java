package com.banking.dto;

import com.banking.model.City;
import com.banking.model.Currency;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BaseAccountRequest {

    @NotBlank(message = "Customer id must not be null")

    private String customerId;


    @NotNull
    @Min(0)
    private Double balance;

    @NotNull
    private Currency currency;

    @NotNull
    private City city;
}
