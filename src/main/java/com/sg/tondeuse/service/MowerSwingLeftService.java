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
        if (mower.getOrientation().equals(Orientation.NORTH)) {
            return Orientation.WEST;
        }
        if (mower.getOrientation().equals(Orientation.EAST)) {
            return Orientation.NORTH;
        }
        if (mower.getOrientation().equals(Orientation.SOUTH)) {
            return Orientation.EAST;
        }
        if (mower.getOrientation().equals(Orientation.WEST)) {
            return Orientation.SOUTH;
        }
        return orientation;
    }
}
