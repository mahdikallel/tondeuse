package com.sg.tondeuse.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Lawn {
    private final Coordinate upperRightCorner;
    private final Coordinate bottomLeftCorner;
}
