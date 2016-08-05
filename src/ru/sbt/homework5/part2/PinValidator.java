package ru.sbt.homework5.part2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirill on 04.08.16
 */
public class PinValidator {

    private final Map<Integer, String> pins = new HashMap<>();

    {
        pins.put(1, "1234");
        pins.put(2, "1235");
        pins.put(3, "1236");
    }

    public boolean checkPin(int id, String pin) {
        return this.pins.get(id).equals(pin);
    }
}
