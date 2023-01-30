package com.sg.tondeuse.convertor;


import com.sg.tondeuse.model.Movement;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
public class InstructionMovementLineConvertor implements Function<String, List<Movement>> {

    public static final String NO_DELIMITER = "";


    @Override
    public List<Movement> apply(String s) {
        return Arrays.stream(s.split(NO_DELIMITER))
                .map(Constant.movementMap::get)
                .collect(Collectors.toList());
    }
}
