package com.fastroof.lab4_spring.entity;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    private Long id;
    @NotNull
    @Valid
    private RoomConfiguration configuration;
    @NotNull
    @Valid
    private RoomDescription description;
    private User user;
}
