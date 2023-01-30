package com.sg.tondeuse.service.mowermover;


import com.sg.tondeuse.exception.MowerOutsideLawnException;
import com.sg.tondeuse.model.*;
import com.sg.tondeuse.service.mowermover.validator.MowerPositionInLawnValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MowerMoverService {
    private final MowerMoveAheadService mowerAheadMoverService;
    private final MowerSwingLeftService mowerSwingLeftService;
    private final MowerSwingRightService mowerSwingRightService;
    private final MowerPositionInLawnValidator mowerPositionInLawnValidator;

    public Mower moveMower(Lawn lawn, Mower mower, List<Movement> movementList) {
        if (!mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn)) {
            throw new MowerOutsideLawnException("the mower is outside the lawn");
        }
        return movementList
                .stream()
                .reduce(mower,
                        (mowerMovement, movement) -> this.moveMowerSingleStep(mowerMovement, movement, lawn),
                        ((mower1, mower2) -> mower2)
                );
    }

    public Mower moveMowerSingleStep(Mower mower, Movement movement, Lawn lawn) {
        return switch (movement) {
            case AHEAD -> moveAhead(mower, lawn);
            case TO_LEFT -> buildMowerWithNewOrientation(mower, mowerSwingLeftService.apply(mower));
            case TO_RIGHT -> buildMowerWithNewOrientation(mower, mowerSwingRightService.apply(mower));
        };
    }

    private Mower buildMowerWithNewOrientation(Mower mower, Orientation newOrientation) {
        return Mower.builder()
                .orientation(newOrientation)
                .coordinate(mower.getCoordinate())
                .build();
    }

    private Mower moveAhead(Mower mower, Lawn lawn) {
        Coordinate newCoordinates = mowerAheadMoverService.apply(mower);
        if (mowerPositionInLawnValidator.isValidPosition(newCoordinates, lawn)) {
            return Mower.builder()
                    .orientation(mower.getOrientation())
                    .coordinate(newCoordinates)
                    .build();
        }
        return mower;
    }
}
