package com.banking.dto;


import com.banking.model.Currency;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AccountDto extends BaseAccountRequest {

    private String id;
    private String customerId;
    private Double balance;
    private Currency currency;
}