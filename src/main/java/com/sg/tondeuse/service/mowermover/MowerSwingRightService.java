package com.sg.tondeuse.service.mowermover;


import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.model.Orientation;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MowerSwingRightService implements Function<Mower, Orientation> {
    @Override
    public Orientation apply(Mower mower) {
        return switch (mower.getOrientation()) {
            case NORTH -> Orientation.EAST;
            case EAST -> Orientation.SOUTH;
            case SOUTH -> Orientation.WEST;
            case WEST -> Orientation.NORTH;
        };
    }
}
