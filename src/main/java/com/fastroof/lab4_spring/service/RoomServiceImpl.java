package com.fastroof.lab4_spring.service;

import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.entity.RoomConfiguration;
import com.fastroof.lab4_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab4_spring.repository.RoomDescriptionRepository;
import com.fastroof.lab4_spring.repository.RoomRepository;
import com.fastroof.lab4_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private UserRepository fakeUserRepository;
    @Autowired
    private RoomRepository fakeRoomRepository;
    @Autowired
    private RoomConfigurationRepository fakeRoomConfigurationRepository;
    @Autowired
    private RoomDescriptionRepository fakeRoomDescriptionRepository;

    @Override
    public boolean addRoom(Room room) {
        fakeRoomDescriptionRepository.getRoomDescriptions().add(room.getDescription());
        fakeRoomConfigurationRepository.getRoomConfigurations().add(room.getConfiguration());
        room.setUser(fakeUserRepository.getUsers().get(0));
        room.setId(fakeRoomRepository.getRooms().get(fakeRoomRepository.getRooms().size() - 1).getId() + 1);
        room.getDescription().setCreationDate(new Date());
        return fakeRoomRepository.getRooms().add(room);
    }

    @Override
    public Room getRoom(Long id) {
        return fakeRoomRepository.findById(id);
    }

    @Override
    public Room updateRoom(Room oldRoom, Room updatedRoom) {
        int index = fakeRoomRepository.getRooms().indexOf(oldRoom);
        fakeRoomConfigurationRepository.getRoomConfigurations().set(index, updatedRoom.getConfiguration());
        fakeRoomDescriptionRepository.getRoomDescriptions().set(index, updatedRoom.getDescription());
        updatedRoom.setUser(oldRoom.getUser());
        updatedRoom.getDescription().setCreationDate(oldRoom.getDescription().getCreationDate());
        return fakeRoomRepository.getRooms().set(index, updatedRoom);
    }

    @Override
    public boolean deleteRoom(Room room){
        fakeRoomConfigurationRepository.getRoomConfigurations().remove(room.getConfiguration());
        fakeRoomDescriptionRepository.getRoomDescriptions().remove(room.getDescription());
        return fakeRoomRepository.getRooms().remove(room);
    }
}
