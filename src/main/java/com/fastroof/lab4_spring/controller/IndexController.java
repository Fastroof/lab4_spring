package com.fastroof.lab4_spring.controller;

import com.fastroof.lab4_spring.repository.OrderRepository;
import com.fastroof.lab4_spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final RoomRepository fakeRoomRepository;

    private final OrderRepository fakeOrderRepository;

    @Autowired
    public IndexController(RoomRepository fakeRoomRepository, OrderRepository fakeOrderRepository) {
        this.fakeRoomRepository = fakeRoomRepository;
        this.fakeOrderRepository = fakeOrderRepository;
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("rooms", fakeRoomRepository.getRooms());
        return "index";
    }

    @GetMapping("/orders")
    public String showOrdersPage(Model model) {
        model.addAttribute("orders", fakeOrderRepository.getOrders());
        return "thymeleaf/orders";
    }
}
