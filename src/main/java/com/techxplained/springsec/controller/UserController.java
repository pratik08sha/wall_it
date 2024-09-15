package com.techxplained.springsec.controller;

import com.techxplained.springsec.dto.CustomerDto;
import com.techxplained.springsec.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    CustomerService customerService;

    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }
    

    @GetMapping("register")
    public ResponseEntity<CustomerDto> registerUser(@RequestBody CustomerDto customer){
            CustomerDto customerDto = customerService.registerUser(customer);
            return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }
}
