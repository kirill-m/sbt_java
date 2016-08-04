package ru.sbt.homework5.part2;

/**
 * Created by kirill on 04.08.16
 */
public interface Terminal {
    int checkAccount() throws NotAllowedOperationException;

    boolean getMoney(int money) throws NotAllowedOperationException;

    void putMoney(int money) throws NotAllowedOperationException;
}
