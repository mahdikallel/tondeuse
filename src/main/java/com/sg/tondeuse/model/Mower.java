package com.sg.tondeuse.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Mower {
    private Coordinate coordinate;
    private Orientation orientation;
}
