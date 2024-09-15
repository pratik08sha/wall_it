package com.techxplained.springsec.model;

import com.techxplained.springsec.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @NotBlank(message = "Mobile number cannot be blank")
    @Size(max = 20, message = "Mobile number cannot exceed 20 characters")
    @Column(name = "mobile_number", nullable = false, length = 20)
    private String mobileNumber;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 500, message = "Password must be between 8 and 500 characters")
    @Column(name = "pwd", nullable = false, length = 500)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Role role;

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;


}
