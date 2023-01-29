package com.sg.tondeuse.service;

import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.model.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MowerMoveAheadServiceTest {

    @Autowired
    private MowerMoveAheadService mowerMoveAheadService;


    @Test
    public void should_increment_y_coordinate_when_moving_mower_ahead_and_mower_direction_is_north() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.NORTH)
                .build();

        // When
        Coordinate newCoordinates = this.mowerMoveAheadService.apply(mower);

        // Then
        Assertions.assertEquals(1, newCoordinates.getCoordinateX());
        Assertions.assertEquals(3, newCoordinates.getCoordinateY());
    }

    @Test
    public void should_decrement_y_coordinate_when_moving_mower_ahead_and_mower_direction_is_south() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.SOUTH)
                .build();


        // When
        Coordinate coordinate = this.mowerMoveAheadService.apply(mower);

        // Then
        Assertions.assertEquals(1, coordinate.getCoordinateX());
        Assertions.assertEquals(1, coordinate.getCoordinateY());
    }

    @Test
    public void should_increment_x_coordinate_when_moving_mower_ahead_and_mower_direction_is_east() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.EAST)
                .build();

        // When
        Coordinate coordinate = this.mowerMoveAheadService.apply(mower);

        // Then
        Assertions.assertEquals(2, coordinate.getCoordinateX());
        Assertions.assertEquals(2, coordinate.getCoordinateY());
    }

    @Test
    public void should_decrement_x_coordinate_when_moving_mower_ahead_and_mower_direction_is_west() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.WEST)
                .build();
        // When
        Coordinate coordinate = this.mowerMoveAheadService.apply(mower);

        // Then
        Assertions.assertEquals(0, coordinate.getCoordinateX());
        Assertions.assertEquals(2, coordinate.getCoordinateY());
    }
}
