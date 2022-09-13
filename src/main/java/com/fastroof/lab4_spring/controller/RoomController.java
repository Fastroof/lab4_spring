package com.fastroof.lab4_spring.controller;

import com.fastroof.lab4_spring.entity.Room;
import com.fastroof.lab4_spring.repository.RoomRepository;
import com.fastroof.lab4_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoomController {
    private final RoomRepository fakeRoomRepository;
    private final UserRepository fakeUserRepository;

    @Autowired
    public RoomController(RoomRepository fakeRoomRepository, UserRepository fakeUserRepository) {
        this.fakeRoomRepository = fakeRoomRepository;
        this.fakeUserRepository = fakeUserRepository;
    }

    @GetMapping("/room/new")
    public String createNewRoom(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        return "thymeleaf/room/new";
    }

    @PostMapping("/room/new")
    public String submitNewRoom(@ModelAttribute Room room) {
        room.setUser(fakeUserRepository.getUsers().get(0));
        fakeRoomRepository.getRooms().add(room);
        return "redirect:../";
    }

    @GetMapping("/room/edit")
    public String editRoom(Model model, @RequestParam Long id) {
        Room room = fakeRoomRepository.findById(id);
        model.addAttribute("room", room);
        return "thymeleaf/room/edit";
    }

    @PostMapping("/room/edit")
    public String submitEditedRoom(@ModelAttribute Room room, @RequestParam Long id) {
        int index = fakeRoomRepository.getRooms().indexOf(fakeRoomRepository.findById(id));
        fakeRoomRepository.getRooms().set(index, room);
        return "redirect:../";
    }

    @GetMapping("/room/delete")
    public String deleteRoom(@RequestParam Long id) {
        fakeRoomRepository.getRooms().remove(fakeRoomRepository.findById(id));
        return "redirect:../";
    }
}
