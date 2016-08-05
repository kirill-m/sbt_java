package ru.sbt.homework5.part2;

/**
 * Created by kirill on 05.08.16
 */
public class TerminalTest {
    public static void main(String[] args) throws InterruptedException {
        final int userId = 1;
        Terminal terminal = new TerminalImpl(userId);

        terminal.enterPin("12");
        terminal.enterPin("12");
        terminal.enterPin("12");
        terminal.enterPin("12");
        terminal.enterPin("12");

        Thread.sleep(5000);
        terminal.enterPin("1234");
        System.out.println("Your balance: " + terminal.checkBalance());
        terminal.putMoney(199);
    }
}
