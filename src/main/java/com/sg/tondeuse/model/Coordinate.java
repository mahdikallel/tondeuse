package com.sg.tondeuse.model;


import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
@Getter
@Builder
public class Coordinate {
    private final Integer coordinateX;
    private final Integer coordinateY;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return coordinateX.equals(that.coordinateX) &&
                coordinateY.equals(that.coordinateY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                '}';
    }
}
