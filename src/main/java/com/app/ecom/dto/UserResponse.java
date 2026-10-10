package com.app.ecom.dto;

import com.app.ecom.model.Address;
import com.app.ecom.model.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    @JsonProperty("firstname")
    private String firstName;
    private String lastname;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO address;
}
