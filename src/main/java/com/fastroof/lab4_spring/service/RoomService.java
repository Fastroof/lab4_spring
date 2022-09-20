package com.fastroof.lab4_spring.service;

import com.fastroof.lab4_spring.entity.Room;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    boolean addRoom(Room room);
    Room getRoom(Long id);
    Room updateRoom(Room oldRoom, Room updatedRoom);
    boolean deleteRoom(Room room);
}
