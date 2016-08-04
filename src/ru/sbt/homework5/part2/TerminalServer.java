package ru.sbt.homework5.part2;

/**
 * Created by kirill on 04.08.16
 */
public class TerminalServer {
    private int attemptsNumber = 0;
    private boolean accessGranted = false;

    public TerminalServer(int attemptsNumber, boolean accessGranted) {
        this.attemptsNumber = attemptsNumber;
        this.accessGranted = accessGranted;
    }

    public int requestBalance() {
        return 0;
    }

    public boolean getMoney(int money) {
        return true;
    }

    public boolean isAccessGranted() {
        return accessGranted;
    }

    public int getAttemptsNumber() {
        return attemptsNumber;
    }

    public void putMoney(int money) {

    }
}
