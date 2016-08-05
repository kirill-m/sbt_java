package ru.sbt.homework5.part2;

import java.util.Date;

/**
 * Created by kirill on 05.08.16
 */
public class Account {
    private final Date lockedUntil;
    private final int id;
    private int balance;

    public Account(Date lockedUntil, int id, int balance) {
        this.lockedUntil = lockedUntil;
        this.id = id;
        this.balance = balance;
    }

    public boolean isLocked() {
        if (lockedUntil == null || (lockedUntil.getTime() - new Date().getTime()) < 0)
            return false;
        else
            return true;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void getMoney(int money) {
        balance -= money;
    }

    public void putMoney(int money) {
        balance += money;
    }

    public long timeRemain() {
        return (lockedUntil.getTime() - new Date().getTime()) / 1000;
    }
}
