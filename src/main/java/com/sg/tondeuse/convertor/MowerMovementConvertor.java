package com.sg.tondeuse.convertor;


import com.sg.tondeuse.model.Movement;
import com.sg.tondeuse.model.Mower;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class MowerMovementConvertor implements Function<List<String>, Map<Mower, List<Movement>>> {

    private final MowerLineConvertor mowerLineConvertor = new MowerLineConvertor();
    private final InstructionMovementLineConvertor instructionMovementLineConvertor = new InstructionMovementLineConvertor();

    @Override
    public Map<Mower, List<Movement>> apply(List<String> fileLines) {
        Map<Mower, List<Movement>> mowerMovementMap = new LinkedHashMap<>();
        Mower mower = null;
        for (int index = 0; index < fileLines.size(); index++) {
            if (index % 2 == 0) {
                mower = mowerLineConvertor.apply(fileLines.get(index));
                mowerMovementMap.put(mower, new ArrayList<>());
            } else {
                mowerMovementMap.get(mower).addAll(instructionMovementLineConvertor.apply(fileLines.get(index)));
            }
        }
        return mowerMovementMap;
    }
}
