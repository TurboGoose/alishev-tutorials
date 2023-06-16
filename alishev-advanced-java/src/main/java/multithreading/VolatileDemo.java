package multithreading;

import java.util.Scanner;

public class VolatileDemo {
    public static void main(String[] args) {
        InfiniteThread thread = new InfiniteThread();
        thread.start();

        try (Scanner sc = new Scanner(System.in)) {
            sc.nextLine();
        }

        thread.shutdown();
    }
}

class InfiniteThread extends Thread {
    private volatile boolean running = true;

    public void shutdown() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("Infinite thread running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}