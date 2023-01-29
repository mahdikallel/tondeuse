package com.sg.tondeuse.service;


import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Mower;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MowerMoveAheadService implements Function<Mower, Coordinate> {


    @Override
    public Coordinate apply(Mower mower) {
        switch (mower.getOrientation()) {
            case NORTH -> {
                return Coordinate.builder()
                        .coordinateX(mower.getCoordinate().getCoordinateX())
                        .coordinateY(mower.getCoordinate().getCoordinateY() + 1)
                        .build();
            }
            case SOUTH -> {
                return Coordinate.builder()
                        .coordinateX(mower.getCoordinate().getCoordinateX())
                        .coordinateY(mower.getCoordinate().getCoordinateY() - 1)
                        .build();
            }
            case EAST -> {
                return Coordinate.builder()
                        .coordinateX(mower.getCoordinate().getCoordinateX() + 1)
                        .coordinateY(mower.getCoordinate().getCoordinateY())
                        .build();
            }
            case WEST -> {
                return Coordinate.builder()
                        .coordinateX(mower.getCoordinate().getCoordinateX() - 1)
                        .coordinateY(mower.getCoordinate().getCoordinateY())
                        .build();
            }
            default -> throw new IllegalArgumentException("Orientation not yet supported");
        }
    }
}
