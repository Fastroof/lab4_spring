package com.fastroof.lab4_spring.service;

import com.fastroof.lab4_spring.pojo.UserRegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    Map<String, Object> processRegister(UserRegistrationRequest request);
}
