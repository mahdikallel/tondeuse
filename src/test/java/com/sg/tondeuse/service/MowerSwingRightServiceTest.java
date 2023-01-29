package com.sg.tondeuse.service;

import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.model.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MowerSwingRightServiceTest {


    @Autowired
    private  MowerSwingRightService mowerSwingRightService;


    @Test
    public void should_set_orientation_east_when_swing_mower_to_left_and_mower_direction_is_north() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.NORTH)
                .build();
        // When
        Orientation orientation = this.mowerSwingRightService.apply(mower);

        // Then
        Assertions.assertEquals(orientation, Orientation.EAST);
    }

    @Test
    public void should_set_orientation_south_when_swing_mower_to_left_and_mower_direction_is_east() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.EAST)
                .build();
        // When
        Orientation orientation = this.mowerSwingRightService.apply(mower);

        // Then
        Assertions.assertEquals(orientation, Orientation.SOUTH);
    }

    @Test
    public void should_set_orientation_west_when_swing_mower_to_left_and_mower_direction_is_south() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.SOUTH)
                .build();
        // When
        Orientation orientation = this.mowerSwingRightService.apply(mower);

        // Then
        Assertions.assertEquals(orientation, Orientation.WEST);
    }

    @Test
    public void should_set_orientation_north_when_swing_mower_to_left_and_mower_direction_is_west() {
        //  Given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(2).build())
                .orientation(Orientation.WEST)
                .build();
        // When
        Orientation orientation = this.mowerSwingRightService.apply(mower);

        // Then
        Assertions.assertEquals(orientation, Orientation.NORTH);
    }
}
