package com.sg.tondeuse.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Coordinate {
    private final Integer coordinateX;
    private final Integer coordinateY;
}
