package com.sg.tondeuse.service;

import com.sg.tondeuse.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MowerMoverServiceTest {

    @Autowired
    private MowerMoverService mowerMoverService;

    private Lawn lawn;

    @BeforeEach
    public void setUp() {
        Coordinate bottomCornerCoordinate = Coordinate.builder().coordinateX(0).coordinateY(0).build();
        Coordinate upperCornerCoordinate = Coordinate.builder().coordinateX(5).coordinateY(5).build();
        this.lawn = Lawn.builder()
                .upperRightCorner(upperCornerCoordinate)
                .bottomLeftCorner(bottomCornerCoordinate)
                .build();
    }

    @Test
    public void should_return_same_mower_coordinates_orientation_when_no_movement_founded() throws Exception {

        // given
        List<Movement> movements = new ArrayList<>();
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.WEST)
                .build();
        // when
        Mower mowerAfterMovement = mowerMoverService.moveMower(this.lawn, mower, movements);

        //then
        Assertions.assertEquals(mower.getOrientation(), mowerAfterMovement.getOrientation());
        Assertions.assertEquals(mower.getCoordinate().getCoordinateX(), mowerAfterMovement.getCoordinate().getCoordinateX());
        Assertions.assertEquals(mower.getCoordinate().getCoordinateY(), mowerAfterMovement.getCoordinate().getCoordinateY());
    }

    @Test
    public void should_return_valid_mower_coordinates_orientation_movement_founded() throws Exception {

        // given
        List<Movement> movements = List.of(
                Movement.TO_LEFT,
                Movement.AHEAD,
                Movement.TO_LEFT,
                Movement.AHEAD,
                Movement.TO_LEFT,
                Movement.AHEAD,
                Movement.TO_LEFT,
                Movement.AHEAD,
                Movement.AHEAD
        );
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.NORTH)
                .build();

        // when
        Mower mowerAfterMovement = mowerMoverService.moveMower(this.lawn, mower, movements);

        //then
        Assertions.assertEquals(Orientation.NORTH, mowerAfterMovement.getOrientation());
        Assertions.assertEquals(1, mowerAfterMovement.getCoordinate().getCoordinateX());
        Assertions.assertEquals(3, mowerAfterMovement.getCoordinate().getCoordinateY());
    }

    @Test
    public void should_return_valid_mower_coordinates_orientation_movement_founded_second_test() throws Exception {
        // given
        List<Movement> movements = List.of(
                Movement.AHEAD,
                Movement.AHEAD,
                Movement.TO_RIGHT,
                Movement.AHEAD,
                Movement.AHEAD,
                Movement.TO_RIGHT,
                Movement.AHEAD,
                Movement.TO_RIGHT,
                Movement.TO_RIGHT,
                Movement.AHEAD
        );
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(3).coordinateY(3).build())
                .orientation(Orientation.EAST)
                .build();

        // when
        Mower mowerAfterMovement = mowerMoverService.moveMower(this.lawn, mower, movements);

        //then
        Assertions.assertEquals(Orientation.EAST, mowerAfterMovement.getOrientation());
        Assertions.assertEquals(5, mowerAfterMovement.getCoordinate().getCoordinateX());
        Assertions.assertEquals(1, mowerAfterMovement.getCoordinate().getCoordinateY());
    }

    @Test
    public void should_throw_exception_when_mower_start_outside_lawn() {
        // given
        List<Movement> movements = new ArrayList<>();
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(6).coordinateY(2).build())
                .orientation(Orientation.WEST)
                .build();


        // when
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            mowerMoverService.moveMower(this.lawn, mower, movements);
        });


        // then
        Assertions.assertEquals(exception.getMessage(), "the mower is outside the lawn");
    }
}
