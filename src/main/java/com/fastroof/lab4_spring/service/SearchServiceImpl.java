package com.fastroof.lab4_spring.service;

import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.entity.RoomConfiguration;
import com.fastroof.lab4_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab4_spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RoomRepository fakeRoomRepository;
    @Autowired
    private RoomConfigurationRepository fakeRoomConfigurationRepository;

    @Override
    public List<Room> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price) {
        List<Room> rooms = new ArrayList<>();
        for (RoomConfiguration roomConfiguration :
                fakeRoomConfigurationRepository.findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price)
        ){
            Room room = fakeRoomRepository.findByRoomConfiguration(roomConfiguration);
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public List<Room> findPaginatedByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price, Integer page, Integer size) {
        List<Room> rooms = findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price);
        int startIndex = page * size;
        int endIndex = page * size + size;
        if (startIndex > rooms.size() || endIndex > rooms.size()) {
            startIndex = Math.min(startIndex, rooms.size());
            endIndex = rooms.size();
        }
        return rooms.subList(startIndex, endIndex);
    }
}
