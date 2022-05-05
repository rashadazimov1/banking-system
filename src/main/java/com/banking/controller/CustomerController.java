package com.banking.controller;

import com.banking.dto.CreateCustomerRequest;
import com.banking.dto.CustomerDto;
import com.banking.dto.UpdateCustomerRequest;
import com.banking.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
 private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return  ResponseEntity.ok(customerService.createCustomer(createCustomerRequest));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerId(@PathVariable  String id){
        return ResponseEntity.ok(customerService.getCustomerDtoById(id));

    }

    @PutMapping ("/{id}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id,
                                               @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return ResponseEntity.ok(customerService.updateCustomer(id,updateCustomerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
