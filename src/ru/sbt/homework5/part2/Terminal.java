package ru.sbt.homework5.part2;

/**
 * Created by kirill on 04.08.16
 */
public interface Terminal {
    int checkBalance();

    void getMoney(int money);

    void putMoney(int money);

    boolean enterPin(String pin);
}
