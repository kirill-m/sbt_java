package ru.sbt.homework16.ping_pong;

/**
 * Created by kirill on 15.09.16
 */
public class PingPong {
    public static volatile Boolean state = false;

    public static void main(String[] args) {
        new Thread(new Player(true)).start();
        new Thread(new Player(false)).start();
    }

    private static class Player implements Runnable {
        private final boolean condition;
        private final String word;

        public Player(boolean condition) {
            this.condition = condition;
            word = condition ? "PING" : "PONG";
        }

        @Override
        public void run() {
            while (true) {
                synchronized (PingPong.class) {
                    while (state != condition) {
                        try {
                            PingPong.class.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Exception happened while Player waiting", e);
                        }
                    }
                    System.out.println(word);
                    state = !state;
                    notifyPlayer();
                }
            }
        }

        private void notifyPlayer() {
            synchronized (PingPong.class) {
                PingPong.class.notify();
            }
        }
    }
}
