package com.sg.tondeuse.service;


import com.sg.tondeuse.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MowerMoverService {

    private final MowerMoveAheadService mowerAheadMoverService;
    private final MowerSwingLeftService mowerSwingLeftService;
    private final MowerSwingRightService mowerSwingRightService;

    public MowerMoverService(MowerMoveAheadService mowerAheadMoverService, MowerSwingLeftService mowerSwingLeftService, MowerSwingRightService mowerSwingRightService) {
        this.mowerAheadMoverService = mowerAheadMoverService;
        this.mowerSwingLeftService = mowerSwingLeftService;
        this.mowerSwingRightService = mowerSwingRightService;
    }


    public Mower moveMower(Lawn lawn, Mower mower, List<Movement> movementList) throws Exception {
        if (
                mower.getCoordinate().getCoordinateX() < lawn.getBottomLeftCorner().getCoordinateX() ||
                        mower.getCoordinate().getCoordinateY() < lawn.getBottomLeftCorner().getCoordinateY() ||
                        mower.getCoordinate().getCoordinateX() > lawn.getUpperRightCorner().getCoordinateX() ||
                        mower.getCoordinate().getCoordinateY() > lawn.getUpperRightCorner().getCoordinateY()
        ) {
            throw new Exception("the mower is outside the lawn");
        }
        return movementList
                .stream()
                .reduce(mower, this::moveMowerSingleStep, ((mower1, mower2) -> mower2));
    }

    public Mower moveMowerSingleStep(Mower mower, Movement movement) {
        return switch (movement) {
            case AHEAD -> Mower.builder()
                    .orientation(mower.getOrientation())
                    .coordinate(mowerAheadMoverService.apply(mower))
                    .build();
            case TO_LEFT -> Mower.builder()
                    .orientation(mowerSwingLeftService.apply(mower))
                    .coordinate(mower.getCoordinate())
                    .build();
            case TO_RIGHT -> Mower.builder()
                    .orientation(mowerSwingRightService.apply(mower))
                    .coordinate(mower.getCoordinate())
                    .build();
        };
    }
}
