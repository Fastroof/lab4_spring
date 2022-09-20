package com.fastroof.lab4_spring.controller;

import com.fastroof.lab4_spring.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String showIndexPage(ModelMap model) {
        model.addAttribute("rooms", indexService.getAllRooms());
        return "index";
    }

    @GetMapping("/orders")
    public String showOrdersPage(ModelMap model) {
        model.addAttribute("orders", indexService.getAllOrders());
        return "thymeleaf/orders";
    }
}
