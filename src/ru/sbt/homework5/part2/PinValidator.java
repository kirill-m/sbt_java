package ru.sbt.homework5.part2;

/**
 * Created by kirill on 04.08.16
 */
public class PinValidator {
    private final String pin;

    public PinValidator(String pin) {
        this.pin = pin;
    }

    public boolean checkPin(String pin) {
        return this.pin.equals(pin);
    }

}
