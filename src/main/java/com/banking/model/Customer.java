package com.banking.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Customer {

    @Id
    private String id;
    private String name;
    private Integer dateOfBirth;
    private City city;
    private String address;
}