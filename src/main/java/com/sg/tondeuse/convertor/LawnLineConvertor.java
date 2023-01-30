package com.sg.tondeuse.convertor;


import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Lawn;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LawnLineConvertor implements Function<String, Lawn> {

    public static final String ESCAPE = " ";

    @Override
    public Lawn apply(String mowerLine) {
        String[] mowerInputs = mowerLine.split(ESCAPE);
        Integer coordinateX = Integer.parseInt(mowerInputs[0]);
        Integer coordinateY = Integer.parseInt(mowerInputs[1]);
        return Lawn.builder()
                .bottomLeftCorner(Coordinate.builder().coordinateX(0).coordinateY(0).build())
                .upperRightCorner(Coordinate.builder().coordinateX(coordinateX).coordinateY(coordinateY).build())
                .build();
    }
}
