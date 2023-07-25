package multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
    private final Random random = new Random();

    public void start() throws InterruptedException {
        Thread producerThread = new Thread(this::produce);
        Thread consumerThread = new Thread(this::consume);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

    public void consume() {
        try {
            while (true) {
                sleepForRandomIntervalInSeconds(2, 3);
                queue.take();
                System.out.printf("TAKE (size: %d)%n", queue.size());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void produce() {
        try {
            while (true) {
                sleepForRandomIntervalInSeconds(3, 4);
                queue.put(random.nextInt(100));
                System.out.printf("PUT  (size: %d)%n", queue.size());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sleepForRandomIntervalInSeconds(int start, int end) throws InterruptedException {
        long sleepInterval = random.nextInt(start, end);
        Thread.sleep(1000 * sleepInterval);
    }


    public static void main(String[] args) throws InterruptedException {
        new BlockingQueueDemo().start();
    }
}
