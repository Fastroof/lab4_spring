package com.fastroof.lab4_spring.service;

import com.fastroof.lab4_spring.entity.Order;
import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.repository.OrderRepository;
import com.fastroof.lab4_spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private RoomRepository fakeRoomRepository;

    @Autowired
    private OrderRepository fakeOrderRepository;

    public List<Room> getAllRooms() {
        return fakeRoomRepository.getRooms();
    }

    public List<Order> getAllOrders() {
        return fakeOrderRepository.getOrders();
    }
}
