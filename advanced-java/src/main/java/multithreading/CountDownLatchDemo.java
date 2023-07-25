package multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 3; i++) {
                executorService.submit(new Processor(latch));
            }

            long start = System.currentTimeMillis();

            executorService.shutdown();
            for (int i = 0; i < 3; i++) {
                Thread.sleep(2000);
                latch.countDown();
            }

            long end = System.currentTimeMillis();

            System.out.println("Elapsed time " + (end - start));
        }
    }
}

class Processor implements Runnable {
    private final CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.printf("Thread %s started%n", Thread.currentThread());
        try {
            Thread.sleep(3000);
            latch.await();
            System.out.printf("Thread %s proceeded%n", Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}