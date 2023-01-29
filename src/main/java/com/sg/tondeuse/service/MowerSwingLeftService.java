package com.sg.tondeuse.service;


import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.model.Orientation;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MowerSwingLeftService implements Function<Mower, Orientation> {

    @Override
    public Orientation apply(Mower mower) {
        Orientation orientation = mower.getOrientation();
        switch (mower.getOrientation()) {
            case NORTH -> {
                return Orientation.WEST;
            }
            case EAST -> {
                return Orientation.NORTH;
            }
            case SOUTH -> {
                return Orientation.EAST;
            }
            case WEST -> {
                return Orientation.SOUTH;
            }
        }
        return orientation;
    }
}
