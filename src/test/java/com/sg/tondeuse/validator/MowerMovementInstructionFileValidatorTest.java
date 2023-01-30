package com.sg.tondeuse.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MowerMovementInstructionFileValidatorTest {

    @Autowired
    private MowerMovementInstructionFileValidator movementInstructionValidator;

    @Test
    public void should_validate_mower_instructions() {
        // given
        List<String> lines = List.of(
                "5 5 ",
                "1 2 N ",
                "GAGAGAGAA"
        );

        // when
        boolean test = movementInstructionValidator.test(lines);

        // then
        Assertions.assertTrue(test);
    }

    @Test
    public void should_throw_exception_when_first_line_wrong() {
        // given
        List<String> lines = List.of(
                "5 5R ",
                "1 2 N ",
                "GAGAGAGAA"
        );

        // when
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> movementInstructionValidator.test(lines));


        // then
        Assertions.assertEquals("invalid Lawn position coordinates Line 0!", illegalArgumentException.getMessage());
    }

    @Test
    public void should_throw_exception_when_mower_line_wrong() {
        // given
        List<String> lines = List.of(
                "5 5 ",
                "1 2F N ",
                "GAGAGAGAA"
        );

        // when
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> movementInstructionValidator.test(lines));


        // then
        Assertions.assertEquals("invalid mower inputs ! Line 1", illegalArgumentException.getMessage());
    }

    @Test
    public void should_throw_exception_when_movement_instruction_line_wrong() {
        // given
        List<String> lines = List.of(
                "5 5 ",
                "1 2 N ",
                "GAGAGAG AA F"
        );

        // when
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> movementInstructionValidator.test(lines));


        // then
        Assertions.assertEquals("invalid instruction movements ! Line 2", illegalArgumentException.getMessage());
    }

    @Test
    public void should_throw_exception_when_file_is_empty() {
        // given
        List<String> lines = List.of();

        // when
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> movementInstructionValidator.test(lines));


        // then
        Assertions.assertEquals("File empty !", illegalArgumentException.getMessage());
    }
}
