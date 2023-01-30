package com.sg.tondeuse.convertor;


import com.sg.tondeuse.model.Coordinate;
import com.sg.tondeuse.model.Mower;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MowerLineConvertor implements Function<String, Mower> {

    public static final String ESCAPE = " ";

    @Override
    public Mower apply(String mowerLine) {
        String[] mowerInputs = mowerLine.split(ESCAPE);
        Integer coordinateX = Integer.parseInt(mowerInputs[0]);
        Integer coordinateY = Integer.parseInt(mowerInputs[1]);

        return Mower.builder()
                .orientation(Constant.orientationMap.get(mowerInputs[2]))
                .coordinate(Coordinate.builder().coordinateY(coordinateY).coordinateX(coordinateX).build())
                .build();
    }
}
