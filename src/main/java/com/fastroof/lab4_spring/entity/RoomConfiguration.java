package com.fastroof.lab4_spring.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomConfiguration {
    private Double area;
    private Integer bedroomCount;
    private Integer price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomConfiguration that = (RoomConfiguration) o;
        return Objects.equals(area, that.area) && Objects.equals(bedroomCount, that.bedroomCount) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, bedroomCount, price);
    }
}
