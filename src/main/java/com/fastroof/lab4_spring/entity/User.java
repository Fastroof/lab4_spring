package com.fastroof.lab4_spring.entity;

import com.fastroof.lab4_spring.enums.Provider;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private Provider provider;
}
