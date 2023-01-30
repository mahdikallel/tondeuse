package com.sg.tondeuse.service.mowermover.printer;

import com.sg.tondeuse.convertor.Constant;
import com.sg.tondeuse.convertor.LawnLineConvertor;
import com.sg.tondeuse.convertor.MowerMovementConvertor;
import com.sg.tondeuse.model.Lawn;
import com.sg.tondeuse.model.Mower;
import com.sg.tondeuse.service.mowermover.MowerMoverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MowerMovementPrinterService {

    private final LawnLineConvertor lawnLineConvertor;
    private final MowerMovementConvertor mowerMovementConvertor;
    private final MowerMoverService mowerMoverService;

    public MowerMovementPrinterService(LawnLineConvertor lawnLineConvertor, MowerMovementConvertor mowerMovementConvertor, MowerMoverService mowerMoverService) {
        this.lawnLineConvertor = lawnLineConvertor;
        this.mowerMovementConvertor = mowerMovementConvertor;
        this.mowerMoverService = mowerMoverService;
    }

     StringBuilder prepareOutput(Mower mower) {
        return new StringBuilder()
                .append(mower.getCoordinate().getCoordinateX())
                .append(" ")
                .append(mower.getCoordinate().getCoordinateY())
                .append(" ")
                .append(Constant.keys(Constant.orientationMap, mower.getOrientation()).get())
                .append(" ");
    }

    public String print(List<String> fileLines) {
        Lawn lawn = lawnLineConvertor.apply(fileLines.get(0));
        StringBuilder output = new StringBuilder();
        mowerMovementConvertor.apply(fileLines.stream().skip(1).collect(Collectors.toList()))
                .forEach((mower, movements) -> {
                    Mower movedMower = mowerMoverService.moveMower(lawn, mower, movements);
                    output.append(prepareOutput(movedMower));
                });
        return output.toString();
    }

}
