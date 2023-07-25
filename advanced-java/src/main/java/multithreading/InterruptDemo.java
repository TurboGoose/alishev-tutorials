package multithreading;

import java.util.Random;
import java.util.Scanner;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        InterruptDemo demo = new InterruptDemo();
//        Thread thread = new Thread(demo::doHeavyCalculations);
        Thread thread = new Thread(demo::longSleep);

        thread.start();
        System.out.println("Thread started");

        try (Scanner sc = new Scanner(System.in)) {
            sc.nextLine();
        }
        thread.interrupt();

        thread.join();
        System.out.println("Thread finished");
    }

    void doHeavyCalculations() {
        Random random = new Random();
        for (int i = 0; i < 1_000_000_000; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Calculations interrupted");
                break;
            }
            Math.sin(random.nextInt());
        }
    }

    void longSleep() {
        try {
            Thread.sleep(1_000_000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }
}
