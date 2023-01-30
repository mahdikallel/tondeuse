package com.sg.tondeuse.service.mowermover.printer;

import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.model.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MowerMovementPrinterServiceTest {

    @Autowired
    MowerMovementPrinterService mowerMovementPrinterService;

    @Test
    public void should_print_valid_output_format() {

        //given
        List<String> lines = List.of(
                "5 5 ",
                "1 2 N ",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );

        // when
        String output = mowerMovementPrinterService.print(lines);

        // then
        String expected = "1 3 N 5 1 E";
        Assertions.assertEquals(expected, output);
    }

    @Test
    public void should_print_valid_mower_output_format() {

        //given
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder().coordinateX(1).coordinateY(3).build())
                .orientation(Orientation.NORTH)
                .build();

        // when
        StringBuilder actual = mowerMovementPrinterService.prepareOutput(mower);

        // then
        String expected = "1 3 N";
        Assertions.assertEquals(expected, actual.toString());
    }
}
