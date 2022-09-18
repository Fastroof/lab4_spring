package com.fastroof.lab4_spring.restcontroller;

import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.entity.RoomConfiguration;
import com.fastroof.lab4_spring.repository.RoomConfigurationRepository;
import com.fastroof.lab4_spring.repository.RoomDescriptionRepository;
import com.fastroof.lab4_spring.repository.RoomRepository;
import com.fastroof.lab4_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class RoomRestController {
    private final RoomRepository fakeRoomRepository;
    private final RoomConfigurationRepository fakeRoomConfigurationRepository;
    private final RoomDescriptionRepository fakeRoomDescriptionRepository;
    private final UserRepository fakeUserRepository;

    @Autowired
    public RoomRestController(RoomRepository fakeRoomRepository, RoomConfigurationRepository fakeRoomConfigurationRepository, RoomDescriptionRepository fakeRoomDescriptionRepository, UserRepository fakeUserRepository) {
        this.fakeRoomRepository = fakeRoomRepository;
        this.fakeRoomConfigurationRepository = fakeRoomConfigurationRepository;
        this.fakeRoomDescriptionRepository = fakeRoomDescriptionRepository;
        this.fakeUserRepository = fakeUserRepository;
    }

    @GetMapping("/rooms")
    List<Room> getRooms(@RequestParam(required = false) Double area ,
                        @RequestParam(required = false) Integer bedroomCount,
                        @RequestParam(required = false) Integer price,
                        @Valid @Min(0) @RequestParam(required = false, defaultValue = "0")  Integer page,
                        @Valid @Min(1) @Max(5) @RequestParam(required = false, defaultValue = "3")  Integer size
    ) {
        List<Room> rooms = new ArrayList<>();
        for (RoomConfiguration roomConfiguration :
                fakeRoomConfigurationRepository.findAllByAreaAndBedroomCountAndPrice(area, bedroomCount, price)
        ){
            Room room = fakeRoomRepository.findByRoomConfiguration(roomConfiguration);
            rooms.add(room);
        }
        int startIndex = page * size;
        int endIndex = page * size + size;
        if (startIndex > rooms.size() || endIndex > rooms.size()) {
            startIndex = Math.min(startIndex, rooms.size());
            endIndex = rooms.size();
        }
        return rooms.subList(startIndex, endIndex);
    }

    @PostMapping("/rooms")
    Room newRoom(@Valid Room newRoom) {
        fakeRoomDescriptionRepository.getRoomDescriptions().add(newRoom.getDescription());
        fakeRoomConfigurationRepository.getRoomConfigurations().add(newRoom.getConfiguration());
        newRoom.setUser(fakeUserRepository.getUsers().get(0));
        newRoom.setId((long) fakeRoomRepository.getRooms().size());
        newRoom.getDescription().setCreationDate(new Date());
        fakeRoomRepository.getRooms().add(newRoom);
        return newRoom;
    }

    @GetMapping("/rooms/{id}")
    Room getRoom(@PathVariable Long id) {
        Room room = fakeRoomRepository.findById(id);
        if (room == null) {
            throw new RoomNotFoundException(id);
        }
        return room;
    }

    @PutMapping("/rooms/{id}")
    Room editRoom(@Valid Room editedRoom, @PathVariable Long id) {
        Room oldRoom = fakeRoomRepository.findById(id);
        if (oldRoom == null) {
            throw new RoomNotFoundException(id);
        }
        int index = fakeRoomRepository.getRooms().indexOf(oldRoom);
        fakeRoomConfigurationRepository.getRoomConfigurations().set(index, editedRoom.getConfiguration());
        fakeRoomDescriptionRepository.getRoomDescriptions().set(index, editedRoom.getDescription());
        editedRoom.setUser(oldRoom.getUser());
        editedRoom.getDescription().setCreationDate(oldRoom.getDescription().getCreationDate());
        fakeRoomRepository.getRooms().set(index, editedRoom);
        return editedRoom;
    }

    @DeleteMapping("/rooms/{id}")
    Room deleteRoom(@PathVariable Long id) {
        Room room = fakeRoomRepository.findById(id);
        if (room == null) {
            throw new RoomNotFoundException(id);
        }
        fakeRoomConfigurationRepository.getRoomConfigurations().remove(room.getConfiguration());
        fakeRoomDescriptionRepository.getRoomDescriptions().remove(room.getDescription());
        fakeRoomRepository.getRooms().remove(room);
        return room;
    }

}
