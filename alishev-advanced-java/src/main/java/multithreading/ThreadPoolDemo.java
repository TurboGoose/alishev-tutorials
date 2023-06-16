package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        final int workCount = 5;
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (int i = 0; i < workCount; i++) {
                executorService.submit(new Worker());
            }
            executorService.shutdown();
            boolean terminated = executorService.awaitTermination(1, TimeUnit.HOURS);
            System.out.println("Thread pool " + (terminated ? "terminated successfully" : "not terminated"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    static class Worker implements Runnable {
        private static int INSTANCE_COUNTER = 1;
        private final int id;

        public Worker() {
            id = INSTANCE_COUNTER++;
        }

        @Override
        public void run() {
            System.out.printf("Work %d started%n", id);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Work %d completed%n", id);
        }
    }
}
