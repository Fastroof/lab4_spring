package com.fastroof.lab4_spring.entity;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomConfiguration {
    @NotNull
    @Min(1)
    private Double area;
    @NotNull
    @Min(1)
    private Integer bedroomCount;
    @NotNull
    @Min(1)
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
