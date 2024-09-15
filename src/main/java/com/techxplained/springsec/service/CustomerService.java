package com.techxplained.springsec.service;

import com.techxplained.springsec.dto.CustomerDto;

public interface CustomerService {

    CustomerDto registerUser(CustomerDto customerDto);
}
