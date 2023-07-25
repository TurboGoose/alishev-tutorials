package multithreading;

import java.util.Random;
import java.util.concurrent.*;

public class FutureCallableDemo {
    public static void main(String[] args) throws InterruptedException {
//        callableWithThread();
//        callableWithExecutorService();
        try {
            testExceptionInRunnableThread();
        } catch (Throwable ex) {
            System.out.println("Some exception occurred");
        }
    }

    static void testExceptionInRunnableThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("Exception thrown");
        });

        thread.start();
        thread.join();
    }

    static void callableWithExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("Thread started");
            Thread.sleep(3000);
            int num = new Random().nextInt(100);
            if (num < 50) {
                throw new RuntimeException("Bad shit here");
            }
            System.out.println("Thread finished");
            return num;
        });
        executorService.shutdown();

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static void callableWithThread() {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("Thread started");
            Thread.sleep(3000);
            int num = new Random().nextInt(100);
            if (num < 50) {
                throw new RuntimeException("Bad shit here");
            }
            System.out.println("Thread finished");
            return num;
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
