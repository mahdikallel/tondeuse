package com.sg.tondeuse.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Mower {

    private Coordinate coordinate;
    private Orientation orientation;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mower mower = (Mower) o;
        return coordinate.equals(mower.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }

    @Override
    public String toString() {
        return "Mower{" +
                "coordinate=" + coordinate +
                ", orientation=" + orientation +
                '}';
    }
}
