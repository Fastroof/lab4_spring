package com.fastroof.lab4_spring.restcontroller;

import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.entity.RoomConfiguration;
import com.fastroof.lab4_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab4_spring.repository.RoomRepository;
import com.fastroof.lab4_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RoomRestController {
    private final RoomRepository fakeRoomRepository;
    private final UserRepository fakeUserRepository;
    private final RoomConfigurationRepository fakeRoomConfigurationRepository;

    @Autowired
    public RoomRestController(RoomRepository fakeRoomRepository, UserRepository fakeUserRepository, RoomConfigurationRepository fakeRoomConfigurationRepository) {
        this.fakeRoomRepository = fakeRoomRepository;
        this.fakeUserRepository = fakeUserRepository;
        this.fakeRoomConfigurationRepository = fakeRoomConfigurationRepository;
    }

    @GetMapping("/api/rooms")
    List<Room> getRooms(@RequestParam(required = false) Double area , @RequestParam(required = false) Integer bedroomCount, @RequestParam(required = false) Integer price) {
        List<Room> rooms = new ArrayList<>();
        for (RoomConfiguration roomConfiguration :
                fakeRoomConfigurationRepository.findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price)
        ){
            Room room = fakeRoomRepository.findByRoomConfiguration(roomConfiguration);
            if (room != null){
                rooms.add(room);
            }
        }
        return rooms;
    }

    @PostMapping("/api/rooms")
    Room newRoom(Room newRoom) {
        fakeRoomConfigurationRepository.getRoomConfigurations().add(newRoom.getConfiguration());
        newRoom.setUser(fakeUserRepository.getUsers().get(0));
        newRoom.setId((long) fakeRoomRepository.getRooms().size());
        newRoom.getDescription().setCreationDate(new Date());
        fakeRoomRepository.getRooms().add(newRoom);
        return newRoom;
    }

    @GetMapping("/api/rooms/{id}")
    Room getRoom(@PathVariable Long id) {
        return fakeRoomRepository.findById(id);
    }

    @PutMapping("/api/rooms/{id}")
    Room editRoom(@RequestBody Room editedRoom, @PathVariable Long id) {
        int index = fakeRoomRepository.getRooms().indexOf(fakeRoomRepository.findById(id));
        fakeRoomRepository.getRooms().set(index, editedRoom);
        return editedRoom;
    }

    @DeleteMapping("/api/rooms/{id}")
    boolean deleteRoom(@PathVariable Long id) {
        return fakeRoomRepository.getRooms().remove(fakeRoomRepository.findById(id));
    }

}
