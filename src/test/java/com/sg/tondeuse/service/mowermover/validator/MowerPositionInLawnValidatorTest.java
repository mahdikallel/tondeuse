package com.sg.tondeuse.service.mowermover.validator;

import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Lawn;
import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.model.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MowerPositionInLawnValidatorTest {

    @Autowired
    private MowerPositionInLawnValidator mowerPositionInLawnValidator;

    @Test
    public void should_validate_mower_position() {

        /// given
        Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
        Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
        Lawn lawn = Lawn.builder()
                .upperRightCorner(upperCornerCoordinate)
                .bottomLeftCorner(bottomCornerCoordinate)
                .build();
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(0).coordinateY(2).build())
                .orientation(Orientation.WEST)
                .build();

        // when
        boolean mowerPositionValid = mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn);

        // then
        Assertions.assertTrue(mowerPositionValid);
    }


    @Nested()
    @DisplayName(" Test mower position with upper corner lawn")
    class UpperCornerTest {
        @Test
        public void should_validate_mower_position_when_position_exact_to_upper_corner() {

            /// given
            Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
            Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
            Lawn lawn = Lawn.builder()
                    .upperRightCorner(upperCornerCoordinate)
                    .bottomLeftCorner(bottomCornerCoordinate)
                    .build();
            Mower mower = Mower.builder()
                    .coordinate(Coordinate.builder().coordinateX(5).coordinateY(5).build())
                    .orientation(Orientation.WEST)
                    .build();


            // when
            boolean mowerPositionValid = mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn);

            // then
            Assertions.assertTrue(mowerPositionValid);
        }

        @Test
        public void should_not_validate_mower_position_when_coordinate_x_bigger_than_upper_corner() {

            /// given
            Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
            Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
            Lawn lawn = Lawn.builder()
                    .upperRightCorner(upperCornerCoordinate)
                    .bottomLeftCorner(bottomCornerCoordinate)
                    .build();
            Mower mower = Mower.builder()
                    .coordinate(Coordinate.builder().coordinateX(6).coordinateY(0).build())
                    .orientation(Orientation.WEST)
                    .build();

            // when
            boolean mowerPositionValid = mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn);

            // then
            Assertions.assertFalse(mowerPositionValid);
        }

        @Test
        public void should_not_validate_mower_position_when_coordinate_y_bigger_than_upper_corner() {

            /// given
            Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
            Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
            Lawn lawn = Lawn.builder()
                    .upperRightCorner(upperCornerCoordinate)
                    .bottomLeftCorner(bottomCornerCoordinate)
                    .build();
            Mower mower = Mower.builder()
                    .coordinate(Coordinate.builder().coordinateX(0).coordinateY(6).build())
                    .orientation(Orientation.WEST)
                    .build();
            // when
            boolean mowerPositionValid = mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn);

            // then
            Assertions.assertFalse(mowerPositionValid);
        }
    }


    @Nested()
    @DisplayName(" Test mower position with bottom corner lawn")
    class BottomCornerTest {
        @Test
        public void should_validate_mower_position_when_position_exact_to_bottom_corner() {

            /// given
            Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
            Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
            Lawn lawn = Lawn.builder()
                    .upperRightCorner(upperCornerCoordinate)
                    .bottomLeftCorner(bottomCornerCoordinate)
                    .build();
            Mower mower = Mower.builder()
                    .coordinate(Coordinate.builder().coordinateX(0).coordinateY(0).build())
                    .orientation(Orientation.WEST)
                    .build();

            // when
            boolean mowerPositionValid = mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn);

            // then
            Assertions.assertTrue(mowerPositionValid);
        }

        @Test
        public void should_not_validate_mower_position_when_coordinate_x_less_than_bottom_corner() {

            /// given
            Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
            Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
            Lawn lawn = Lawn.builder()
                    .upperRightCorner(upperCornerCoordinate)
                    .bottomLeftCorner(bottomCornerCoordinate)
                    .build();
            Mower mower = Mower.builder()
                    .coordinate(Coordinate.builder().coordinateX(-1).coordinateY(0).build())
                    .orientation(Orientation.WEST)
                    .build();

            // when
            boolean mowerPositionValid = mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn);

            // then
            Assertions.assertFalse(mowerPositionValid);
        }


        @Test
        public void should_not_validate_mower_position_when_coordinate_y_less_than_bottom_corner() {

            /// given
            Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
            Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
            Lawn lawn = Lawn.builder()
                    .upperRightCorner(upperCornerCoordinate)
                    .bottomLeftCorner(bottomCornerCoordinate)
                    .build();
            Mower mower = Mower.builder()
                    .coordinate(Coordinate.builder().coordinateX(0).coordinateY(-1).build())
                    .orientation(Orientation.WEST)
                    .build();

            // when
            boolean mowerPositionValid = mowerPositionInLawnValidator.isValidPosition(mower.getCoordinate(), lawn);

            // then
            Assertions.assertFalse(mowerPositionValid);
        }

    }


}
