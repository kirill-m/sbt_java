package ru.sbt.homework5.part2;

import ru.sbt.homework5.part2.exceptions.AttemptsLimitException;
import ru.sbt.homework5.part2.exceptions.BadMoneyException;
import ru.sbt.homework5.part2.exceptions.ConnectionLostException;
import ru.sbt.homework5.part2.exceptions.NotEnoughMoneyException;

import javax.security.auth.login.AccountLockedException;
import java.util.*;

/**
 * Created by kirill on 04.08.16
 */
public class TerminalServer {
    private final int maxCount = 3;
    private int attemptsNumber = 0;
    private boolean accessGranted = false;
    private Date lockedUntil;
    private boolean isLocked = false;
    private Account user;
    private final List<Account> accounts = new ArrayList<>();

    {
        accounts.add(new Account(null, 1, 15000));
        accounts.add(new Account(null, 2, 1000));
        accounts.add(new Account(null, 3, 7000));
    }

    public int requestBalance(int id) {
        return accounts.get(id).getBalance();
    }

    public void getMoney(int id, int money) throws ConnectionLostException {
        if (accounts.get(id).getBalance() > money) {
            accounts.get(id).getMoney(money);
        } else if (accounts.get(id).getBalance() < money)
            throw new NotEnoughMoneyException("Not enough money on your account.", new RuntimeException());
        else if (money % 100 != 0)
            throw new BadMoneyException("You can get only multiple 100 sum.", new RuntimeException());
    }

    public void putMoney(int id, int money) {
        if (money % 100 != 0)
            throw new BadMoneyException("You can put only multiple 100 sum.", new RuntimeException());

        accounts.get(id).putMoney(money);
    }

    public boolean isAccessGranted() {
        return accessGranted;
    }

    public void pinCorrect() {
        accessGranted = true;
    }

    public void pinWrong(int id) throws AccountLockedException {
        if (!accounts.get(id).isLocked()) {
            attemptsNumber++;
            if (attemptsNumber >= maxCount) {
                long newLock = new Date().getTime() + 5000;
                Account newAccount = new Account(new Date(newLock), id, accounts.get(id).getBalance());
                accounts.set(id,newAccount);
                try {
                    throw new AttemptsLimitException("Wrong pin 3 times. Account is locked for 5 sec.", new RuntimeException());
                } catch (AttemptsLimitException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else
            throw new AccountLockedException("Your account is locked. Please wait " + accounts.get(id).timeRemain() + " seconds");
    }

    public Account getUserById(int userId) {
        return accounts.get(userId - 1);
    }
}
