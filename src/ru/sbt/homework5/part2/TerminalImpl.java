package ru.sbt.homework5.part2;

import ru.sbt.homework5.part2.exceptions.NotAllowedOperationException;

import javax.security.auth.login.AccountLockedException;

/**
 * Created by kirill on 04.08.16
 */
public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private Account user;

    public TerminalImpl(int userId) {
        this.pinValidator = new PinValidator();
        this.server = new TerminalServer();
        this.user = server.getUserById(userId);
    }


    @Override
    public int checkBalance() {
        if (server.isAccessGranted())
            return server.requestBalance(user.getId());
        else
            throw new NotAllowedOperationException("Operation is not allowed. Enter pin first.", new RuntimeException());
    }

    @Override
    public void getMoney(int money) {
        if (server.isAccessGranted())
            server.getMoney(user.getId() ,money);
        else
            throw new NotAllowedOperationException("Operation is not allowed. Enter pin first.", new RuntimeException());
    }

    @Override
    public void putMoney(int money) {
        if (server.isAccessGranted())
            server.putMoney(user.getId() ,money);
        else
            throw new NotAllowedOperationException("Operation is not allowed. Enter pin first.", new RuntimeException());
    }

    public boolean enterPin(String pin) {
        if (pinValidator.checkPin(user.getId(), pin)) {
            server.pinCorrect();
            return true;
        } else {
            try {
                server.pinWrong(user.getId());
            } catch (AccountLockedException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
    }
}
