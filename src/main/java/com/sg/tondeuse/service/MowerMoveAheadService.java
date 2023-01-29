package com.sg.tondeuse.service;


import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.model.Orientation;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MowerMoveAheadService implements Function<Mower, Coordinate> {


    @Override
    public Coordinate apply(Mower mower) {
        if (mower.getOrientation().equals(Orientation.NORTH)) {
            return Coordinate.builder()
                    .coordinateX(mower.getCoordinate().getCoordinateX())
                    .coordinateY(mower.getCoordinate().getCoordinateY() + 1)
                    .build();
        }
        if (mower.getOrientation().equals(Orientation.SOUTH)) {
            return Coordinate.builder()
                    .coordinateX(mower.getCoordinate().getCoordinateX())
                    .coordinateY(mower.getCoordinate().getCoordinateY() - 1)
                    .build();
        }
        if (mower.getOrientation().equals(Orientation.EAST)) {
            return Coordinate.builder()
                    .coordinateX(mower.getCoordinate().getCoordinateX() + 1)
                    .coordinateY(mower.getCoordinate().getCoordinateY())
                    .build();
        }
        if (mower.getOrientation().equals(Orientation.WEST)) {
            return Coordinate.builder()
                    .coordinateX(mower.getCoordinate().getCoordinateX() - 1)
                    .coordinateY(mower.getCoordinate().getCoordinateY())
                    .build();
        }
        return null;
    }
}
