package com.sg.tondeuse.convertor;

import com.sg.tondeuse.model.Movement;
import com.sg.tondeuse.model.Orientation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Constant {

    public final static Map<String, Movement> movementMap = new HashMap<>() {
        {
            put("A", Movement.AHEAD);
            put("G", Movement.TO_LEFT);
            put("D", Movement.TO_RIGHT);
        }
    };

    public final static Map<String, Orientation> orientationMap = new HashMap<>() {
        {
            put("N", Orientation.NORTH);
            put("E", Orientation.EAST);
            put("W", Orientation.WEST);
            put("S", Orientation.SOUTH);
        }
    };

    public static <K, V> Optional<K> keys(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
    }

}
