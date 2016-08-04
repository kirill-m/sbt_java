package ru.sbt.homework5.part2;

/**
 * Created by kirill on 04.08.16
 */
public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(String pin, PinValidator pinValidator) {
        this.pinValidator = pinValidator;

        if (pinValidator.checkPin(pin))
            server = new TerminalServer(0, true);
        else
            server = new TerminalServer(server.getAttemptsNumber() + 1, false);
    }


    @Override
    public int checkAccount() {
        if (server.isAccessGranted())
            return server.requestBalance();
        else
            throw new NotAllowedOperationException("Operation is not allowed. Enter pin first.", new RuntimeException());
    }

    @Override
    public boolean getMoney(int money) {
        if (server.isAccessGranted())
            return server.getMoney(money);
        else
            throw new NotAllowedOperationException("Operation is not allowed. Enter pin first.", new RuntimeException());
    }

    @Override
    public void putMoney(int money) {
        if (server.isAccessGranted())
            server.putMoney(money);
        else
            throw new NotAllowedOperationException("Operation is not allowed. Enter pin first.", new RuntimeException());
    }
}
