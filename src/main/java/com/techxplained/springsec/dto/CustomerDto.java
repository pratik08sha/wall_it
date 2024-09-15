package com.techxplained.springsec.dto;

import com.techxplained.springsec.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data

public class CustomerDto {

    private int customerId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank(message = "Mobile number cannot be blank")
    @Size(max = 20, message = "Mobile number cannot exceed 20 characters")
    private String mobileNumber;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 500, message = "Password must be between 8 and 500 characters")
    private String password;

    @NotNull(message = "Role cannot be null")
    private Role role;

    private Date createDate;
    private Date modifiedDate;

}
