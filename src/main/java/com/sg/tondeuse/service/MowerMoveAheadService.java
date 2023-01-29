package com.sg.tondeuse.service;


import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Mower;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MowerMoveAheadService implements Function<Mower, Coordinate> {


    @Override
    public Coordinate apply(Mower mower) {
        final Integer coordinateX = mower.getCoordinate().getCoordinateX();
        final Integer coordinateY = mower.getCoordinate().getCoordinateY();
        return switch (mower.getOrientation()) {
            case NORTH -> buildCoordinates(coordinateX, coordinateY + 1);
            case SOUTH -> buildCoordinates(coordinateX, coordinateY - 1);
            case EAST -> buildCoordinates(coordinateX + 1, coordinateY);
            case WEST -> buildCoordinates(coordinateX - 1, coordinateY);

        };
    }

    private static Coordinate buildCoordinates(Integer coordinateX, Integer coordinateY) {
        return Coordinate.builder()
                .coordinateX(coordinateX)
                .coordinateY(coordinateY)
                .build();
    }
}
