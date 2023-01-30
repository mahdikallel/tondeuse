package com.sg.tondeuse.service.mowermover.validator;


import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Lawn;
import org.springframework.stereotype.Service;

@Service
public class MowerPositionInLawnValidator {
    public boolean isValidPosition(Coordinate mowerCoordinates, Lawn lawn) {
        return mowerCoordinates.getCoordinateX() >= lawn.getBottomLeftCorner().getCoordinateX() &&
                mowerCoordinates.getCoordinateY() >= lawn.getBottomLeftCorner().getCoordinateY() &&
                mowerCoordinates.getCoordinateX() <= lawn.getUpperRightCorner().getCoordinateX() &&
                mowerCoordinates.getCoordinateY() <= lawn.getUpperRightCorner().getCoordinateY();
    }
}
