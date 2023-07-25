package multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerDemo {
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    public void produce() {
        System.out.println("Producer started");
        while (true) {
            synchronized (lock) {
                try {
                    while (queue.size() >= LIMIT) {
                        System.out.println("Producer waiting");
                        lock.wait();
                        System.out.println("Producer awake");
                    }
                    sleepForRandomIntervalInSeconds(1, 2);
                    queue.offer(1);
                    System.out.println("PUT : " + queue.size());
                    lock.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void consume() {
        System.out.println("Consumer started");
        while (true) {
            synchronized (lock) {
                try {
                    while (queue.isEmpty()) {
                        System.out.println("Consumer waiting");
                        lock.wait();
                        System.out.println("Consumer awake");
                    }
                    sleepForRandomIntervalInSeconds(1, 2);
                    queue.poll();
                    System.out.println("TAKE: " + queue.size());
                    lock.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void sleepForRandomIntervalInSeconds(int start, int end) throws InterruptedException {
        Random random = new Random();
        long sleepInterval = random.nextInt(start, end);
        Thread.sleep(1000 * sleepInterval);
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerDemo pc = new ProducerConsumerDemo();
        Thread producerThread = new Thread(pc::produce);
        Thread consumerThread = new Thread(pc::consume);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}
