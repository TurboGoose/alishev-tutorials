package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        final int NUM_THREADS = 30;
        Connection connection = Connection.getInstance();
        try (ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS)) {
            for (int i = 0; i < NUM_THREADS; i++) {
                executorService.submit(connection::work);
            }
            executorService.shutdown();
            boolean terminated = executorService.awaitTermination(1, TimeUnit.HOURS);
            System.out.println("Executor service terminated: " + terminated);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}


class Connection {
    private static final Connection connection = new Connection();
    private final Semaphore semaphore = new Semaphore(5);

    private Connection() {
    }

    public static Connection getInstance() {
        return connection;
    }

    public void work() {
        try {
            System.out.printf("%s WAIT%n", Thread.currentThread().getName());
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            doWork();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    private void doWork() throws InterruptedException {
        System.out.printf("%s START%n", Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.printf("%s FINISH%n", Thread.currentThread().getName());
    }
}