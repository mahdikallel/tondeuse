package com.sg.tondeuse.service.mowermover.printer;

import com.sg.tondeuse.convertor.Constant;
import com.sg.tondeuse.convertor.LawnLineConvertor;
import com.sg.tondeuse.convertor.MowerMovementConvertor;
import com.sg.tondeuse.model.Lawn;
import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.service.mowermover.MowerMoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MowerMovementPrinterService {
    public static final String ESCAPE = " ";
    private final LawnLineConvertor lawnLineConvertor;
    private final MowerMovementConvertor mowerMovementConvertor;
    private final MowerMoverService mowerMoverService;

    StringBuilder prepareOutput(Mower mower) {
        return new StringBuilder()
                .append(mower.getCoordinate().getCoordinateX())
                .append(ESCAPE)
                .append(mower.getCoordinate().getCoordinateY())
                .append(ESCAPE)
                .append(Constant.keys(Constant.orientationMap, mower.getOrientation()).get());
    }

    public String print(List<String> fileLines) {
        Lawn lawn = lawnLineConvertor.apply(fileLines.get(0));
        return mowerMovementConvertor.apply(fileLines.stream().skip(1).collect(Collectors.toList()))
                .entrySet()
                .stream()
                .map(entry -> prepareOutput(mowerMoverService.moveMower(lawn, entry.getKey(), entry.getValue())))
                .collect(Collectors.joining(ESCAPE));
    }

}
