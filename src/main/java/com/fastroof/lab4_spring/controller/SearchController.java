package com.fastroof.lab4_spring.controller;

import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.entity.RoomConfiguration;
import com.fastroof.lab4_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab4_spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    private final RoomRepository fakeRoomRepository;

    private final RoomConfigurationRepository fakeRoomConfigurationRepository;
    @Autowired
    public SearchController(RoomRepository fakeRoomRepository, RoomConfigurationRepository fakeRoomConfigurationRepository) {
        this.fakeRoomRepository = fakeRoomRepository;
        this.fakeRoomConfigurationRepository = fakeRoomConfigurationRepository;
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(@RequestParam(required = false) Double area , @RequestParam(required = false) Integer bedroomCount, @RequestParam(required = false) Integer price) {
        List<Room> rooms = new ArrayList<>();
        for (RoomConfiguration roomConfiguration :
                fakeRoomConfigurationRepository.findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price)
        ){
            rooms.add(fakeRoomRepository.findByRoomConfiguration(roomConfiguration));
        }
        return rooms.toString();
    }
}
