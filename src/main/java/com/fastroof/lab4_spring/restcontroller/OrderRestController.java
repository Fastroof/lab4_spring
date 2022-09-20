package com.fastroof.lab4_spring.restcontroller;

import com.fastroof.lab4_spring.entity.Order;
import com.fastroof.lab4_spring.repository.OrderRepository;
import com.fastroof.lab4_spring.service.IndexService;
import com.fastroof.lab4_spring.service.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {
    private final IndexService indexService;

    @Autowired
    public OrderRestController(IndexService indexServiceImpl) {
        this.indexService = indexServiceImpl;
    }

    @GetMapping("/orders")
    List<Order> allOrders() {
        return indexService.getAllOrders();
    }

}
