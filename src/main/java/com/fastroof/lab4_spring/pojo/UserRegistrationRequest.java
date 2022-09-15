package com.fastroof.lab4_spring.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserRegistrationRequest {
    private String email;
    private String password;
    private String fullName;
}