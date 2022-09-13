package com.fastroof.lab4_spring.controller;

import com.fastroof.lab4_spring.entity.User;
import com.fastroof.lab4_spring.pojo.UserRegistrationRequest;
import com.fastroof.lab4_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "thymeleaf/login";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(ModelMap model) {
        model.addAttribute("user", new UserRegistrationRequest());
        return "thymeleaf/registration";
    }

    @PostMapping("/registration")
    public String processRegister(ModelMap model, UserRegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            model.addAttribute("message_number", 1);
            model.addAttribute("msg", String.format("Користувач з email %s вже зареєстрований.", request.getEmail()));
            model.addAttribute("link", "/registration");
            model.addAttribute("text", "Натисніть, щоб спробувати ще раз ➜");
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            // Коли буде додана JDBC, id будуть генеруватися автоматично
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(encodedPassword);
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());

//            userRepository.save(user);
            userRepository.getUsers().add(user);
            model.addAttribute("message-number", 0);
            model.addAttribute("msg", "Ви успішно зареєстровані!");
            model.addAttribute("link", "/login");
            model.addAttribute("text", "Натисніть, щоб продовжити ➜");
        }
        return "info";
    }
}