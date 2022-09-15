package com.fastroof.lab4_spring.repository;

import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.entity.RoomConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter

public class FakeRoomRepository implements RoomRepository {
    private final List<Room> rooms = new ArrayList<>();

    private UserRepository fakeUserRepository;
    private RoomConfigurationRepository fakeRoomConfigurationRepository;
    private RoomDescriptionRepository fakeRoomDescriptionRepository;

    @Autowired
    public void setFakeUserRepository(UserRepository fakeUserRepository) {
        this.fakeUserRepository = fakeUserRepository;
    }

    @Autowired
    public void setFakeRoomConfigurationRepository(RoomConfigurationRepository fakeRoomConfigurationRepository) {
        this.fakeRoomConfigurationRepository = fakeRoomConfigurationRepository;
    }

    @Autowired
    public void setFakeRoomDescriptionRepository(RoomDescriptionRepository fakeRoomDescriptionRepository) {
        this.fakeRoomDescriptionRepository = fakeRoomDescriptionRepository;
    }

    public FakeRoomRepository() {
        setFakeUserRepository(new FakeUserRepository());
        setFakeRoomConfigurationRepository(new FakeRoomConfigurationRepository());
        setFakeRoomDescriptionRepository(new FakeRoomDescriptionRepository());
        rooms.add(new Room(0L, fakeRoomConfigurationRepository.getRoomConfigurations().get(0), fakeRoomDescriptionRepository.getRoomDescriptions().get(0), fakeUserRepository.getUsers().get(0)));
        rooms.add(new Room(1L, fakeRoomConfigurationRepository.getRoomConfigurations().get(1), fakeRoomDescriptionRepository.getRoomDescriptions().get(1), fakeUserRepository.getUsers().get(1)));
    }

    @Override
    public Room findByRoomConfiguration(RoomConfiguration roomConfiguration) {
        return rooms.stream().filter(room -> room.getConfiguration().equals(roomConfiguration)).findAny().orElse(null);
    }

    @Override
    public Room findById(Long id) {
        return rooms.stream().filter(room -> room.getId().equals(id)).findAny().orElse(null);
    }
}
