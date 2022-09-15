package com.fastroof.lab4_spring.repository;

import com.fastroof.lab4_spring.entity.RoomConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class FakeRoomConfigurationRepository implements RoomConfigurationRepository{
    private final List<RoomConfiguration> roomConfigurations = new ArrayList<>();

    public FakeRoomConfigurationRepository(){
        roomConfigurations.add(new RoomConfiguration(80.0, 2, 580));
        roomConfigurations.add(new RoomConfiguration(50.0, 1, 450));
    }

    public List<RoomConfiguration> findAllByAreaAndBedroomCountAndPrice(Double area, Integer bedroomCount, Integer price) {
        return roomConfigurations.stream().filter(roomConfiguration -> ((area == null) || (roomConfiguration.getArea().equals(area))) &&
        ((bedroomCount == null) || (roomConfiguration.getBedroomCount().equals(bedroomCount))) &&
        ((price == null) || (roomConfiguration.getPrice().equals(price)))
        ).toList();
    }


}
