package com.techxplained.springsec.service.impl;

import com.techxplained.springsec.dto.CustomerDto;
import com.techxplained.springsec.exceptions.ApiException;
import com.techxplained.springsec.model.Customer;
import com.techxplained.springsec.repository.CustomerRepository;
import com.techxplained.springsec.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class WallitUserDetailsService implements UserDetailsService, CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public WallitUserDetailsService(CustomerRepository customerRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User details not found for user : "+ username));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole().toString()));
        return modelMapper.map(customer, User.class);
    }

    @Override
    public CustomerDto registerUser(CustomerDto customerDto) {
        Optional<Customer> checkCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if (!Objects.isNull(checkCustomer))
            throw new ApiException(HttpStatus.BAD_REQUEST, "User with email id " + customerDto.getEmail() + " already exists");
        Customer customer = modelMapper.map(customerDto, Customer.class);
        String hashPwd = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(hashPwd);
        Customer newCustomer = customerRepository.save(customer);
        if (newCustomer.getCustomerId() > 0)
            return modelMapper.map(newCustomer, CustomerDto.class);
        throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while registering user");
    }
}
