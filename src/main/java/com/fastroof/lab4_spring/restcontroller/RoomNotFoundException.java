package com.fastroof.lab4_spring.restcontroller;

public class RoomNotFoundException extends RuntimeException{
    RoomNotFoundException(Long id){
        super("Could not find room " + id);
    }
}
