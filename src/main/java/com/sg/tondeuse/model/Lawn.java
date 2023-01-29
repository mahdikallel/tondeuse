package com.sg.tondeuse.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Lawn {
    private final Coordinate upperRightCorner;
    private final Coordinate bottomLeftCorner;
}
